package com.project.shared_card.database.entity;

import android.arch.persistence.room.PrimaryKey;

public class Groups {
    @PrimaryKey
    public long id;
    public String name;
    public String photo;
}
