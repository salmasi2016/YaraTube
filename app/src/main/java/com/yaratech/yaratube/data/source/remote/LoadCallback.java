package com.yaratech.yaratube.data.source.remote;

public interface LoadCallback<T> {

    void onLoadedData(T data);

    void noInternet();

    void onDataNotAvailable();
}