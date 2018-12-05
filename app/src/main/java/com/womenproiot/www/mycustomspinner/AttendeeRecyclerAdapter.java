package com.womenproiot.www.mycustomspinner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

class AttendeeRecyclerAdapter extends RecyclerView.Adapter<AttendeeRecyclerAdapter.ViewHolder> {

    ArrayList<AttendeeDto> arrayList = null;

    public AttendeeRecyclerAdapter(ArrayList<AttendeeDto> list) {
        this.arrayList = list;
        notifyDataSetChanged();
        Log.e("김정아","리사이클러 생성자");
    }


    @NonNull
    @Override
    public AttendeeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.e("김정아","리사이클러 onCreateViewHolder");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendeeRecyclerAdapter.ViewHolder viewHolder, int i) {
        Log.e("김정아","리사이클러 onBindViewHolder");
        viewHolder.textViewName.setText(arrayList.get(i).name);
        viewHolder.textViewAddr.setText(arrayList.get(i).roadAddress);
        viewHolder.textViewFrSeq.setText(arrayList.get(i).frSeq);
    }

    @Override
    public int getItemCount() {
        Log.e("김정아","리사이클러 getItemCount");
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewFrSeq,textViewName,textViewAddr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.e("김정아","리사이클러 ViewHolder 생성자");
            textViewFrSeq = itemView.findViewById(R.id.textViewFrSeq);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewAddr = itemView.findViewById(R.id.textViewAddr);
        }
    }
}
