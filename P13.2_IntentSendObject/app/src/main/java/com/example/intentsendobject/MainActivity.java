package com.example.intentsendobject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartActivity = (Button) findViewById(R.id.start_activity);
        btnStartActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendNormalData();
//                sendObjWithSerializable();
                sendObjWithParcelable();
            }
        });
    }

    private void sendNormalData(){
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("string_data", "hello");
        intent.putExtra("int_data", 100);
        startActivity(intent);
    }
    private void sendObjWithSerializable(){
        PersonSerializable person = new PersonSerializable();
        person.setName("Tom");
        person.setAge(20);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("person_data", person);
        startActivity(intent);
    }

    private void sendObjWithParcelable(){
        PersonParcelable person = new PersonParcelable();
        person.setName("Jerry");
        person.setAge(28);
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("person_data", person);
        startActivity(intent);
    }
}