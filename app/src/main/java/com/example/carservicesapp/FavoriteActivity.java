package com.example.carservicesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.carservicesapp.adapters.FavoritesAdapter;
import com.example.carservicesapp.sqlite.FavoriteItemsDB;
import com.example.carservicesapp.sqlite.Myappdatabas;

import java.util.List;

public class FavoriteActivity extends AppCompatActivity {

    RecyclerView mList;
    FavoritesAdapter mAdapter;
    List<FavoriteItemsDB> items;

    Myappdatabas myappdatabas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        myappdatabas = Myappdatabas.getDatabase(this);

        mList = findViewById(R.id.fav_list);

        items = myappdatabas.favoritesDao().getItems();
        mAdapter = new FavoritesAdapter(this, items);

        mList.setAdapter(mAdapter);


    }
}