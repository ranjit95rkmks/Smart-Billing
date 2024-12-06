package com.example.smartbilling.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookData {
   /* @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("fname")
    @Expose
    String fname;

    @SerializedName("mname")
    @Expose
    String mname;

    @SerializedName("book")
    @Expose
    String book;


    @SerializedName("account")
    @Expose
    String account;

    @SerializedName("phone")
    @Expose
    String phone;

    @SerializedName("tarrif")
    @Expose
    String tarrif;

    @SerializedName("demand")
    @Expose
    String demand;

    @SerializedName("status")
    @Expose
    String status;
*/

    String id, name, fname, phone, book, account, tarrif, demand, status, mname, address;

    public BookData(String id, String name, String fname, String mname, String book, String account, String phone, String tarrif, String demand, String status, String address) {

        this.id = id;
        this.name = name;
        this.fname = fname;
        this.phone = phone;
        this.book = book;
        this.account = account;
        this.tarrif = tarrif;
        this.demand = demand;
        this.status = status;
        this.mname = mname;
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getBook() {
        return book;
    }

    public String getAccount() {
        return account;
    }

    public String getPhone() {
        return phone;
    }

    public String getTarrif() {
        return tarrif;
    }

    public String getDemand() {
        return demand;
    }

    public String getStatus() {
        return status;
    }


}
