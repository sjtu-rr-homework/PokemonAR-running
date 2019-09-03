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
        assertEquals(0,BitmapUtils.calculateInSampleSize(null,10,0));
    }

    @Test
    public void decodeSampledBitmapFromFd() {
        assertEquals(null,BitmapUtils.decodeSampledBitmapFromFd(null,10,0));
    }

    @Test
    public void base64ToBitmap() {
        assertEquals(null,BitmapUtils.base64ToBitmap(null));
    }

    @Test
    public void bitmapCompress() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Bitmap bp = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.bg_blue);
        assertEquals(null,BitmapUtils.BitmapCompress(bp));
    }

    @Test
    public void bitmapToBase64() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        Bitmap bp = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.bg_blue);
       assertEquals(null,BitmapUtils.bitmapToBase64(null));
        assertEquals(null,BitmapUtils.bitmapToBase64(bp));
    }
}