package org.hogel.androidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ApiClient client;

    private ArrayList<Book> books;

    private LinearLayout booksContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        books = new ArrayList<>();

        booksContainer = (LinearLayout) findViewById(R.id.books_container);

        ApiClient
            .getBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<Book>>() {
                @Override
                public void call(List<Book> books) {
                    MainActivity.this.books.addAll(books);
                    updateView();
                }
            }, new Action1<Throwable>() {
                @Override
                public void call(Throwable throwable) {
                    Toast.makeText(MainActivity.this, "unknown response!", Toast.LENGTH_LONG).show();
                }
            });
    }

    private void updateView() {
        booksContainer.removeAllViews();
        for (Book book : books) {
            TextView textView = new TextView(this);
            textView.setText(book.getTitle());
            booksContainer.addView(textView);
        }
    }
}
