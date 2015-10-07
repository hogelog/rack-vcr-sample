package org.hogel.androidapp;

import com.squareup.okhttp.Request;

public class MockApiClient extends ApiClient {
    private String cassette;

    public MockApiClient(String cassette) {
        this.cassette = cassette;
    }

    @Override
    protected Request createRequest(String path) {
        return new Request.Builder()
            .url("http://10.0.3.2:9292" + path)
            .get()
            .addHeader("X_VCR_CASSETTE", cassette)
            .build();
    }
}
