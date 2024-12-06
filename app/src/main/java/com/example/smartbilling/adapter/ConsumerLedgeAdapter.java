package com.example.smartbilling.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.R;
import com.example.smartbilling.model.BookData;

public class ConsumerLedgeAdapter extends RecyclerView.Adapter<ConsumerLedgeAdapter.MyViewHolder>{
    @NonNull
    @Override
    public ConsumerLedgeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumerLedgeAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, phone, account, village;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
           // account = itemView.findViewById(R.id.account);
            village = itemView.findViewById(R.id.village);

        }

        public void bindViews(BookData bookData) {
            name.setText(bookData.getBook());
            //phone.setText(MessageFormat.format("{0},{1}", bookData.getName(), bookData.getAddress()));
           // phone.setText(bookData.getAddress());
            account.setText( bookData.getAccount());
            village.setText(bookData.getName());
        }
    }
}
