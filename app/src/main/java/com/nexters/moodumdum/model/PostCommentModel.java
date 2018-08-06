package com.nexters.moodumdum.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by User on 2018-02-22.
 */

public class PostCommentModel implements Serializable {
    @SerializedName( "user" ) private String user;
    @SerializedName( "board_id" ) private BigInteger board_id;
    @SerializedName( "description" ) private String description;
    @SerializedName( "color") private String color;
    @SerializedName( "backImagUrl") private String backImagUrl;
    @SerializedName( "likeCount") private int likeCount;


    public BigInteger getBoard_id() {
        return board_id;
    }

    public void setBoard_id(BigInteger board_id) {
        this.board_id = board_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackImagUrl() {
        return backImagUrl;
    }

    public void setBackImagUrl(String backImagUrl) {
        this.backImagUrl = backImagUrl;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}
