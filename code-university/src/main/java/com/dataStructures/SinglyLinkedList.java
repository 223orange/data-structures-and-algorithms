package com.dataStructures;

import java.util.StringJoiner;

/**
 * My version of Singly Linked List implementation.I've decided to follow
 * the coding-interview-university GitHub project https://github.com/jwasham/coding-interview-university
 * created by John Washam and this is an exercise from it.
 * @param <E>  the type of elements in this implementation of Linked list.
 * @author Iliya Naydenov
 */
public class SinglyLinkedList<E> {
    /**
     * The first element in the list
     */
    private ListNode<E> head;
    /**
     *  The size of the SinglyLinkedList(the number of elements it contains)
     */
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
        this.head = new ListNode<>();
    }

    /**
     * Returns the number of elements in the list.
     * @return number of items in the list
     */
    public int size(){
        return this.size;
    }

    /**
     * Returns <tt>true</tt> if this list contains no elements.
     * @return <tt>true</tt> if this list contains no elements
     */
    public boolean empty(){
        return this.size == 0;
    }

    /**
     * Gets the value from the node at the specified index.
     * @param index index of the node whose value is to be returned
     * @return element at the specified index
     */
    public E valueAt(int index){
        checkForBounds(index);
        ListNode<E> firstElement = this.head;
        for (int i = 0; i < index; i++) {
            firstElement = firstElement.next;
        }
        return firstElement.data;
    }

    /**
     * Inserts the specified value in front of the list.
     * @param element element which will be added in front of the list
     */
    public void pushFront(E element){
        ListNode<E> newNode = new ListNode<>();
        newNode.data = element;
        newNode.next = this.head;
        this.head = newNode;
        this.size++;
    }

    /**
     * Removes the last value from the front of the list.
     * @return element to be removed
     */
    public E popFront(){
        if (this.head.data == null){
            throw new ArrayIndexOutOfBoundsException("The list is empty");
        }
        E elementToReturn = this.head.data;
        this.head = this.head.next;
        this.size--;
        return elementToReturn;
    }

    /**
     * Inserts the specified value at the end of the list.
     * @param element element to be added at the end of the list.
     */
    public void pushBack(E element){
        ListNode<E> newNode = new ListNode<>();
        newNode.data = element;
        if(this.size == 0){
            this.head = newNode;
        }
        if(this.size > 0){
            ListNode<E> oldLast = this.getLastNode();
            oldLast.next = newNode;
        }
        this.size++;
    }

    /**
     * Removes the last value from the list
     * @return element to be removed from the end of the list
     */
    public E popBack(){
        ListNode<E> lastToPop = this.getLastNode();
        E value = lastToPop.data;
        lastToPop = null;
        this.size--;
        return value;
    }

    /**
     * Returns the first value from the list.
     * @return element from the front of the list
     */
    public E front(){
        this.checkIfEmpty();
        return this.head.data;
    }

    /**
     * Returns the first value from the list.
     * @return element from the back of the list
     */
    public E back(){
        this.checkIfEmpty();
        return this.getLastNode().data;
    }

    /**
     * Inserts an element at a given index in the list.
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void insert(int index, E element){
        this.checkForBounds(index);
        ListNode<E> seartchedElement = this.head;
        for (int i = 0; i < index - 1; i++) {
            seartchedElement = seartchedElement.next;
        }
        ListNode<E> elementToReconnect = seartchedElement.next;
        ListNode<E> elementToInclude = new ListNode<>();
        elementToInclude.data = element;
        elementToInclude.next = elementToReconnect;
        seartchedElement.next = elementToInclude;
        this.size++;
    }

    /**
     * Removes an element at a given index in the list.
     * @param index index from which the specified element is to be removed
     */
    public void erase(int index){
        this.checkForBounds(index);
        ListNode<E> searchedElement = this.head;
        for (int i = 0; i < index - 1; i++) {
            searchedElement = searchedElement.next;
        }
        ListNode<E> elementToErase= searchedElement.next;
        searchedElement.next = elementToErase.next;
        elementToErase.next = null;
        elementToErase.data = null;
        this.size--;
    }

    /**
     * Reverses the order of the elements in the list.
     */
    public void reverse(){
        if(this.head.data == null){
            throw new IndexOutOfBoundsException("The list is empty");
        }
        ListNode<E> currNode = this.head;
        ListNode<E> nextNode;
        ListNode<E> prevNode = null;
        while (currNode.data != null){
            nextNode = currNode.next;
            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }
        this.head = prevNode;
    }

    /**
     * Removes the first element in the list with a specified value
     * @param value value of the first element to be removed
     */
    public void removeValue(E value){
        ListNode<E> nodeToBeRemoved = this.head;
        for (int i = 0; i < this.size; i++) {
            if(nodeToBeRemoved.data == value){
                this.erase(i);
            }
            nodeToBeRemoved = nodeToBeRemoved.next;
        }
    }

    /**
     * Returns string containing the elements in the list
     * @return string representation of the elements in the list
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        ListNode<E> list = this.head;
        sj.add(list.data.toString());
        for (int i = 0; i < this.size - 1; i++) {
            list = list.next;
            sj.add(list.data.toString());
        }
        return sj.toString();
    }

    /**
     * Returns the last node for the list
     * @return the last ListNode from the list
     */
    private ListNode<E> getLastNode(){
        ListNode<E> last = this.head;
        if(this.size == 1){
            return last;
        }
        for (int i = 0; i < this.size - 1; i++) {
            last = last.next;
        }
        return last;
    }

    /**
     * Throws IndexOutOfBoundsException if the list is empty
     */
    private void checkIfEmpty(){
        if(this.head.data == null){
            throw new IndexOutOfBoundsException("The list is empty!");
        }
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

    private static class ListNode<E> {
        private ListNode<E> next;
        private E data;

        public ListNode() {
            this.next = null;
            this.data = null;
        }
    }
}
