package com.p.s.w.api;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ListJson<T> {

    private int size;

    private List<T> datas;

    public ListJson(List<T> datas) {
        setDatas(datas);
        setSize(this.datas.size());
    }

    public ListJson(T[] datas) {
        this(Arrays.asList(datas));
    }

}
