package com.example.demo.service;

public interface TempoService<T>  {
    T save (T t);

    void deleteById(Integer id);

    void deleteAll();
}
