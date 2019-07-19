package example.com.pkmnavidemo4.classes;

import android.content.Context;

import example.com.pkmnavidemo4.R;

public class ElfSourceController {
    //private Context mContext;
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
            default:
                return 99999;
        }
    }
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
            default:
                return 2;
        }
    }
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
            default:
                return R.drawable.charizard;
        }
    }

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
            default:
                return "ERROR";
        }
    }



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
