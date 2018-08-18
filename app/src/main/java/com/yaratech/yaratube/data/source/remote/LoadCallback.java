package com.yaratech.yaratube.data.source.remote;

import com.yaratech.yaratube.data.model.Category;
import com.yaratech.yaratube.data.model.HeaderItem;
import com.yaratech.yaratube.data.model.HomeItem;
import com.yaratech.yaratube.data.model.Product;

import java.util.ArrayList;

public interface LoadCallback<T> {

    void onLoadedData(T data);

    void noInternet();

    void onDataNotAvailable();
}