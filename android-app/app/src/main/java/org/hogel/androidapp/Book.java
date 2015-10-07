package org.hogel.androidapp;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class Book {
    @SerializedName("id")
    private long id;

    @SerializedName("title")
    private String title;

    @SerializedName("created_at")
    private Date createdAt;

    @SerializedName("updated_at")
    private Date updatedAt;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
