package com.example.xingzuoweb.luckfrag;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xingzuoweb.R;
import com.example.xingzuoweb.bean.StarBean;
import com.example.xingzuoweb.utils.AssetsUtils;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class LuckBaseAdapter extends BaseAdapter {
    private final Map<String, Bitmap> contentlogoImgMap;
    Context context;
    List<StarBean.StarinfoBean> mDatas;

    public LuckBaseAdapter(Context context, List<StarBean.StarinfoBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        contentlogoImgMap = AssetsUtils.getContentlogoImgMap();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_luck_gv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        StarBean.StarinfoBean bean = mDatas.get(position);
        holder.starTv.setText(bean.getName());
        Bitmap bitmap = contentlogoImgMap.get(bean.getLogoname());
        holder.starIv.setImageBitmap(bitmap);
        return convertView;
    }

    class ViewHolder{
       CircleImageView starIv;
       TextView starTv;
       public ViewHolder(View view){
           starIv = view.findViewById(R.id.item_luck_iv);
           starTv = view.findViewById(R.id.item_luck_tv);
       }
    }
}