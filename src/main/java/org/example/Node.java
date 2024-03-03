package org.example;
//Клвсс для создания узлов в бакетах MyHashMap
public class Node<K, V> {
    final K key;
    V value;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
