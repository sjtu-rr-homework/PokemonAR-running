package example.com.pkmnavidemo4.classes;

import org.junit.Test;

import example.com.pkmnavidemo4.R;

import static org.junit.Assert.*;

public class ElfSourceControllerTest {

    @Test
    public void getAttack() {
        //对战伤害测试
        assertEquals(30, ElfSourceController.getAttack(1,1));
        assertEquals(0, ElfSourceController.getAttack(1,1000));
        assertEquals(59, ElfSourceController.getAttack(1000,1));
    }

    @Test
    public void getPower() {
        //精灵战斗力测试
        assertEquals(0, ElfSourceController.getPower(10000,1,1));

        assertEquals(1, ElfSourceController.getPower(1,1,1));
        assertEquals(189, ElfSourceController.getPower(1,10,1));
        assertEquals(4725, ElfSourceController.getPower(1,50,1));
        assertEquals(18523, ElfSourceController.getPower(1,99,1));
        assertEquals(6075, ElfSourceController.getPower(1,50,2));
        assertEquals(23816, ElfSourceController.getPower(1,99,2));
    }

    @Test
    public void getBackground() {
        //图鉴界面图片测试
        assertEquals(R.drawable.charizard, ElfSourceController.getBackground(8888));
        assertEquals(R.drawable.bulbasaur, ElfSourceController.getBackground(1));
        assertEquals(R.drawable.charmander, ElfSourceController.getBackground(2));
        assertEquals(R.drawable.squirtle, ElfSourceController.getBackground(3));
        assertEquals(R.drawable.pikachu, ElfSourceController.getBackground(4));
        assertEquals(R.drawable.psyduck, ElfSourceController.getBackground(5));
    }

    @Test
    public void getMaxLevel() {
        //精灵最高形态测试
        assertEquals(2, ElfSourceController.getMaxLevel(1));
        assertEquals(3, ElfSourceController.getMaxLevel(2));
        assertEquals(3, ElfSourceController.getMaxLevel(3));
        assertEquals(2, ElfSourceController.getMaxLevel(4));
        assertEquals(2, ElfSourceController.getMaxLevel(5));
        assertEquals(2, ElfSourceController.getMaxLevel(6));
        assertEquals(2, ElfSourceController.getMaxLevel(1555));
    }

    @Test
    public void getBackgroundWithLevel() {
        //图鉴界面图片测试
        assertEquals(R.drawable.bulbasaur, ElfSourceController.getBackgroundWithLevel(1,1));
        assertEquals(R.drawable.bulbasaur2, ElfSourceController.getBackgroundWithLevel(1,2));
        assertEquals(R.drawable.bulbasaur, ElfSourceController.getBackgroundWithLevel(1,111));
        assertEquals(R.drawable.charmander, ElfSourceController.getBackgroundWithLevel(2,1));
        assertEquals(R.drawable.charmander2, ElfSourceController.getBackgroundWithLevel(2,2));
        assertEquals(R.drawable.charmander3, ElfSourceController.getBackgroundWithLevel(2,3));
        assertEquals(R.drawable.charmander, ElfSourceController.getBackgroundWithLevel(2,11));
        assertEquals(R.drawable.squirtle, ElfSourceController.getBackgroundWithLevel(3,1));
        assertEquals(R.drawable.squirtle2, ElfSourceController.getBackgroundWithLevel(3,2));
        assertEquals(R.drawable.squirtle3, ElfSourceController.getBackgroundWithLevel(3,3));
        assertEquals(R.drawable.squirtle, ElfSourceController.getBackgroundWithLevel(3,11));
        assertEquals(R.drawable.pikachu, ElfSourceController.getBackgroundWithLevel(4,1));
        assertEquals(R.drawable.pikachu2, ElfSourceController.getBackgroundWithLevel(4,2));
        assertEquals(R.drawable.pikachu, ElfSourceController.getBackgroundWithLevel(4,111));
        assertEquals(R.drawable.psyduck, ElfSourceController.getBackgroundWithLevel(5,1));
        assertEquals(R.drawable.psyduck2, ElfSourceController.getBackgroundWithLevel(5,2));
        assertEquals(R.drawable.psyduck, ElfSourceController.getBackgroundWithLevel(5,111));
        assertEquals(R.drawable.catchball, ElfSourceController.getBackgroundWithLevel(599,111));
    }

    @Test
    public void getColorfulElfName() {
    }

    @Test
    public void getName() {
    }

    @Test
    public void getModel() {
        //模型测试
        assertEquals(R.raw.bulbasaur, ElfSourceController.getModel(1));
        assertEquals(R.raw.charizard, ElfSourceController.getModel(2));
        assertEquals(R.raw.squirtle, ElfSourceController.getModel(3));
        assertEquals(R.raw.pikachu, ElfSourceController.getModel(4));
        assertEquals(R.raw.psyduck, ElfSourceController.getModel(5));
        assertEquals(R.raw.charizard, ElfSourceController.getModel(7777));
    }

    @Test
    public void getModel1() {
    }

    @Test
    public void getMapPic() {
        //地图头像测试
        assertEquals(R.drawable.elf_1, ElfSourceController.getMapPic(1));
        assertEquals(R.drawable.elf_2, ElfSourceController.getMapPic(2));
        assertEquals(R.drawable.elf_3, ElfSourceController.getMapPic(3));
        assertEquals(R.drawable.elf_4, ElfSourceController.getMapPic(4));
        assertEquals(R.drawable.elf_5, ElfSourceController.getMapPic(5));
        assertEquals(0, ElfSourceController.getMapPic(6666));
    }
}