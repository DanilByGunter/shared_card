package com.project.shared_card.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.project.shared_card.model.SignUpOfUser;

@Entity(tableName = "users")
public class UsersEntity {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String photo;
    private long groupId;

    public static UsersEntity fromSignUpOfUser(SignUpOfUser user) {
        UsersEntity entity = new UsersEntity(0,user.getName(), user.getPhoto(), 0);
        return entity;
    }
    public static SignUpOfUser toSignUpOfUser(UsersEntity entity){
        SignUpOfUser user = new SignUpOfUser(entity.getName(), entity.getPhoto());
        return user;
    }
    public UsersEntity(long id, String name, String photo, long groupId) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.groupId = groupId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
}
