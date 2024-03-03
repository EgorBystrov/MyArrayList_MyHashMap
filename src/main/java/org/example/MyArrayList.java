package org.example;

public class MyArrayList <T>{
    //Начальная емкость списка
    private final int DEFAULT_CAPACITY = 10;
    //Используется для уменьшения размера массива (Объяснение в методе remove())
    private final int CUT_RATE = 4;
    private Object[] array = new Object[DEFAULT_CAPACITY];
    //Считает количество элементов в списке (не равен размеру массива)
    private int pointer = 0;

    /*
    Добавляет новый элемент в список. При достижении размера внутреннего
    массива происходит его увеличение в два раза.
    */
    public void add(T item) {
        if(pointer == array.length-1)
            resize(array.length*2);
        array[pointer++] = item;
    }

    //Возвращает элемент списка по индексу.
    public T get(int index) {
        return (T) array[index];
    }

    /*
    Удаляет элемент списка по индексу. Все элементы справа от удаляемого
    перемещаются на шаг влево. Если после удаления элемента количество
    элементов стало в CUT_RATE раз меньше, чем размер внутреннего массива,
    то внутренний массив уменьшается в два раза, для экономии занимаемого
    места.
    */
    public void remove(int index) {
        for (int i = index; i<pointer; i++)
            array[i] = array[i+1];
        array[pointer] = null;
        pointer--;
        if (array.length > DEFAULT_CAPACITY && pointer < array.length / CUT_RATE)
            resize(array.length/2);
    }
    //Возвращает количество элементов в списке
    public int size() {
        return pointer;
    }

    //Вспомогательный метод для изменения размера массива
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
