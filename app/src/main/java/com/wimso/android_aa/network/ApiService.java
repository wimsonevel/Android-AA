package com.wimso.android_aa.network;

import com.wimso.android_aa.model.Post;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created by Wim on 9/21/16.
 */
@Rest(rootUrl = "https://jsonplaceholder.typicode.com", converters = { GsonHttpMessageConverter.class })
public interface ApiService {

    @Get("/posts/{id}")
    Post getPost(@Path String id);

}
