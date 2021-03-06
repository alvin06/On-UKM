package com.example.ilhamk.adminonukm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.List;

public class UKMList extends ArrayAdapter<UKMInformation> {

   private Activity context;
   private List<UKMInformation> ukmList;

   public UKMList(Activity context, List<UKMInformation> ukmList){
        super(context, R.layout.list_layout_ukm, ukmList);
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

        View listViewItem = inflater.inflate(R.layout.list_layout_ukm, null, true);

        TextView textViewNamaUKM = listViewItem.findViewById(R.id.textViewNamaUKM);
        ImageView imageViewLogo = listViewItem.findViewById(R.id.imageLogoUKM);

        UKMInformation ukmInformation = ukmList.get(pos);

        Picasso.with(getContext()).load(ukmInformation.getLogoUKM()).into(imageViewLogo);

        textViewNamaUKM.setText(ukmInformation.getNamaUKM() + '\n' + ukmInformation.getKategori());

        return listViewItem;
    }
}
