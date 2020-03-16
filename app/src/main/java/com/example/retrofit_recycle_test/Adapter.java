package com.example.retrofit_recycle_test;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context context;
    private List<Bean.DataBean> dataBeanList = new ArrayList<>();
    public Adapter(Context context,List<Bean.DataBean>dataBeanList){
        this.context = context;
        this.dataBeanList = dataBeanList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView simple_DraweeView;
        TextView item_Text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            simple_DraweeView = (SimpleDraweeView)itemView.findViewById(R.id.simple_DraweeView);
            item_Text = (TextView)itemView.findViewById(R.id.item_Text);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.simple_DraweeView.setImageURI(dataBeanList.get(position).getPic());
        Log.d("zsbenn", "onBindViewHolder: "+dataBeanList.get(position).getPic());
        holder.item_Text.setText(dataBeanList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }
}
