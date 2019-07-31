package example.com.pkmnavidemo4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import example.com.pkmnavidemo4.classes.HttpHandler;
import example.com.pkmnavidemo4.Bitmap.Adapter;
import example.com.pkmnavidemo4.Bitmap.BitmapUtils;
import example.com.pkmnavidemo4.classes.UserData;

public class ShareActivity extends Activity {
	private List<Bitmap> data = new ArrayList<Bitmap>();
	private List<String> jsonData=new ArrayList<>();
	private GridView mGridView;
	private String photoPath;
	private Adapter adapter;
    private TextView content;
    private Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
       // 设置默认图片为加号
		Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
		data.add(bp);
		//找到内容控件
		content=findViewById(R.id.act_share_content_et);
		// 找到控件ID
		mGridView = (GridView) findViewById(R.id.act_share_gridView1);
		// 绑定Adapter
		adapter = new Adapter(getApplicationContext(), data, mGridView);
		mGridView.setAdapter(adapter);
		// 设置点击监听事件
		mGridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (data.size() ==10) {
					Toast.makeText(ShareActivity.this, "只能添加九张照片", Toast.LENGTH_SHORT).show();
				} else {
					if (position == data.size() - 1) {
						Toast.makeText(ShareActivity.this, "添加图片", Toast.LENGTH_SHORT).show();
						// 选择图片
						Intent intent = new Intent(Intent.ACTION_PICK, null);
						intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
						startActivityForResult(intent, 0x1);
					} else {
						Toast.makeText(ShareActivity.this, "点击第" + (position + 1) + " 号图片", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		// 设置长按事件
		mGridView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				dialog(position);
				return true;
			}
		});
		send=findViewById(R.id.act_share_send_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timestamp time = new Timestamp(System.currentTimeMillis());
                HttpHandler.postPic(jsonData,time.toString(), UserData.getUserName(),content.getText().toString());
            }
        });
	}
	/*
	 * Dialog对话框提示用户删除操作 position为删除图片位置
	 */
	protected void dialog(final int position) {
		if(position!=data.size()-1) {
			AlertDialog.Builder builder = new Builder(ShareActivity.this);
			builder.setMessage("确认移除已添加图片吗？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					jsonData.remove(position);
					data.remove(position);
					adapter.notifyDataSetChanged();
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
	}

	// 响应startActivityForResult，获取图片路径
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 0x1 && resultCode == RESULT_OK) {
			if (data != null) {

				ContentResolver resolver = getContentResolver();
				try {
					Uri uri = data.getData();
					// 这里开始的第二部分，获取图片的路径：
					String[] proj = { MediaStore.Images.Media.DATA };
					Cursor cursor = managedQuery(uri, proj, null, null, null);
					// 按我个人理解 这个是获得用户选择的图片的索引值
					int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
					cursor.moveToFirst();
					// 最后根据索引值获取图片路径
					photoPath = cursor.getString(column_index);
					Bitmap term=BitmapFactory.decodeFile(photoPath);
					int width=term.getWidth()/8;
					int height=term.getHeight()/8;
					Bitmap bp=BitmapUtils.decodeSampledBitmapFromFd(photoPath,width,height);
					jsonData.add(bitmapToBase64(bp));
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}



	@Override
	protected void onResume() {
		super.onResume();
		if (!TextUtils.isEmpty(photoPath)) {
			Bitmap newBp = BitmapUtils.decodeSampledBitmapFromFd(photoPath, 300, 300);
			data.remove(data.size() - 1);
			Bitmap bp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_addpic);
			data.add(newBp);
			data.add(bp);
			//将路径设置为空，防止在手机休眠后返回Activity调用此方法时添加照片
			photoPath = null;
			adapter.notifyDataSetChanged();
		}
	}

	/**
	 * bitmap转为base64
	 * @param bitmap
	 * @return
	 */
	public static String bitmapToBase64(Bitmap bitmap) {

		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			if (bitmap != null) {
				baos = new ByteArrayOutputStream();
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

				baos.flush();
				baos.close();

				byte[] bitmapBytes = baos.toByteArray();
				result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (baos != null) {
					baos.flush();
					baos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
