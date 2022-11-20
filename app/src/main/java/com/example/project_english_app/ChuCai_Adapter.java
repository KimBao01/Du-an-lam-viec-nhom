package com.example.project_english_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChuCai_Adapter extends BaseAdapter {
    ArrayList<ChuCai_CorrectWord> ChuCaiList;
    Context context;
    int layout;

    public ChuCai_Adapter(ArrayList<ChuCai_CorrectWord> ChuCaiList, Context context, int layout) {
        this.ChuCaiList = ChuCaiList;
        this.context = context;
        this.layout = layout;
    }


    @Override
    public int getCount() {
        return ChuCaiList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    private  class ViewHolder{
        TextView ChuCai;

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.ChuCai = (TextView) view.findViewById(R.id.textCorrect);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        ChuCai_CorrectWord ChuCai = ChuCaiList.get(i);
        holder.ChuCai.setText(ChuCai.getChuCai());
        view.setVisibility(View.VISIBLE);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(view.getVisibility()== View.VISIBLE)
//                {
//                    view.setVisibility(View.INVISIBLE);
//                }
//            }
//        });
        return view;
    }
//    public View DelView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder holder;
//        holder = new ViewHolder();
//        holder.ChuCai = (TextView) view.findViewById(R.id.textCorrect);
//        ChuCai_CorrectWord ChuCai = ChuCaiList.get(i);
//        holder.ChuCai.setText(ChuCai.getChuCai());
//        view.getVisibility();
//        return view;
//    }
}
