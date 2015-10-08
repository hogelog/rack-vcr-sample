package org.hogel.androidapp;

import org.junit.Test;
import rx.functions.Action1;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ApiClientTest extends AbstractTestCase {
    ApiClient apiClient;

    @Test
    @VcrCassette("books_index")
    public void testBookIndex() {
        apiClient.getBooks().subscribe(new Action1<List<Book>>() {
            @Override
            public void call(List<Book> books) {
                assertThat(books.size(), is(2));
                assertThat(books.get(0).getTitle(), is("K&R"));
                assertThat(books.get(1).getTitle(), is("Camel"));
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                assertTrue(false);
            }
        });
    }
}
