package com.ll.example.app_2022_12_19;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RsData<T> {
    private String resultCode;
    private String msg;
    private T data;
}
