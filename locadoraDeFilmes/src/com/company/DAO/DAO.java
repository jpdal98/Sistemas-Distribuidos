package com.company.DAO;

import java.util.ArrayList;

public interface DAO<T> {

    ArrayList<T> getAll();
    String add(T object);
    String remove(int id);
    T getObject(int id);
    String update(String field, String value, int id);
}
