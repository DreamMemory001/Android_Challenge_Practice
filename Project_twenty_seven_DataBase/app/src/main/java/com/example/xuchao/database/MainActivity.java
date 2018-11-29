package com.example.xuchao.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xuchao.database.data.Book;
import com.idescout.sql.SqlScoutServer;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private Button create_db;
    private MyDataBaseHelper dbHelper;
    private Button add_data;
    private Button update_data;
    private Button delete_data;
    private Button query_data;

    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDataBaseHelper(this,"BookStore.db",null, 2);
        add_data = findViewById(R.id.add_data);
        create_db = findViewById(R.id.create_database);
        update_data = findViewById(R.id.update_data);
        delete_data = findViewById(R.id.delete_data);
        query_data = findViewById(R.id.query_data);
        create_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /// /dbHelper.getWritableDatabase();
           /**
            *
            * 换成使用LitePal进行创建
            * */
                LitePal.getDatabase();

            }
        });

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                //开始组装第一条数据
//                values.put("name","The Da Vinci Code");
//                values.put("author","Dan Brown");
//                values.put("pages",151);
//                values.put("price",153.12);
//                db.insert("Book",null, values);  //插入第一条
//                values.clear();
//                values.put("name","The fffff");
//                values.put("author","Dan Brown");
//                values.put("pages",185);
//                values.put("price",19.95);
//                db.insert("Book",null, values);  //插入第一条

                //使用litePal进行添加数据
                Book book = new Book();
                book.setName("The Da Vinci Brown");
                book.setAuthor("Dan Brown");
                book.setPages(454);
                book.setPrice(16.96);
                book.setPress("Unknow");
                book.save();


                Toast.makeText(MainActivity.this, "add succeed", Toast.LENGTH_SHORT).show();
            }
        });

        update_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
//                values.put("price", 10.99);
//                db.update("Book",values, "name = ?", new String []{"The Da Vinci Code"});
                  Book book  = new Book();
                  book.setPrice(14.95);
                  book.setPress("Anchor");
                  book.updateAll("name = ? and author = ?", "The Lost Symbol", "Dan Brown");

            }
        });

        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase()
//;
//                db.delete("Book","pages > ?", new String [] { "153" });

                //第一行代码中的所有DataSupport类中的方法都用LitePal来代替

               LitePal.deleteAll(Book.class, "price < ?", "15");
                Toast.makeText(MainActivity.this, "delete succeed！！", Toast.LENGTH_SHORT).show();
            }
        });
        //查询
        query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历cursor对象 并且进行打印
                        String name  = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));

                        Log.d(TAG, "Book name is"+ name);
                        Log.d(TAG, "book author is"+author);
                        Log.d(TAG, "book pages is"+pages);
                        Log.d(TAG, "book  price is "+price);
                    }while(cursor.moveToNext());
                }
                cursor.close();  //每次在进行查询之后都要对cursor对象进行关闭  养成好习惯*/
            //使用LitePal进行查找
                List<Book> books = LitePal.findAll(Book.class);

                for (Book book: books){
                    Log.d(TAG, "Book name is"+ book.getName());
                    Log.d(TAG, "book author is"+ book.getAuthor());
                    Log.d(TAG, "book pages is"+book.getPages());
                    Log.d(TAG, "book  price is "+ book.getPrice());
                }

            }

        });
    }
}
