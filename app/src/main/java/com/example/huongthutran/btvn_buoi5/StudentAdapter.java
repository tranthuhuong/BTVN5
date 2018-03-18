package com.example.huongthutran.btvn_buoi5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Huong Thu Tran on 3/18/2018.
 */

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Student> students=new ArrayList<>();
    public StudentAdapter(Context context,ArrayList<Student>l){
        this.context=context;
        students=l;
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView=View.inflate(context,R.layout.activity_item_student,null);
        }
        TextView tvInfo=(TextView)convertView.findViewById(R.id.tv_infoStudent);

        tvInfo.setText(students.get(i).toString());
        return convertView;
    }
}
