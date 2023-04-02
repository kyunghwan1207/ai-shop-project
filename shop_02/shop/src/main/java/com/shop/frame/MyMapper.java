package com.shop.frame;

import java.util.List;

public interface MyMapper<K, V> {
    // CRUD
    void insert(V v) throws Exception;
    void update(V v) throws Exception;
    void delete(K k) throws Exception;
    V select(K k) throws Exception;
    List<V> selectall() throws Exception;
}
