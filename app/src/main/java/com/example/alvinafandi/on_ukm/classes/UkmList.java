package com.example.alvinafandi.on_ukm.classes;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.example.alvinafandi.on_ukm.R;

import java.util.List;

/**
 * Created by rizky on 6/18/2018.
 */

public class UkmList extends ArrayAdapter<Ukm> {

    private Activity context;
    private List<Ukm> ukmList;

    public UkmList(Activity context, List<Ukm> ukmList) {
        super(context, R.layout.activity_home, ukmList);
        this.context = context;
        this.ukmList = ukmList;
    }


}
