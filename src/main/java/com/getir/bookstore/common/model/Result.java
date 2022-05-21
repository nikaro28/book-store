package com.getir.bookstore.common.model;

import org.springframework.http.HttpStatus;

public class Result<T> {

    private final HttpStatus status;
    private final T object;
    private final boolean hasError;
    private final String errorMessage;

    private Result(HttpStatus status, T obj) {
        this(status, obj, false, null);
    }

    private Result(HttpStatus status, T obj, boolean hasError, String errorMessage) {
        this.status = status;
        this.object = obj;
        this.hasError = hasError;
        this.errorMessage = errorMessage;
    }

    public static Result of(HttpStatus status, Object obj) {
        return new Result(status, obj);
    }

    public static Result withError(HttpStatus status, String errorMessage) {
        return new Result(status, null, true, errorMessage);
    }

    public static Result success(Object obj) {
        return new Result(HttpStatus.OK, obj);
    }

    public static Result success() {
        return success(null);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public T getObject() {
        return object;
    }

    public boolean isHasError() {
        return hasError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
