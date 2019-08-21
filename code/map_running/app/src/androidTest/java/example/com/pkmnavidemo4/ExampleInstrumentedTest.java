package example.com.pkmnavidemo4;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.Button;

import org.junit.Test;
import org.junit.runner.RunWith;

import example.com.pkmnavidemo4.classes.ElfSourceController;
import example.com.pkmnavidemo4.classes.HttpHandler;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("example.com.pkmnavidemo4", appContext.getPackageName());
    }
    @Test
    public void elfSource(){
        Log.i("log","资源加载");
        assertEquals(R.drawable.charizard, ElfSourceController.getBackground(8888));
        assertEquals(R.drawable.bulbasaur, ElfSourceController.getBackground(1));
        assertEquals(R.drawable.charmander, ElfSourceController.getBackground(2));
        assertEquals(R.drawable.squirtle, ElfSourceController.getBackground(3));
        assertEquals(R.drawable.pikachu, ElfSourceController.getBackground(4));
        assertEquals(R.drawable.psyduck, ElfSourceController.getBackground(5));
        assertEquals(R.raw.bulbasaur, ElfSourceController.getModel(1));
        assertEquals(R.raw.charmander, ElfSourceController.getModel(2));
        assertEquals(R.raw.squirtle, ElfSourceController.getModel(3));
        assertEquals(R.raw.pikachu, ElfSourceController.getModel(4));
        assertEquals(R.raw.psyduck, ElfSourceController.getModel(5));
        assertEquals(R.raw.charizard, ElfSourceController.getModel(7777));
        assertEquals(R.drawable.elf_1, ElfSourceController.getMapPic(1));
        assertEquals(R.drawable.elf_2, ElfSourceController.getMapPic(2));
        assertEquals(R.drawable.elf_3, ElfSourceController.getMapPic(3));
        assertEquals(R.drawable.elf_4, ElfSourceController.getMapPic(4));
        assertEquals(R.drawable.elf_5, ElfSourceController.getMapPic(5));
        assertEquals(0, ElfSourceController.getMapPic(6666));
    }
}
