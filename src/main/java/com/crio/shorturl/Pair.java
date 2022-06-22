package com.crio.shorturl;

public class Pair <T, T1>{
    T key;
    T1 value;
    public Pair (T key, T1 value){
        this.key = key;
        this.value = value;
    }
    public T getKey(){
        return key;
    }public void setKey(T key){
        this.key = key;
    }
    public T1 getValue(){
        return value;
    }
    public void setValue(T1 value){
        this.value = value;
    }
}