package com.anchal.hotnew.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseModel<N> implements Parcelable {

    @SerializedName("children")
    @Expose
    private N data;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("isSuccess")
    @Expose
    private String isSuccess;

    @SerializedName("pages")
    @Expose
    private String pages;

    private BaseModel(Parcel in) {
        message = in.readString();
    }

    public static final Creator<BaseModel> CREATOR = new Creator<BaseModel>() {
        @Override
        public BaseModel createFromParcel(Parcel in) {
            return new BaseModel(in);
        }

        @Override
        public BaseModel[] newArray(int size) {
            return new BaseModel[size];
        }
    };

    public N getData() {
        return data;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setData(N data) {
        this.data = data;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static Creator<BaseModel> getCREATOR() {
        return CREATOR;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
    }
}
