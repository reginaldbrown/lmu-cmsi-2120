import java.util.NoSuchElementException;

public class ExpandableArrayQueue implements Queue {
    private static final int MINIMUM_CAPACITY = 16;

    private Object[] elements = new Object[MINIMUM_CAPACITY];
    private int size = 0;
    private int head = 0; // index of the current front item, if one exists
    private int tail = 0; // index of next item to be added

    public void enqueue(Object item) {
        // If the queue is full, double the capacity of the array.
        if (size == elements.length) {
            reallocate(elements.length * 2);
        }
        elements[tail] = item;
        // Move the tail to the next position in the circular array.
        tail = (tail + 1) % elements.length;
        size++;
    }

    public Object dequeue() {
        var topElement = peek();
        // If the queue is at most 25% full, halve the capacity of the array.
        if (size <= capacity() / 4 && capacity() > MINIMUM_CAPACITY) {
            reallocate(elements.length / 2);
        }
        elements[head] = null;
        // Move the head to the next position in the circular array.
        head = (head + 1) % elements.length;
        size--;
        return topElement;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[head];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return elements.length;
    }

    /*
     * When reallocating, always make the new head 0.
     */
    private void reallocate(int newSize) {
        Object[] newArray = new Object[newSize];
        for (var i = 0; i < size(); i++) {
            // Copy the elements from the old array to the new array,
            // taking into account the circular nature of the old array.
            newArray[i] = elements[(head + i) % capacity()];
        }
        head = 0;
        tail = size();
        elements = newArray;
    }
}
