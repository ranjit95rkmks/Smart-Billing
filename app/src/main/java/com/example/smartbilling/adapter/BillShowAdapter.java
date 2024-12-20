package com.example.smartbilling.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.R;
import com.example.smartbilling.model.BillShowClass;

import java.util.List;

public class BillShowAdapter extends RecyclerView.Adapter<BillShowAdapter.BillHoder> {

    List<BillShowClass> billShowClasses;


    public BillShowAdapter(List<BillShowClass> context){
        this.billShowClasses = context;

    }


    @NonNull
    @Override
    public BillHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaser_item,parent,false);
        // LayoutInflater inflater = LayoutInflater.from(context);
        //  View view = inflater.inflate(R.layout.book_item, parent, false);
        return new BillShowAdapter.BillHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BillHoder holder, int position) {
        BillShowClass mydata = billShowClasses.get(position);
        holder.month.setText(mydata.getBillmonth());
        holder.year.setText(mydata.getBillyear());
        holder.amount.setText(mydata.getgTotal_taka());
        holder.status.setText(mydata.getStatus());
    }

    @Override
    public int getItemCount() {
        return billShowClasses.size();
    }

    public class BillHoder extends RecyclerView.ViewHolder {

        TextView month, year, amount, status;
        public BillHoder(@NonNull View itemView) {
            super(itemView);
            month = itemView.findViewById(R.id.bill_month);
            year = itemView.findViewById(R.id.bill_year);
            amount = itemView.findViewById(R.id.bill_amount);
            status = itemView.findViewById(R.id.status);
        }
    }
}
