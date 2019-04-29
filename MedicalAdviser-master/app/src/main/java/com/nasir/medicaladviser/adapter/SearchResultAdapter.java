package com.nasir.medicaladviser.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.nasir.medicaladviser.R;
import com.nasir.medicaladviser.model.SearchResultItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultHolder>{

    private List<SearchResultItem> listData;
    private LayoutInflater inflater;
    private SearchResultItemClickCallback searchResultItemClickCallback;




    public interface SearchResultItemClickCallback{
        void onItemClick(int p,View view);

    }

    public void setSearchResultItemClickCallback(final SearchResultItemClickCallback searchResultItemClickCallback){
        this.searchResultItemClickCallback =searchResultItemClickCallback;
    }

    public SearchResultAdapter(List<SearchResultItem> listData, Context c) {
        this.inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    @Override
    public SearchResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_f_search_result,parent,false);
        return new SearchResultHolder(view);
    }


    @Override
    public void onBindViewHolder(SearchResultHolder holder, int position) {
        final SearchResultItem item = listData.get(position);

        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.test_price.setText(item.getPrice());
        holder.delivery_time.setText(item.getDelivery_time());
    }


    @Override
    public int getItemCount() {
        return listData.size();
    }


    class SearchResultHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView name;
        private TextView address;
        private TextView test_price;
        private TextView delivery_time;


        private View container;

        public SearchResultHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            test_price = itemView.findViewById(R.id.price);
            delivery_time = itemView.findViewById(R.id.delivery_in);


            container = itemView.findViewById(R.id.cont_item_f_search_result);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            searchResultItemClickCallback.onItemClick(getAdapterPosition(),container);
        }
    }

    public void setFilter(ArrayList<SearchResultItem> filterList){

        listData = new ArrayList<>();
        listData.addAll(filterList);
        notifyDataSetChanged();

    }


}
