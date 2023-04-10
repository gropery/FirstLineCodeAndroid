package com.example.intentsendobject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        getNormalData();
//        getObjWithSerializable();
        getObjWithParcelable();
    }

    private void getNormalData(){
        String stringData = getIntent().getStringExtra("string_data");
        int intData = getIntent().getIntExtra("int_data", 0);
        Log.d("SecondActivity", "String Data: " + stringData);
        Log.d("SecondActivity", "int Data: " + intData);
    }

    private void getObjWithSerializable(){
        PersonSerializable person = (PersonSerializable) getIntent().getSerializableExtra("person_data");
        Log.d("SecondActivity", "Name: " + person.getName());
        Log.d("SecondActivity", "Age: " + person.getAge());
    }

    private void getObjWithParcelable(){
        PersonParcelable person = (PersonParcelable) getIntent().getParcelableExtra("person_data");
        LogUtil.d("SecondActivity", "Name: " + person.getName());
        LogUtil.d("SecondActivity", "Age: " + person.getAge());
    }
}