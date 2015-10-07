package org.hogel.androidapp;

import android.content.Intent;
import android.test.ActivityUnitTestCase;
import android.test.suitebuilder.annotation.MediumTest;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {
    private Intent intent;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @MediumTest
    public void testFetchFromApi() throws Exception {
        intent = new Intent(getInstrumentation() .getTargetContext(), MainActivity.class);
        intent.putExtra(MainActivity.EXTRA_MOCK_VCR_CASSETTE, "books_index");
        MainActivity activity = startActivity(intent, null, null);

        // waiting for mock api response
        Thread.sleep(50);

        assertEquals(activity.books.size(), 2);
    }
}
