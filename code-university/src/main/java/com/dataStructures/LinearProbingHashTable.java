package com.dataStructures;

import java.util.ArrayList;
import java.util.List;

public class LinearProbingHashTable<Key, Value> {
    private static final int INIT_CAPACITY = 16;
    private int n;
    private int m;
    private Key [] keys;
    private Value[] values;

    public LinearProbingHashTable() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashTable(int capacity) {
        m = capacity;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    //The method masks off the sign bit (to turn the
    //32-bit number into a 31-bit nonnegative
    //integer) and then computes the remainder when dividing by M,
    // as in modular hashing.
    private int hash(Key key){
        return (key.hashCode() & 0x7fffffff) % m;
    }

    public void add(Key key, Value value){
        if(key == null){
            throw new IllegalArgumentException("First argument to add() is null");
        }
        if(value == null){
            remove(key);
            return;
        }
        if (n > 0 && n <= m/8){
            resize(m/2);
        }

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if(keys[i].equals(key)){
                values[i] = value;
                return;
            }

        }
        keys[i] = key;
        values[i] = value;
        n++;

    }

    public Value get(Key key){
        if(key == null){
            throw new IllegalArgumentException("Argument to get() is null");
        }
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                return values[i];
            }
        }
        return null;
    }

    public void remove(Key key){
        if(key == null){
            throw new IllegalArgumentException("Argument to remove() is null");
        }
        if(!contains(key)){
            return;
        }
        int i = hash(key);
        while (!key.equals(keys[i])){
            i = (i + 1) % m;
        }

        // deletes key and associated value
        keys[i] = null;
        values[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null){
            // deletes keys[i] and values[i] and reinserts
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            add(keyToRehash, valueToRehash);
            i = (i + 1) % m;
        }

        n--;
        if( n > 0 && n <= m / 8){
            resize(m / 2);
        }

    }

    public boolean contains(Key key){
        if(key == null){
            throw new IllegalArgumentException("Argument to contains() is null");
        }

        return get(key) != null;
    }

    public Iterable<Key> keys(){
        List<Key> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if(keys[i] != null) {
                list.add(keys[i]);
            }
        }
        return list;
    }

    private void resize(int capacity){
        LinearProbingHashTable<Key, Value> temp = new LinearProbingHashTable<Key, Value>(capacity);
        for (int i = 0; i < m; i++) {
            if(keys[i] != null){
                temp.add(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
    }
}
