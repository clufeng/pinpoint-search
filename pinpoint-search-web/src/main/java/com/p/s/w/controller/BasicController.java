package com.p.s.w.controller;


import com.p.s.w.api.ApiResponse;
import com.p.s.w.api.ApiStatus;
import com.p.s.w.api.ListJson;

import java.util.Collections;
import java.util.List;

public class BasicController {

    protected <T> ApiResponse<T> success(T t) {
        return ApiResponse.of(ApiStatus.OK, t);
    }

    protected <T> ApiResponse<ListJson<T>> successListJson(List<T> t) {
        return ApiResponse.of(ApiStatus.OK, new ListJson<>(t));
    }

    protected  <T> ApiResponse<ListJson<T>> successEmptyListJson() {
        return ApiResponse.of(ApiStatus.OK, new ListJson<>(Collections.emptyList()));
    }

}
