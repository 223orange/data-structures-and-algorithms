package com.dataStructures;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * My version of Resizable-Array implementation.I've decided to follow
 * the coding-interview-university GitHub project https://github.com/jwasham/coding-interview-university
 * created by John Washam and this is an exercise from it.
 * @param <E> the type of elements in this implementation of Resizable-Array
 * @author Iliya Naydenov
 */
public class ResizableArray<E> {
    /**
     * Default initial capacity
     */
    public static final int INITIAL_CAPACITY = 16;

    /**
     *  The capacity(the length) of the ResizableArray
     */
    private int capacity;

    /**
     * The size of the ResizableArray(the number of elements it contains)
     */
    private int size;

    /**
     * Raw data array with INITIAL_CAPACITY (the length of the ResizableArray)
     */
    private Object[] initArray;

    public ResizableArray() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.initArray = new Object[this.capacity];
    }

    /**
     * Returns the number of items in the ResizableArray.
     * @return number of items in the list.
     */
    public int size(){
        return this.size;
    }

    /**
     * Returns the length of the ResizableArray.
     * @return number of items the ResizableArray can hold
     */
    public int capacity(){
        return this.capacity;
    }

    /**
     * Checks whether the data structure is empty.
     * @return {@code true} if this ResizableArray contains no elements
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the element specified by the given index in the ResizableArray.
     * @param index index of the element to return
     * @return the element at the specified index in the ResizableArray
     */
    @SuppressWarnings("unchecked")
    public E at(int index) {
        checkForBounds(index);
        return (E) this.initArray[index];
    }

    /**
     * Adds an item at the last position of the ResizableArray.
     * @param item element to be added on the last position of the ResizableArray
     */
    public void push(E item){
        resize();
        this.initArray[size] = item;
        this.size++;
    }

    /**
     * Inserts an element at a given index in the ResizableArray.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void insert(int index, E element){
        checkForBounds(index);
        resize();
        Object[] copiedInitArray = this.initArray;
        System.arraycopy(this.initArray, index, copiedInitArray,
                index + 1, this.size - index);
        copiedInitArray[index] = element;
        this.size++;
    }

    /**
     * Inserts an element in front of the ResizableArray.
     * @param element element to be inserted
     */
    public void prepend(E element){
        resize();
        Object[] copiedInitArray = this.initArray;
        System.arraycopy(this.initArray, 0, copiedInitArray,
                1, this.size + 1);
        copiedInitArray[0] = element;
        this.size++;
    }

    /**
     * Removes the last element of the ResizableArray.
     * @return element which was removed
     */
    @SuppressWarnings("unchecked")
    public E pop(){
        Object poppedItem = this.initArray[this.size - 1];
        this.initArray = Arrays.copyOf(this.initArray, this.size - 1);
        this.size--;
        resize();
        return (E) poppedItem;
    }

    /**
     * Deletes an element at a given index in the Resizable Array
     * @param index index at which the specified element is to be deleted
     */
    public void delete(int index){
        checkForBounds(index);
        int numToBeMoved = this.size - index - 1;
        System.arraycopy(this.initArray, index + 1, this.initArray,
                index, numToBeMoved);
        this.initArray[index] = null;
    }

    /**
     * Finds the first occurrence of a given element in the array, or -1
     * if the element was not found
     * @param element element to be found in the array
     * @return index at which was found the specified element, or -1 if the elment
     * doesn't exist in the array.
     */
    public int find(Object element){
        for (int i = 0; i < this.size; i++) {
            if(this.initArray[i].equals(element)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes a specified element from the array
     * @param element element to be removed
     */
    public void remove(Object element){
        while (this.find(element) != -1){
            int foundIndex = this.find(element);
            this.delete(foundIndex);
        }
    }

    /**
     * Returns string containing the elements in the array
     * @return string representation of the elements in the array
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");

        for (int i = 0; i < this.size; i++) {
            sj.add(this.initArray[i].toString());
        }
        return sj.toString();
    }

    /**
     * Throws IndexOutOfBoundsException if the specified index is out of the bounds of the array
     * @param index index to be checked if it is out of the bounds of the array
     */
    private void checkForBounds(int index){
        if (index > this.size || index < 0) {
            throw new IndexOutOfBoundsException("Please specify a valid index!");
        }
    }

    /**
     * Resizes the Resizable Array. If the size is equal to the capacity - 1, the capacity increases two times,
     * if the size of the array is 1/4 of the capacity, the capacity decreases two times.
     */
    private void resize(){
        if(this.size == this.capacity - 1){
            this.initArray = Arrays.copyOf(this.initArray,this.capacity * 2);
        }
        if(this.size == (this.capacity / 4)){
            this.initArray = Arrays.copyOf(this.initArray, this.capacity / 2);
        }
    }
}
