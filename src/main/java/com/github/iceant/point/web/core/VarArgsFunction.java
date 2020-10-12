package com.github.iceant.point.web.core;

@FunctionalInterface
public interface VarArgsFunction<T, U>{
    U apply(T... args) throws Exception;
}