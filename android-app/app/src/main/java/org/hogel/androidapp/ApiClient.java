package org.hogel.androidapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import rx.Observable;
import rx.Subscriber;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {
    public static class UnknownResponseError extends Exception {
    }

    private static final Gson GSON = new Gson();
    private static final OkHttpClient client = new OkHttpClient();

    private static final Type TYPE_BOOK_LIST = new TypeToken<ArrayList<Book>>(){}.getType();

    public Observable<List<Book>> getBooks() {
        final Request request = createRequest("/books");
        return Observable.create(new Observable.OnSubscribe<List<Book>>() {
            @Override
            public void call(Subscriber<? super List<Book>> subscriber) {
                try {
                    Response response = client.newCall(request).execute();
                    if (response.code() == 200) {
                        List<Book> books = GSON.fromJson(response.body().string(), TYPE_BOOK_LIST);
                        subscriber.onNext(books);
                        subscriber.onCompleted();
                    } else {
                        subscriber.onError(new UnknownResponseError());
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

    protected Request createRequest(String path) {
        return new Request.Builder().url("http://10.0.3.2:9292" + path).get().build();
    }
}
