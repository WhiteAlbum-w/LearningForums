package com.xiayang.learningforums.bean;

/**
 * 统一网络请求响应类
 * <p>
 * Date: 2020/2/17
 * Author: aaronzzxup@gmail.com
 */
public final class Response<T> {

    public int errorCode;
    public String errorMsg;
    public T data;
}
