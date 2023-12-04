package com.example.alga_works_1.aula1.core.contracts;

public interface EventObserver<T> {
    public void onEvent(T event);
}