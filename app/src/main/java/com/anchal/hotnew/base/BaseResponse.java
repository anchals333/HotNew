package com.anchal.hotnew.base;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse<N> implements Parcelable {

    @SerializedName("data")
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

    private BaseResponse(Parcel in) {
        message = in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
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

    public static Creator<BaseResponse> getCREATOR() {
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
