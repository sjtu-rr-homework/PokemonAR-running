package example.com.pkmnavidemo4.classes;

import android.content.Context;

import java.util.List;

import example.com.pkmnavidemo4.R;

public class ElfSourceController {
    private Context mContext;

    public ElfSourceController(Context context) {
        mContext = context;
    }
    public int getBackground(int id){
        switch (id){
            case 0:
                return R.drawable.bulbasaur;
            case 1:
                return R.drawable.charizard;
            case 2:
                return R.drawable.squirtle;
            case 3:
                return R.drawable.pikachu;
            case 4:
                return R.drawable.psyduck;
            default:
                return R.drawable.charizard;
        }
    }
    public int getModel(int id){
        switch (id){
            case 1:
                return R.raw.bulbasaur;
            case 2:
                return R.raw.charizard;
            case 3:
                return R.raw.squirtle;
            case 4:
                return R.raw.pikachu;
            case 5:
                return R.raw.psyduck;
            default:
                return R.raw.charizard;
        }
    }
}
