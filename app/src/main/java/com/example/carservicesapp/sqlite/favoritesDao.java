package com.example.carservicesapp.sqlite;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface favoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addItem(FavoriteItemsDB item);

    @Query("select * from FavoriteItemsDB")
    public List<FavoriteItemsDB> getItems();

    @Query("select * from FavoriteItemsDB where id = :id1")
    public FavoriteItemsDB getItem(String id1);

    @Query("select * from FavoriteItemsDB where name = :name")
    public FavoriteItemsDB getByName(String name);

    @Query("select * from FavoriteItemsDB where name = :name")
    public FavoriteItemsDB getItemByName(String name);

    @Delete
    public void deleteItem(FavoriteItemsDB item);

    @Delete
    public void deleteAll(List<FavoriteItemsDB> items);
}
