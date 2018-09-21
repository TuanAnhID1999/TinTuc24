package com.example.administrator.miniproject;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.miniproject.databinding.ActivityItemBinding;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHoder> {
    private ArrayList<News> data ;
    private LayoutInflater inflater;
    private  ItemViewActionCallBack callBack;
    private static OnItemClickListener listener;
    private NewsAdapter adapter;




    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public NewsAdapter(ArrayList<News> data, Context context) {
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setCallBack(ItemViewActionCallBack callBack) {
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ActivityItemBinding binding = DataBindingUtil.inflate(inflater,R.layout.activity_item,viewGroup,false);
        return new ViewHoder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder viewHoder, final int i) {
        viewHoder.binData(data.get(i));
        viewHoder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callBack != null) {
                    callBack.onClick(i);
                }
            }
        });
        viewHoder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (callBack != null) {
                    callBack.onLongClick(i);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        ActivityItemBinding binding;
        public ViewHoder(ActivityItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void binData(News news){
            binding.txtTitle.setText(news.getTitle());
            binding.txtDesc.setText(news.getDesc());
            binding.txtDate.setText(news.getPubDate());
            Glide.with(itemView.getContext())
                    .load(news.getImage())
                    .into(binding.imgAv);
        }




    }
    }
    interface ItemViewActionCallBack {
        void onClick(int position);

        void onLongClick(int positon);
    }


