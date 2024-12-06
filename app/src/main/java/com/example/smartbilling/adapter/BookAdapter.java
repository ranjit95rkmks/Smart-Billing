package com.example.smartbilling.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartbilling.R;
import com.example.smartbilling.model.BookData;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context context;
    private List<BookData> bookDataList;

    private OnItemClickListener mListener;


    public BookAdapter(List<BookData> context){

        this.bookDataList = context;
    }

//    @SuppressLint("NotifyDataSetChanged")
//    public void addModel(BookData bookData){
//        bookDataList.add(bookData);
//        notifyDataSetChanged();
//    }

//    @SuppressLint("NotifyDataSetChanged")
//    public void clear(){
//        bookDataList.clear();
//        notifyDataSetChanged();;
//    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item,parent,false);
       // LayoutInflater inflater = LayoutInflater.from(context);
      //  View view = inflater.inflate(R.layout.book_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BookData bookData = bookDataList.get(position);
        //holder.ame.setText(bookData.getBook());
        //phone.setText(MessageFormat.format("{0},{1}", bookData.getName(), bookData.getAddress()));
        //holder.phone.setText(bookData.getPhone());
        holder.account.setText( bookData.getBook()+"/"+bookData.getAccount());
       // holder.village.setText(bookData.getName());
        //holder.bindViews(bookDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookDataList.size();
    }




    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

       TextView name, phone, account, village;

        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
           // name = itemView.findViewById(R.id.name);
           // phone = itemView.findViewById(R.id.phone);
            account = itemView.findViewById(R.id.n_book);
          //  village = itemView.findViewById(R.id.village);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }
}
