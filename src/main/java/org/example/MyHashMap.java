package org.example;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    LinkedList<Node>[] hashMap = new LinkedList[2];
    int size = 0;


    //Вставляет элементы
    public void put(K key, V value) {
        if (size >= hashMap.length) resize();

        int cell = getIndex(key) % hashMap.length; //Нормализует хэш значение, чтоб определить номер ячейки для вставки

        if (hashMap[cell] == null) { //Проверка на наличие элементов в ячейке
            hashMap[cell] = new LinkedList<>();
            hashMap[cell].add(new Node(key, value)); //Вставляет новый узел
            size++;
            return;
        }
        else {
            for (Node node : hashMap[cell]) { //Цикл проходится по каждому узлу в ячейке
                if (node.key.equals(key)) { //Если ключи равны, то происходит замена значения на новой
                    node.value = value;
                    size++;
                    return;
                }
            }

            hashMap[cell].add(new Node(key, value)); //Если ключи разные, то создается новый узел
            size++;
            return;
        }
    }

    //Получение значения по ключу
    public V get(K key){
        int ix = getIndex(key) % hashMap.length;
        if (hashMap[ix] == null) return null; // Возвращает null, если ячейка пустая
        for (Node node: hashMap[ix]){ //Цикл проходится по каждому узлу в ячейке
            if (node.key.equals(key)){
                return (V) node.getValue();
            }
        }
        return null;    // Возвращает null, если ключ не совпал
    }

    //Удаление значения по ключу
    public void remove(K key){
        if (key == null) return;
        int ix = getIndex(key) % hashMap.length; //Нормализует хэш значение, чтоб определить номер ячейки для удаления
        if(hashMap[ix] == null) return;
        Node toRemove = null;

        for (Node node: hashMap[ix]){ //Цикл проходится по каждому узлу в ячейке
            if (node.key.equals(key)){
                toRemove = node;
                break;
            }
        }
        if (toRemove == null) return;
        hashMap[ix].remove(toRemove);
        size--;
    }

    //Проверка на наличие ключа
    public boolean containsKey(K key){
        if (key == null) return false;
        int ix = getIndex(key) % hashMap.length;

        if (hashMap[ix] == null){
            return false;
        }
        for(Node node: hashMap[ix]){
            if(node.key.equals(key)) return true;
        }
        return false;
    }

    //Увеличивает размер массива
    public void resize(){
        LinkedList<Node>[] oldHashMap = hashMap;
        hashMap = new LinkedList[size * 2];
        size = 0;
        for (int i = 0; i < oldHashMap.length; i++){
            if (oldHashMap[i] == null) continue;
            for (Node node : oldHashMap[i]){
                put((K) node.getKey(), (V) node.getValue());
            }
        }
    }
    //Вычисляет хэшкод
    public int getIndex(K key){ return key.hashCode();}
    //Возвращает количество заполненных ячеек в массиве
    public int size(){ return size;}
}


