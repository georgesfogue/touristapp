package com.example.georges.tourapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Georges on 08/04/2017.
 */

public class user implements Parcelable {
    /**
     *
     */

    private String    email;
    private String    motDePasse;


    public user(String Email, String MotDePasse) {
        super();
        this.email = Email;
        this.motDePasse = MotDePasse;
    }

    protected user(Parcel in) {
        this.email = in.readString();
        this.motDePasse = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(motDePasse);
    }

    public static final Parcelable.Creator<user> CREATOR = new Creator<user>() {
        @Override
        public user createFromParcel(Parcel source) {
            return new user(source);
        }

        @Override
        public user[] newArray(int size) {
            return new user[size];
        }
    };

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public static Parcelable.Creator<user> getCreator() {
        return CREATOR;
    }

}
