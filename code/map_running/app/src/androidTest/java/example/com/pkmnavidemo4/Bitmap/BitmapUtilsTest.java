package example.com.pkmnavidemo4.Bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;

import example.com.pkmnavidemo4.R;

import static org.junit.Assert.*;

public class BitmapUtilsTest {

    @Test
    public void decodeSampledBitmapFromResource() {
    }

    @Test
    public void decodeSampledBitmapFromFd() {
    }

    @Test
    public void base64ToBitmap() {
    }

    @Test
    public void bitmapCompress() {
    }

    @Test
    public void bitmapToBase64() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Bitmap bp = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.bg_blue);
       assertEquals(null,BitmapUtils.bitmapToBase64(null));
        assertEquals(null,BitmapUtils.bitmapToBase64(bp));
    }
}