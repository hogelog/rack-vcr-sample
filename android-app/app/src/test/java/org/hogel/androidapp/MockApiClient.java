package org.hogel.androidapp;

import com.squareup.okhttp.Request;

public class MockApiClient extends ApiClient {
    private final String cassette;

    public MockApiClient(String url, String cassette) {
        super(url);
        this.cassette = cassette;
    }

    @Override
    protected Request createRequest(String path) {
        return new Request.Builder()
            .url(getUrl() + path)
            .get()
            .addHeader("X_VCR_CASSETTE", cassette)
            .build();
    }
}
