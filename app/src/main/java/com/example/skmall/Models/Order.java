package com.example.skmall.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.skmall.Utils;

import java.util.ArrayList;

public class Order implements Parcelable {
    private int id;
    private ArrayList<Integer> items;
    private String address;
    private String phoneNo;
    private String zipCode;
    private String paymentMethod;
    private boolean success;
    private double totalPrice;

    public Order() {
    }

    public Order(ArrayList<Integer> items, String address, String phoneNo, String zipCode, String paymentMethod, boolean success, double totalPrice) {
        this.id = Utils.getOrder_id();
        this.items = items;
        this.address = address;
        this.phoneNo = phoneNo;
        this.zipCode = zipCode;
        this.paymentMethod = paymentMethod;
        this.success = success;
        this.totalPrice = totalPrice;
    }


    protected Order(Parcel in) {
        id = in.readInt();
        address = in.readString();
        phoneNo = in.readString();
        zipCode = in.readString();
        paymentMethod = in.readString();
        success = in.readByte() != 0;
        totalPrice = in.readDouble();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getItems() {
        return items;
    }

    public void setItems(ArrayList<Integer> items) {
        this.items = items;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", items=" + items +
                ", address='" + address + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", success=" + success +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(address);
        dest.writeString(phoneNo);
        dest.writeString(zipCode);
        dest.writeString(paymentMethod);
        dest.writeByte((byte) (success ? 1 : 0));
        dest.writeDouble(totalPrice);
    }
}
