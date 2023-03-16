package com.example.user.infra.adapters;

public interface Queue {
    void publisher(String name, byte[] data) throws Exception;
}
