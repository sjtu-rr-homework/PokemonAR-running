/*
 * Copyright 2018 Google LLC. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.com.pkmnavidemo4;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.core.Trackable;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.NotYetAvailableException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Iterator;
import java.util.List;

import example.com.pkmnavidemo4.Fragments.ElfsFragment;
import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.classes.UserData;


/**
 * This is an example activity that uses the Sceneform UX package to make common AR tasks easier.
 */
public class SceneformActivity extends AppCompatActivity implements Scene.OnUpdateListener {
  private static final String TAG = SceneformActivity.class.getSimpleName();
  private static final double MIN_OPENGL_VERSION = 3.0;
  private ArFragment arFragment;
  private ModelRenderable andyRenderable;
  private Button returnButton;
  private Button catchButton;
  private boolean elfSetted=false;
  @Override
  @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
  // CompletableFuture requires api level 24
  // FutureReturnValueIgnored is not valid
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (!checkIsSupportedDeviceOrFinish(this)) {
      return;
    }

    setContentView(R.layout.activity_ux);
    //获取生成精灵模型的id
      Intent intent=getIntent();
      int variety=intent.getIntExtra("variety", -1);
    arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
    returnButton=findViewById(R.id.act_ux_button_return);
    returnButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              finish();
          }
      });
      catchButton=findViewById(R.id.act_ux_button_catch);
      catchButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(elfSetted) {
                  HttpHandler.successCatch(SceneformActivity.this, UserData.getUserName(),variety+"",""+1);
                  AlertDialog alertDialog = new AlertDialog.Builder(SceneformActivity.this)
                          .setTitle("捕捉成功")
                          .setMessage("是否返回")
                          .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {
                                  finish();
                              }
                          })
                          .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                              @Override
                              public void onClick(DialogInterface dialogInterface, int i) {
                                  return;
                              }
                          }).create();
                  alertDialog.show();
              }
              else
                  Toast.makeText(getApplicationContext(), "尚未发现精灵", Toast.LENGTH_SHORT).show();
          }
      });

    // When you build a Renderable, Sceneform loads its resources in the background while returning
    // a CompletableFuture. Call thenAccept(), handle(), or check isDone() before calling get().
      ModelRenderable.builder()
              .setSource(this, ElfSourceController.getModel(variety))
              .build()
              .thenAccept(renderable -> andyRenderable = renderable)
              .exceptionally(
                      throwable -> {
                          Toast toast =
                                  Toast.makeText(this, "Unable to load andy renderable", Toast.LENGTH_LONG);
                          toast.setGravity(Gravity.CENTER, 0, 0);
                          toast.show();
                          return null;
                      });


    arFragment.getArSceneView().getScene().addOnUpdateListener(this);

    arFragment.setOnTapArPlaneListener(
        (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
          if (andyRenderable == null) {
            return;
          }
          if(!elfSetted) {
              elfSetted=true;
              // Create the Anchor.
              Anchor anchor = hitResult.createAnchor();
              AnchorNode anchorNode = new AnchorNode(anchor);
              anchorNode.setParent(arFragment.getArSceneView().getScene());

              // Create the transformable andy and add it to the anchor.
              TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
              andy.setParent(anchorNode);
              andy.setRenderable(andyRenderable);
          }
        });
  }

    @Override
    public void onUpdate(FrameTime frameTime) {
        //get the frame from the scene for shorthand
       Frame frame = arFragment.getArSceneView().getArFrame();
        if (frame != null) {
            //get the trackables to ensure planes are detected
            Iterator<Plane> var3 = frame.getUpdatedTrackables(Plane.class).iterator();
            while(var3.hasNext()) {
                Plane plane = var3.next();
                //If a plane has been detected & is being tracked by ARCore
                if (plane.getTrackingState() == TrackingState.TRACKING) {
                    //Get all added anchors to the frame
                    Iterator<Anchor> iterableAnchor = frame.getUpdatedAnchors().iterator();
                    //place the first object only if no previous anchors were added
                    if(!iterableAnchor.hasNext()) {
                        //Perform a hit test at the center of the screen to place an object without tapping
                        List<HitResult> hitTest = null;
                        View vw = findViewById(android.R.id.content);
                        Vector3 vector3=new Vector3(vw.getWidth() / 2f, vw.getHeight() / 2f, 0f);
                            hitTest = frame.hitTest(vector3.x,vector3.y);
                        //iterate through all hits
                        Iterator<HitResult> hitTestIterator = hitTest.iterator();
                        while(hitTestIterator.hasNext()) {
                            HitResult hitResult = hitTestIterator.next();
                            //Create an anchor at the plane hit
                            Anchor modelAnchor = hitResult.createAnchor();
                            //Attach a node to this anchor with the scene as the parent
                            if(!elfSetted){
                                elfSetted=true;
                              AnchorNode anchorNode = new AnchorNode(modelAnchor);
                              anchorNode.setParent(arFragment.getArSceneView().getScene());
                              TransformableNode andy = new TransformableNode(arFragment.getTransformationSystem());
                              andy.setParent(anchorNode);
                              andy.setRenderable(andyRenderable);
                              Toast toast2 =
                                     Toast.makeText(this, "精灵出现", Toast.LENGTH_LONG);
                              toast2.setGravity(Gravity.CENTER, 0, 0);
                              toast2.show();
                            }
                        }
                    }
                }
            }
        }
    }

  /**
   * Returns false and displays an error message if Sceneform can not run, true if Sceneform can run
   * on this device.
   *
   * <p>Sceneform requires Android N on the device as well as OpenGL 3.0 capabilities.
   *
   * <p>Finishes the activity if Sceneform can not run
   */
  public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
    if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
      Log.e(TAG, "Sceneform requires Android N or later");
      Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
      activity.finish();
      return false;
    }
    String openGlVersionString =
        ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
            .getDeviceConfigurationInfo()
            .getGlEsVersion();
    if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
      Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
      Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
          .show();
      activity.finish();
      return false;
    }
    return true;
  }

}
