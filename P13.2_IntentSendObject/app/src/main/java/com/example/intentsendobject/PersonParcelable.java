package com.example.intentsendobject;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PersonParcelable implements Parcelable {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);   // 写出name
        parcel.writeInt(age);       // 写出age
    }

    public static final Parcelable.Creator<PersonParcelable> CREATOR = new Parcelable.Creator<PersonParcelable>(){
        @Override
        public PersonParcelable createFromParcel(Parcel parcel) {
            PersonParcelable person = new PersonParcelable();
            person.name = parcel.readString(); // 读取 name
            person.age = parcel.readInt();     // 读取 age
            return person;
        }

        @Override
        public PersonParcelable[] newArray(int i) {
            return new PersonParcelable[i];
        }
    };
}
