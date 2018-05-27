public class QueueLinkedList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;
    private int size;

    public QueueLinkedList() {
        this.size = 0;
        this.head = new ListNode<>();
        this.tail = new ListNode<>();
    }

    public int size(){
        return this.size;
    }

    public void enqueue(E element){
        ListNode<E> newElement = new ListNode<>();
        newElement.data = element;
        if(isEmpty()){
            this.head.next = newElement;
            this.tail.next = newElement;
        } else {
            ListNode<E> prevNode = this.tail.next;
            prevNode.next = newElement;
            this.tail.next = newElement;
        }
        this.size++;
    }

    public E dequeue(){
        if(isEmpty()){
            throw new IndexOutOfBoundsException("The queue is already empty");
        } else {
            ListNode<E> element = this.head.next;
            if(this.size == 1){
                this.head.next = null;
                this.tail.next = null;
            } else {
                this.head.next = element.next;
            }
            this.size--;
            return element.data;
        }

    }

    public boolean isEmpty(){
        return this.size == 0;
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
