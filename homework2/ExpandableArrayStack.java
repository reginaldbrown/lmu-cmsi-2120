import java.util.NoSuchElementException;

public class ExpandableArrayStack implements Stack {
    private static final int MINIMUM_CAPACITY = 16;

    private Object[] elements = new Object[MINIMUM_CAPACITY];
    private int size = 0;

    public void push(Object item) {
        // If the stack is full, double the capacity of the array.
        if (size == elements.length) {
            reallocate(elements.length * 2);
        }
        elements[size++] = item;
    }

    public Object pop() {
        var topElement = peek();
        // If the stack is at most 25% full, halve the capacity of the array.
        if (size <= capacity() / 4 && capacity() > MINIMUM_CAPACITY) {
            reallocate(elements.length / 2);
        }
        elements[--size] = null;
        return topElement;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements[size - 1];
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

    private void reallocate(int newSize) {
        Object[] newArray = new Object[newSize];
        // Copy the elements from the old array to the new array.
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }
}
