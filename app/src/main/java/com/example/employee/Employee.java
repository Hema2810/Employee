package com.example.employee;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Employee implements Parcelable{


    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    @PrimaryKey
    @NonNull
    private String mName;
    private int mAge;
    private String mAvatar;


    public Employee(String name, int age, String avatar) {

        this.mName = name;
        this.mAge = age;
        this.mAvatar = avatar;
    }

    private Employee(Parcel in) {
        mName= in.readString();
        mAge = in.readInt();
        mAvatar = in.readString();

    }

    public String getName() { return mName; }

    public int getAge() {
        return mAge;
    }

    public String getAvatar() { return mAvatar; }

    public void setAge(int age) {  mAge = age; }

    public void setName(String name) {  mName = name; }

    public void setAvatar(String avatar) {  mName = mAvatar; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mAge);
        dest.writeString(mAvatar);

    }
}