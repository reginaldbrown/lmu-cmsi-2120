
// This class implements a queue data structure using an expandable array.
public class ExpandableArrayQueue implements Queue {
    // The minimum capacity of the array used to store the queue elements.
    // This value is used when reallocating the array to a smaller size.
    private static final int MINIMUM_CAPACITY = 16;

    // The array used to store the queue elements.
    private Object[] elements = new Object[MINIMUM_CAPACITY];
    // The number of elements currently in the queue.
    private int size = 0;
    // The index of the current front item, if one exists.
    private int head = 0;
    // The index of the next item to be added.
    private int tail = 0;

    // Adds an item to the back of the queue.
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

    // Removes and returns the front item from the queue.
    public Object dequeue() {
        // Save a reference to the front item, so it can be returned later.
        var topElement = peek();
        // If the queue is at most 25% full, halve the capacity of the array.
        if (size <= capacity() / 4 && capacity() > MINIMUM_CAPACITY) {
            reallocate(elements.length / 2);
        }
        // Remove the front item from the queue by setting the element at the head
        // index to null.
        elements[head] = null;
        // Move the head to the next position in the circular array.
        head = (head + 1) % elements.length;
        size--;
        return topElement;
    }

    // Returns the front item from the queue without removing it.
    public Object peek() {
        // If the queue is empty, throw an exception.
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[head];
    }

    // Returns true if the queue is empty, and false otherwise.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns the number of items currently in the queue.
    public int size() {
        return size;
    }

    // Returns the current capacity of the array used to store the queue elements.
    public int capacity() {
        return elements.length;
    }

    // Reallocates the array used to store the queue elements to the specified size.
    // When reallocating, always make the new head 0.
    private void reallocate(int newSize) {
        Object[] newArray = new Object[newSize];
        for (var i = 0; i < size(); i++) {
            // Copy the elements from the old array to the new array,
            // taking into account the circular nature of the old array.
            newArray[i] = elements[(head + i) % capacity()];
        }
        // Set the head and tail indices to their new positions in the new array.
        head = 0;
        tail = size();
        elements = newArray;
    }
}

