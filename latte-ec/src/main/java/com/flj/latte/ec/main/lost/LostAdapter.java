package com.flj.latte.ec.main.lost;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.diabin.latte.ec.R;
import com.flj.latte.ec.main.found.FoundGood;

import java.util.List;

class LostAdapter extends BaseAdapter {

    private List<LostGood> list;
    private Context context;

    public LostAdapter(List<LostGood> list_FoundGood, Context context) {
        this.list = list_FoundGood;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoundViewHolder foundViewHolder;

        if (convertView==null){
            convertView=View.inflate(context,R.layout.delegate_found_item,null);
            foundViewHolder=new FoundViewHolder();
            foundViewHolder.tv_found_title=convertView.findViewById(R.id.tv_found_title);
            foundViewHolder.tv_found_desc=convertView.findViewById(R.id.tv_found_desc);
            foundViewHolder.tv_found_time=convertView.findViewById(R.id.tv_found_time);
            foundViewHolder.tv_found_num=convertView.findViewById(R.id.tv_found_num);
            convertView.setTag(foundViewHolder);
        }else{
            foundViewHolder=(FoundViewHolder)convertView.getTag();
        }

        LostGood foundobj =list.get(position);
        foundViewHolder.tv_found_title.setText(foundobj.getTitle());
        foundViewHolder.tv_found_desc.setText(foundobj.getDesc());
        foundViewHolder.tv_found_time.setText(foundobj.getCreatedAt());
        foundViewHolder.tv_found_num.setText(foundobj.getPhoneNum());
        return convertView;
    }

    class FoundViewHolder{
        TextView tv_found_title,tv_found_desc,tv_found_time,tv_found_num;
    }

}
