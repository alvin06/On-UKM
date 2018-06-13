package com.example.ilhamk.adminonukm;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PengurusList extends ArrayAdapter<PengurusInformation>{

    private Activity context;
    private List<PengurusInformation> anggotaList;

    public PengurusList(Activity context, List<PengurusInformation> anggotaList){
        super(context, R.layout.list_layout_user, anggotaList);
        this.context = context;
        this.anggotaList = anggotaList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_user, null, true);

        TextView textViewNamaUser = (TextView) listViewItem.findViewById(R.id.textViewNamaUser);

        PengurusInformation anggotaInformation = anggotaList.get(pos);

        textViewNamaUser.setText(anggotaInformation.getNama() + '\n' + anggotaInformation.getJabatan());

        return listViewItem;
    }
}
