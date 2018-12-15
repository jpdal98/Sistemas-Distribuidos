package com.company.Factory;

public interface Factory<T> {
    T create (String type);
}
