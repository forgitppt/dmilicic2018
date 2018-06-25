package com.alex.readabc.letters.myapplication.presentation.ui.fragments;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alex.readabc.letters.myapplication.R;
import com.alex.readabc.letters.myapplication.domain.model.Contact;

import java.util.List;

/**
 * Created on 6/26/18.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {


    private List<Contact> mItems;
    private Context mContext;

    public void setItems(List<Contact> items) {
        this.mItems = items;
    }

    public ContactsAdapter(Context context, List<Contact> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @NonNull
    @Override
    public ContactsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.fragment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {
        Contact item = mItems.get(position);

        try {
            holder.txt.setText(item.getName());
        } catch (Exception e) {
        }
    }

    @Override
    public int getItemCount() {
        if(mItems == null)
            return 0;
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt;
        public Button btn;

        public ViewHolder(View itemView) {
            super(itemView);

            txt = (TextView) itemView.findViewById(R.id.txt1);
            btn = (Button) itemView.findViewById(R.id.submitBtn);
        }
    }


}
