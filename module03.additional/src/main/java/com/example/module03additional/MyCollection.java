package com.example.module03additional;

import java.util.ArrayList;
import java.util.Arrays;

public class MyCollection<T extends Object> extends ArrayList<T> {
    private T[] mObjects;
    private int indexOfLastElement = -1;

    @SuppressWarnings("unchecked")
    public MyCollection() {
        mObjects = (T[]) new Object[0];
    }

    @SuppressWarnings("unchecked")
    public MyCollection(int size) {
        mObjects = (T[]) new Object[size];
    }

    @SuppressWarnings("unchecked")
    public boolean add(Object element) {
        T el = (T) element;
        if (indexOfLastElement < 0 || indexOfLastElement == mObjects.length - 1) {
            changeSizeOfArray(1);
        }
        set(++indexOfLastElement, el);
        return true;
    }

    private void changeSizeOfArray(int numberOfElements) {
        mObjects = Arrays.copyOf(mObjects, mObjects.length + numberOfElements);
    }

    @SuppressWarnings("unchecked")
    public void add(int index, Object element) {
        T el = (T) element;
        changeSizeOfArray(1);
        int counter = size() - 1;
        while (counter > index) {
            mObjects[counter] = mObjects[counter - 1];
            counter--;
        }
        if (counter == index) {
            set(index, el);
        }
    }

    @SuppressWarnings("unchecked")
    public T set(int index, Object element) {
        mObjects[index] = (T) element;
        return (T) element;
    }

    public void addAll(T[] c) {
        if (c == null) {
            return;
        }
        int cSize = c.length;
        int missingPlace = cSize - (size() - indexOfLastElement + 1);
        if (missingPlace > 0) {
            changeSizeOfArray(missingPlace);
        }
        for (int i = 0; i < cSize; i++) {
            set(++indexOfLastElement, c[i]);
        }
    }

    public void addAll(int index, T[] c) {
        if (c == null) {
            return;
        }
        int cSize = c.length;
        changeSizeOfArray(cSize);
        int counter = size() - 1;
        while (counter >= index) {
            set(counter, mObjects[counter - cSize]);
            counter--;
        }
        for (T obj : c) {
            set(index++, obj);
        }
    }

    public T get(int index) {
        return mObjects[index];
    }

    public int indexOf( Object o) {
        for (int i = 0; i < size(); i++) {
            if (o.equals(mObjects[i])) {
                return i;
            }
        }
        return -1;
    }

    public T remove(int index) {
        T obj = get(index);
        while (index < size() - 2) {
            mObjects[index] = mObjects[index + 1];
            index++;
        }
        changeSizeOfArray(-1);
        return obj;
    }

    public int size() {
        return mObjects.length;
    }

    public T[] toArray() {
        return mObjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MyCollection<?> that = (MyCollection<?>) o;

        if (indexOfLastElement != that.indexOfLastElement) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(mObjects, that.mObjects);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(mObjects);
        result = 31 * result + indexOfLastElement;
        return result;
    }

    @Override
    public String toString() {
        return "MyCollection{" +
                "mObjects=" + Arrays.toString(mObjects) +
                ", indexOfLastElement=" + indexOfLastElement +
                '}';
    }
}
