package com.example.ilhamk.adminonukm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class UKMList extends ArrayAdapter<UKMInformation> {

   private Activity context;
   private List<UKMInformation> ukmList;

   public UKMList(Activity context, List<UKMInformation> ukmList){
        super(context, R.layout.list_layout_user, ukmList);
        this.context = context;
        this.ukmList = ukmList;
   }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_user, null, true);

        TextView textViewNamaUKM = (TextView) listViewItem.findViewById(R.id.textViewNamaUser);

        UKMInformation ukmInformation = ukmList.get(pos);

        textViewNamaUKM.setText(ukmInformation.getNamaUKM() + '\n' + ukmInformation.getKategori());

        return listViewItem;
    }
}
