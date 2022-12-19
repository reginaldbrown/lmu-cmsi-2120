import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * A simple linked list class, implemented completely from scratch, using doubly
 * linked nodes and a cool "header" node to greatly simplify insertion and
 * deletions on the end of the list.
 */
public class SimpleLinkedList {

    /**
     * Doubly-linked node class, completely private to the List class, as clients
     * don't care about the implementation of the list. This is a regular class and
     * not a record because nodes are mutable.
     */

        private class Node {
            Object item;
            Node next;
            Node previous;
    
            Node(Object item, Node next, Node previous) {
                this.item = item;
                this.next = next;
                this.previous = previous;
            }
        }
    
        private Node header = new Node(null, null, null);
        private int size = 0;
    
        {
            header.next = header.previous = header;
        }
    
        public int size() {
            return size;
        }
    
        public void addFirst(Object item) {
            addBefore(item, header.next);
        }
    
        public void addLast(Object item) {
            addBefore(item, header);
        }
    
        public void add(int index, Object item) {
            addBefore(item, (index == size ? header : nodeAt(index)));
        }
    
        public void remove(int index) {
            remove(nodeAt(index));
        }
    
        public Object get(int index) {
            return nodeAt(index).item;
        }
    
        public void set(int index, Object item) {
            nodeAt(index).item = item;
        }
    
        public int indexOf(Object item) {
            var index = 0;
            for (var node = header.next; node != header; node = node.next) {
                if (node.item.equals(item)) {
                    return index;
                }
                index++;
            }
            return -1;
        }
    
        public void take(int n) {
            if (n < 0 || n > size) {
                throw new IllegalArgumentException("Invalid value of n: " + n);
            }
    
            var node = header.next;
            for (int i = 0; i < n; i++) {
                node = node.next;
            }
            header.next = node;
            node.previous = header;
    
            size = n;
        }
    
        public void drop(int n) {
            if (n < 0 || n > size) {
                throw new IllegalArgumentException("Invalid value of n: " + n);
            }
    
            var node = header.next;
            for (int i = 0; i < n; i++) {
                node = node.next;
            }
            header.next = node;
            node.previous = header;
    
            size -= n;
        }
    
        public void reverse() {
            var node = header.next;
            while (node != header) {
                var next = node.next;
                node.next = node.previous;
                node.previous = next;
                node = next;
            }
            var temp = header.next;
            header.next = header.previous;
            header.previous = temp;
        }
    
        public void append(SimpleLinkedList other) {
            header.previous.next = other.header.next;
            other.header.next.previous = header.previous;
            header.previous = other.header.previous;
            other.header.previous.next = header;
        
            size += other.size;
            other.size = 0;
        
            other.header.next = other.header.previous = other.header;
        }
        
        public void map(UnaryOperator<Object> f) {
            var node = header.next;
            while (node != header) {
                node.item = f.apply(node.item);
                node = node.next;
            }
        }
    
        public void filter(Predicate<Object> p) {
            var node = header.next;
            while (node != header) {
                if (!p.test(node.item)) {
                    var next = node.next;
                    remove(node);
                    node = next;
                } else {
                    node = node.next;
                }
            }
        }
    
        public Object last() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
            return header.previous.item;
        }
    
        public boolean every(Predicate<Object> p) {
            var node = header.next;
            while (node != header) {
                if (!p.test(node.item)) {
                    return false;
                }
                node = node.next;
            }
            return true;
        }
    
        public boolean some(Predicate<Object> p) {
            var node = header.next;
            while (node != header) {
                if (p.test(node.item)) {
                    return true;
                }
                node = node.next;
            }
            return false;
        }
    
        private void addBefore(Object item, Node node) {
            var newNode = new Node(item, node, node.previous);
            newNode.previous.next = newNode;
            newNode.next.previous = newNode;
            size++;
        }
    
        private void remove(Node node) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            size--;
        }
    
        private Node nodeAt(int index) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
            var node = header.next;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        }
    }