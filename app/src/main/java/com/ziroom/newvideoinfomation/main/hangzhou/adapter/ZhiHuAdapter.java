package com.ziroom.newvideoinfomation.main.hangzhou.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ziroom.newvideoinfomation.R;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShangHaiDetailBean;
import com.ziroom.newvideoinfomation.main.shanghai.dto.ShanghaiBean;
import com.ziroom.newvideoinfomation.main.shanghai.view.ShanghaiDetailActivity;

import java.util.ArrayList;

/**
 * Author:关震
 * Date:2020/4/6 12:27
 * Description:ShanghaiAdapter
 **/
public class ZhiHuAdapter extends RecyclerView.Adapter {
    private final ArrayList<ShangHaiDetailBean.XiaoHuaBean> mData;

    public ZhiHuAdapter(ArrayList<ShangHaiDetailBean.XiaoHuaBean> data) {
        mData = data;
    }

    //创建View 然后进行缓存
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shanghai_fragment, null);
        ShanghaiViewHolder viewHolder = new ShanghaiViewHolder(inflate);
        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ShangHaiDetailBean.XiaoHuaBean xiaoHuaBean = mData.get(position);
        ((ShanghaiViewHolder) holder).mTv.setText(xiaoHuaBean.content);
        ((ShanghaiViewHolder) holder).mIv.setVisibility(View.GONE);
    }

    //条目的数量
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //缓存View 内存友好设计
    public class ShanghaiViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIv;
        public TextView mTv;

        public ShanghaiViewHolder(@NonNull View itemView) {
            super(itemView);
            mIv = itemView.findViewById(R.id.item_shanghai_iv);
            mTv = itemView.findViewById(R.id.item_shanghai_tv);
        }
    }
}
