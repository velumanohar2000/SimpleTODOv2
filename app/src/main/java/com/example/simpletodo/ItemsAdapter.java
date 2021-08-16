package com.example.simpletodo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//#2 Adapter responsible for displaying data from the the model into a recycler view
//ItemsAdapter again extend to RecyclerView and and as a parameter use ViewHolder
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    public interface OnClickListener {
        void onItemClicked(int position);
    }
    public interface OnLongClickListener {
        void onItemLongClick(int position);
    }
    // #3 MainActivity.java is where we will get the data
    //it will construct the itemAdapter
    List<String> items;
    OnLongClickListener LongClickListener;
    OnClickListener clickListener;
    public ItemsAdapter(List<String> items, OnLongClickListener LongClickListener, OnClickListener clickListener) {

        this.items = items;
        this.LongClickListener = LongClickListener;
        this.clickListener = clickListener;
    }




    @NonNull
    @Override
    // creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate a view
        //LayoutInflator.from method to inflate view --> parameter use "parent" from onCreateViewHolder
        // --> next pass in xml file for the view that you are creating(android built in file)
        // --> root will be "parent" --> false because recylerView will not be attaching to root
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a view holder and return it
        return new ViewHolder(todoView);
    }



    @Override
    // taking data and putting it in view holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Grab the item at the position
        String item = items.get(position);
        //Bind the item into the specified ViewHolder
        holder.bind(item);

    }



    @Override
    //counting how many items in data
        public int getItemCount() {
        return items.size();
    }



    //#1 container to provide easy access to views that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }
        //Update view inside of the view holder with this data(String item)
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //remove the item from the recycler view
                    LongClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
