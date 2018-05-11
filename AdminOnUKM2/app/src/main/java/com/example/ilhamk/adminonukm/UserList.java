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

public class UserList extends ArrayAdapter<UserInformation> {

    private Activity context;
    private List<UserInformation> userList;

    public UserList(Activity context, List<UserInformation> userList){
        super(context, R.layout.list_layout_user, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int pos, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_user, null, true);

        TextView textViewNamaUKM = (TextView) listViewItem.findViewById(R.id.textViewNamaUser);

        UserInformation userInformation = userList.get(pos);

        textViewNamaUKM.setText(userInformation.getNama() + '\n' + userInformation.getRole());

        return listViewItem;
    }
}
