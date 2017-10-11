package com.clkj.insurance.interf;

import com.clkj.insurance.throwable.ExceptionEngine;

public interface BaseViewI {
    void toast(String message);
    void showConntectError(ExceptionEngine.ApiException e);
}