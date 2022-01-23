package com.example.carservicesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservicesapp.R;
import com.example.carservicesapp.sqlite.FavoriteItemsDB;
import com.example.carservicesapp.sqlite.Myappdatabas;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{


    Context context;
    private List<FavoriteItemsDB> items;
    Myappdatabas myappdatabas;

    // RecyclerView recyclerView;
    public FavoritesAdapter(Context context, List<FavoriteItemsDB> items) {
        this.context = context;
        this.items = items;
        myappdatabas = Myappdatabas.getDatabase(context);
    }

    public List<FavoriteItemsDB> getItems() {
        return items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.cart_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FavoriteItemsDB item = items.get(position);

        holder.title.setText(item.getCategory());

        holder.price.setText(String.valueOf(item.getPrice()) +" " +context.getResources().getString(R.string.price_unit));

        holder.itemView.setOnLongClickListener(v -> {

            LayoutInflater factory = LayoutInflater.from(context);
            final View view = factory.inflate(R.layout.delete_confirmation_dialog, null);
            final AlertDialog deleteProductDialog = new AlertDialog.Builder(context).create();
            deleteProductDialog.setView(view);

            TextView yes = view.findViewById(R.id.yes_btn);
            TextView no = view.findViewById(R.id.no_btn);


            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //to see changes immediately
                    removeOrder(position);
                    //when done dismiss;
                    deleteProductDialog.dismiss();

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteProductDialog.dismiss();
                }
            });
            deleteProductDialog.show();

            return true;
        });

    }

    private void removeOrder(int index) {
        myappdatabas.favoritesDao().deleteItem(items.get(index));
        items.remove(index);
        notifyItemRemoved(index);
    }




    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.price = itemView.findViewById(R.id.price);
        }
    }





}
