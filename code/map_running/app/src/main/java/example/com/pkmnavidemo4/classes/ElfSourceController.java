package example.com.pkmnavidemo4.classes;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import example.com.pkmnavidemo4.R;

public class ElfSourceController {
    //得到精灵之间互相的伤害值
    public static int getAttack(int leftPower,int rightPower){
        return (30*leftPower)/(rightPower+leftPower/2);
    }
    //精灵战斗力计算;
    public static int getPower(int id,int level,int grade) {
        switch (id) {
            case 1:
                return (int) (1.35 * level * level * (1 + grade * 0.4));
            case 2:
                return (int) (1.25 * level * level * (1 + grade * 0.35));
            case 3:
                return (int) (1.30 * level * level * (1 + grade * 0.28));
            case 4:
                return (int) (1.30 * level * level * (1 + grade * 0.55));
            case 5:
                return (int) (1.10*level*level*(1+grade*0.5));
            case 6:
                return (int) (1.12*level*level*(1+grade*0.46));
            default:
                return 0;
        }
    }
    //精灵图鉴中背景图片
    public static int getBackground(int id){
        switch (id){
            case 1:
                return R.drawable.bulbasaur;
            case 2:
                return R.drawable.charmander;
            case 3:
                return R.drawable.squirtle;
            case 4:
                return R.drawable.pikachu;
            case 5:
                return R.drawable.psyduck;
            default:
                return R.drawable.charizard;
        }
    }
    //精灵的最高形态
    public static int getMaxLevel(int id){
        switch (id){
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 3;
            case 4:
                return 2;
            case 5:
                return 2;
            case 6:
                return 2;
            default:
                return 2;
        }
    }
    //根据精灵种类精灵形态得到背景图片
    public static int getBackgroundWithLevel(int id, int level){
        switch (id){
            case 1:
                switch (level) {
                    case 1:
                        return R.drawable.bulbasaur;
                    case 2:
                        return R.drawable.bulbasaur2;
                    default:
                        return R.drawable.bulbasaur;
                }
            case 2:
                switch (level) {
                    case 1:
                        return R.drawable.charmander;
                    case 2:
                        return R.drawable.charmander2;
                    case 3:
                        return R.drawable.charmander3;
                    default:
                        return R.drawable.charmander;
                }
            case 3:
                switch (level) {
                    case 1:
                        return R.drawable.squirtle;
                    case 2:
                        return R.drawable.squirtle2;
                    case 3:
                        return R.drawable.squirtle3;
                    default:
                        return R.drawable.squirtle;
                }
            case 4:
                switch (level) {
                    case 1:
                        return R.drawable.pikachu;
                    case 2:
                        return R.drawable.pikachu2;
                    default:
                        return R.drawable.pikachu;
                }
            case 5:
                switch (level) {
                    case 1:
                        return R.drawable.psyduck;
                    case 2:
                        return R.drawable.psyduck2;
                    default:
                        return R.drawable.psyduck;
                }
            case 6:
                switch (level) {
                    case 1:
                        return R.drawable.psyduck;
                    case 2:
                        return R.drawable.psyduck2;
                    default:
                        return R.drawable.psyduck;
                }
            default:
                return R.drawable.catchball;
        }
    }

    /*private static SpannableString getColorfulName(String content,int who,int len)
    {
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);
        if(who==0)
            ssb.setSpan(new ForegroundColorSpan(Color.BLUE), 1, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        else
            ssb.setSpan(new ForegroundColorSpan(Color.RED), 1, len, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return SpannableString.valueOf(ssb);
    }
    //得到彩色精灵名字
    public static SpannableString getColorfulElfName(int id, int level,int who)
    {
        switch (id) {
            case 1:
                switch (level) {
                    case 1:
                        return getColorfulName("妙蛙种子",who,4);
                    case 2:
                        return getColorfulName("妙蛙花",who,3);
                    default:
                        return getColorfulName("妙蛙种子",who,4);
                }
            case 2:
                switch (level) {
                    case 1:
                        return getColorfulName("小火龙",who,3);
                    case 2:
                        return getColorfulName("火恐龙",who,3);
                    case 3:
                        return getColorfulName("喷火龙",who,3);
                    default:
                        return getColorfulName("小火龙",who,3);
                }
            case 3:
                switch (level) {
                    case 1:
                        return getColorfulName("杰尼龟",who,3);
                    case 2:
                        return getColorfulName("卡米龟",who,3);
                    case 3:
                        return getColorfulName("水箭龟",who,3);
                    default:
                        return getColorfulName("杰尼龟",who,3);
                }
            case 4:
                switch (level) {
                    case 1:
                        return getColorfulName("皮卡丘",who,3);
                    case 2:
                        return getColorfulName("雷丘",who,2);
                    default:
                        return getColorfulName("皮卡丘",who,3);
                }
            case 5:
                switch (level) {
                    case 1:
                        return getColorfulName("可达鸭",who,3);
                    case 2:
                        return getColorfulName("哥达鸭",who,3);
                    default:
                        return getColorfulName("可达鸭",who,3);
                }
            default:
                return getColorfulName("无",0,1);
        }
    }*/
    //得到精灵名字
    public static String getName(int id, int level){
        switch (id){
            case 1:
                switch (level) {
                    case 1:
                        return "妙蛙种子";
                    case 2:
                        return "妙蛙花";
                    default:
                        return "妙蛙种子";
                }
            case 2:
                switch (level) {
                    case 1:
                        return "小火龙";
                    case 2:
                        return "火恐龙";
                    case 3:
                        return "喷火龙";
                    default:
                        return "小火龙";
                }
            case 3:
                switch (level) {
                    case 1:
                        return "杰尼龟";
                    case 2:
                        return "卡米龟";
                    case 3:
                        return "水箭龟";
                    default:
                        return "杰尼龟";
                }
            case 4:
                switch (level) {
                    case 1:
                        return "皮卡丘";
                    case 2:
                        return "雷丘";
                    default:
                        return "雷丘";
                }
            case 5:
                switch (level) {
                    case 1:
                        return "可达鸭";
                    case 2:
                        return "哥达鸭";
                    default:
                        return "可达鸭";
                }
                case 6:
                switch (level) {
                    case 1:
                        return "可达鸭";
                    case 2:
                        return "哥达鸭";
                    default:
                        return "可达鸭";
                }
            default:
                return "无";
        }
    }


   //得到精灵模型
    public static int getModel(int id, int level) {
        switch (id){
            case 1:
                switch (level) {
                    case 1:
                        return R.raw.bulbasaur;
                    case 2:
                        return R.raw.venusaur;
                    default:
                        return R.raw.bulbasaur;
                }
            case 2:
                switch (level) {
                    case 1:
                        return R.raw.charmander;
                    case 2:
                        return R.raw.charizard;
                    case 3:
                        return R.raw.charmeleon;
                    default:
                        return R.raw.charmander;
                }
            case 3:
                switch (level) {
                    case 1:
                        return R.raw.squirtle;
                    case 2:
                        return R.raw.wartortle;
                    case 3:
                        return R.raw.blastoise;
                    default:
                        return R.raw.squirtle;
                }
            case 4:
                switch (level) {
                    case 1:
                        return R.raw.pikachu;
                    case 2:
                        return R.raw.raichu;
                    default:
                        return R.raw.pikachu;
                }
            case 5:
                switch (level) {
                    case 1:
                        return R.raw.psyduck;
                    case 2:
                        return R.raw.golduck;
                    default:
                        return R.raw.psyduck;
                }
            default:
                return R.raw.pikachu;
        }
    }
    //得到精灵模型
    public static int getModel(int id){
        switch (id){
            case 1:
                return R.raw.bulbasaur;
            case 2:
                return R.raw.charizard;
            case 3:
                return R.raw.squirtle;
            case 4:
                return R.raw.pikachu ;
            case 5:
                return R.raw.psyduck;
            default:
                return R.raw.charizard;
        }
    }
    //得到精灵在地图上的头像
    public static int getMapPic(int id){
        switch (id){
            case 1:
                return R.drawable.elf_1;
            case 2:
                return R.drawable.elf_2;
            case 3:
                return R.drawable.elf_3;
            case 4:
                return R.drawable.elf_4;
            case 5:
                return R.drawable.elf_5;
            default:
                return 0;
        }
    }

}
