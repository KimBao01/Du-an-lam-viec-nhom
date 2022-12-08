package com.example.project_english_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Mem_Adapter_Quiz extends BaseAdapter {
    ArrayList<member> MemberList;
    Context context;
    int layout;

    public Mem_Adapter_Quiz(ArrayList<member> memberList, Context context, int layout) {
        MemberList = memberList;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return MemberList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView TvTop, TvName, TvSoCau, TvSao, TvTime;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.TvTop = (TextView) view.findViewById(R.id.TvTop);
            holder.TvName = (TextView) view.findViewById(R.id.TvName);
            holder.TvSoCau = (TextView) view.findViewById(R.id.TvSoCau);
            holder.TvTime = (TextView) view.findViewById(R.id.TvTime);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        member Member = MemberList.get(i);
        holder.TvTop.setText(String.valueOf(i+1));
        holder.TvName.setText(Member.getName());
        holder.TvSoCau.setText("Số câu : " + String.valueOf(Member.getSoCau()));
        holder.TvTime.setText("Thời gian : " + String.valueOf(Member.getEar_Finish_Time()) + "s");


        return view;
    }

}
