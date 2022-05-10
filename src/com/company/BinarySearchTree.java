package com.company;
import java.util.*;

public class BinarySearchTree<K extends Comparable<K>, V>
{
    private Node root;
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int N;
        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null)
            return 0;
        else return x.N;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }
    private Node put(Node x, K key, V val) {
        if (x == null){
            return new Node(key, val, 1);
        }
        int a = key.compareTo(x.key);
        if (a < 0){
            x.left = put(x.left, key, val);
        }
        else if (a > 0){
            x.right = put(x.right, key, val);
        }
        else{
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public V get(K key) {
        return get(root, key);
    }
    private V get(Node x, K key) {

        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return get(x.left, key);
        }
        else if (cmp > 0){
            return get(x.right, key);
        }
        else{
            return x.val;
        }
    }

    public K min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null){
            return x;
        }else{
            return min(x.left);
        }
    }
    public void delMin() {
        root = delMin(root);
    }
    private Node delMin(Node x) {
        if (x.left == null){
            return x.right;
        }
        x.left = delMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void del(K key) {
        root = del(root, key);
    }
    private Node del(Node x, K key) {
        int cmp = key.compareTo(x.key);
        if      (cmp < 0){
            x.left  = del(x.left,  key);
        } else if (cmp > 0){
            x.right = del(x.right, key);
        } else {
            if (x.right == null){
                return x.left;
            }
            if (x.left  == null){
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = delMin(t.right);
            x.left = t.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
}