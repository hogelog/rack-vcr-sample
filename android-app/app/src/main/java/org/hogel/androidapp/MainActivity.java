package org.hogel.androidapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    public static final String EXTRA_MOCK_VCR_CASSETTE = "mock_vcr_casette";

    final List<Book> books = new ArrayList<>();

    LinearLayout booksContainer;

    ApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        books.clear();

        booksContainer = (LinearLayout) findViewById(R.id.books_container);
        String mockVcrCassette = getIntent().getStringExtra(EXTRA_MOCK_VCR_CASSETTE);
        if (mockVcrCassette != null) {
            client = new MockApiClient(mockVcrCassette);
        } else {
            client = new ApiClient();
        }

        client.getBooks()
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
