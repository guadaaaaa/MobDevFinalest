package com.example.finalproject;

import java.util.HashMap;

public class Session {
    private HashMap<String,Object> map;
    private static Session instance;
    private Session(){
        map = new HashMap<>();
    }
    protected void put(String key, Object value){ //insert entry to hash table
        map.put(key,value);
    }
    protected void clear(){ //remove all entries in hash table, para sa logout ni
        map.clear();
    }
    protected Object get(String key){ //get value using key
        return map.get(key);
    }
    public static Session getInstance(){ //pang-singleton
        if(instance == null){
            instance = new Session();
        }
        return instance;
    }
}
