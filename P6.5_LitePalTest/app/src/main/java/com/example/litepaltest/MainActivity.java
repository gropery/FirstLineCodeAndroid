package com.example.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 创建数据库(创建新表/更改新表栏目, 注意不是内容)
        setContentView(R.layout.activity_main);
        Button createDatabase = (Button) findViewById(R.id.create_databese);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.getDatabase();
            }
        });

        // 添加数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("The Da Vinci Code");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("UnKnow");
                book.save();
            }
        });

        // 更新数据-方式1
        Button updateData1 = (Button) findViewById(R.id.update_data_method1);
        updateData1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setName("The Lost Symbol");
                book.setAuthor("Dan Brown");
                book.setPages(510);
                book.setPrice(19.95);
                book.setPress("UnKnow");
                book.save();
                book.setPrice(10.99);
                book.save();
            }
        });

        // 更新数据-方式2
        Button updateData = (Button) findViewById(R.id.update_data_method2);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setPrice(14.95);
                book.setPress("Anchor");
                book.updateAll("name = ? and author = ? ","The Lost Symbol","Dan Brown");
            }
        });

        // 删除数据
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.deleteAll(Book.class,"price < ? ","15");
            }
        });

        // 查询数据
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book: books) {
                    Log.d("MainActivity","book name is " + book.getName());
                    Log.d("MainActivity","book author is " + book.getAuthor());
                    Log.d("MainActivity","book pages is " + book.getPages());
                    Log.d("MainActivity","book price is " + book.getPrice());
                    Log.d("MainActivity","book press is " + book.getPress());
                }
            }
        });
    }
}