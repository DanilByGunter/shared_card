package com.project.shared_card.database.entity.group;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface GroupDao {
    @Insert
    void createGroup(GroupEntity entity);

    @Query("SELECT * FROM `group`")
    LiveData<List<GroupEntity>>  getAllGroup();
}
