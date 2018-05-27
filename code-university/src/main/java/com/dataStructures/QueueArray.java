public class QueueArray<E> {
    private Object[] initArray;
    private int capacity;
    private int size;
    private int read;
    private int write;

    public QueueArray(int capacity) {
        this.initArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
        this.read = 0;
        this.write = 0;
    }

    public void enqueue(E element){
        if(this.write < this.read & this.read - this.write == 1){
            throw new IndexOutOfBoundsException("Please dequeue an element first");
        }
        if(this.write == this.capacity & this.size + 1 < this.capacity){
            this.write = 0;
        }
        if(this.initArray[this.write] == null){
            this.initArray[this.write] = element;
            this.write++;
            this.size++;
        } else{
            throw new IndexOutOfBoundsException("The queue is already full");
        }

    }

    @SuppressWarnings("unchecked")
    public E dequeue(){
        if(isEmpty()){
            return null;
        } else {
            if(this.read == this.capacity & this.size > 0){
                this.read = 0;
            }
            E elementToBeRemoved = (E)this.initArray[this.read];
            this.initArray[this.read] = null;
            this.read++;
            this.size--;
            return elementToBeRemoved;
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
}
