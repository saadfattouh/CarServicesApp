package com.example.carservicesapp.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carservicesapp.R;
import com.example.carservicesapp.model.Service;
import com.example.carservicesapp.sqlite.CartItemsDB;
import com.example.carservicesapp.sqlite.FavoriteItemsDB;
import com.example.carservicesapp.sqlite.Myappdatabas;

import java.util.ArrayList;
import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder>{


    Context context;
    private List<Service> services;
    Myappdatabas myappdatabas;

    // RecyclerView recyclerView;
    public ServicesAdapter(Context context, ArrayList<Service> services) {
        this.context = context;
        this.services = services;
        myappdatabas = Myappdatabas.getDatabase(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.service_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Service service = services.get(position);

        holder.title.setText(service.getTitle());

        holder.image.setImageDrawable(context.getResources().getDrawable(service.getImageId()));


        holder.itemView.setOnClickListener(v -> {
            LayoutInflater factory = LayoutInflater.from(context);
            final View view = factory.inflate(R.layout.add_order_dialog, null);
            final AlertDialog addOrderDialog = new AlertDialog.Builder(context).create();
            addOrderDialog.setView(view);


            TextView title = view.findViewById(R.id.title);
            EditText mDetails = view.findViewById(R.id.details);
            Button save = view.findViewById(R.id.save);
            Button fav = view.findViewById(R.id.add_favorite_btn);

            title.setText(service.getTitle());


            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String details = mDetails.getText().toString();
                    if(TextUtils.isEmpty(details)){
                        Toast.makeText(context, context.getResources().getString(R.string.details_empty_message), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        CartItemsDB item = myappdatabas.myDao().getItemByName(service.getTitle());
                        if(item != null){
                            Toast.makeText(context, "you have a "+service.getTitle()+" already in your orders list!", Toast.LENGTH_SHORT).show();
                        }else{
                            addToCart(service, details);
                        }
                        addOrderDialog.dismiss();

                    }
                }
            });

            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String details = mDetails.getText().toString();
                    if(TextUtils.isEmpty(details)){
                        Toast.makeText(context, context.getResources().getString(R.string.details_empty_message), Toast.LENGTH_SHORT).show();
                    }
                    else {
                        FavoriteItemsDB item = myappdatabas.favoritesDao().getItemByName(service.getTitle());
                        if(item != null){
                            Toast.makeText(context, "you have a "+service.getTitle()+" already in your favorites list!", Toast.LENGTH_SHORT).show();
                        }else{
                            addToFavorites(service, details);
                        }
                        addOrderDialog.dismiss();

                    }
                }
            });
            addOrderDialog.show();
        });

    }


    private void addToCart(Service service, String details) {
        CartItemsDB item = new CartItemsDB();
        item.setCategory(service.getTitle());
        item.setDetails(details);
        item.setPrice(service.getPrice());
        myappdatabas.myDao().addItem(item);
    }
    private void addToFavorites(Service service, String details) {
        FavoriteItemsDB item = new FavoriteItemsDB();
        item.setCategory(service.getTitle());
        item.setDetails(details);
        item.setPrice(service.getPrice());
        myappdatabas.favoritesDao().addItem(item);
    }


    @Override
    public int getItemCount() {
        return services.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image ;

        public ViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.title);
            this.image = itemView.findViewById(R.id.image);
        }
    }





}
