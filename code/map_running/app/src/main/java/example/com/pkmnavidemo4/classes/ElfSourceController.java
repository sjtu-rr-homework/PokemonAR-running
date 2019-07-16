package example.com.pkmnavidemo4.classes;

import android.content.Context;

import example.com.pkmnavidemo4.R;

public class ElfSourceController {
    //private Context mContext;

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
