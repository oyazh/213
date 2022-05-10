package com.company;
import java.util.*;

public class HashTable<K, V> {

    private class HashNode<K, V> {
        K key;
        V value;
        int hashCode;
        HashNode<K, V> next;

        public HashNode(K key, V value, int hashCode)
        {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }

    private ArrayList<HashNode<K, V>> chainArray;
    private int M = 11;
    private int size;

    public HashTable() {
        this(11);
    }
    public HashTable(int M) {
        chainArray = new ArrayList<>();
        size = 0;
        for (int i = 0; i < M; i++) {
            chainArray.add(null);
        }
    }
    private int hash(K key) {
        return key.hashCode();
    }
    private int hashIndex(K key) {
        int hashCode = hash(key);
        int index = hashCode % M;
        return index;
    }

    public void put(K key, V value) {
        int index = hashIndex(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        head = chainArray.get(index);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hash);
        newNode.next = head;
        chainArray.set(index, newNode);
        size++;
    }

    public V get(K key) {
        int index = hashIndex(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hash) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = hashIndex(key);
        int hash = hash(key);
        HashNode<K, V> head = chainArray.get(index);
        HashNode<K, V> empty = null;
        while (head != null) {
            if (head.key.equals(key) && hash == head.hashCode) {
                break;
            }else{
                empty = head;
                head = head.next;
            }
        }
        if (head == null) {
            return null;
        }
        size--;
        if (empty != null) {
            empty.next = head.next;
        }else{
            chainArray.set(index, head.next);
        }
        return head.value;
    }
    public boolean contains(K key) {
        return get(key) != null;
    }
}