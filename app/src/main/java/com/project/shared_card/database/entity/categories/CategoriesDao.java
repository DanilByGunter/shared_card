package com.project.shared_card.database.entity.categories;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.shared_card.retrofit.model.Category;

import java.util.List;

import retrofit2.http.GET;

@Dao
public interface CategoriesDao {
    @Insert
    void insertCategory(List<CategoriesEntity> categoriesEntity);
    @Query("Select * from category where status=1")
    LiveData<List<CategoriesEntity>> getForProduct();
    @Query("Select * from category where status=0")
    LiveData<List<CategoriesEntity>> getForTarget();
}
