import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A simple immutable linked list, implement completely from scratch as a
 * singly-linked structure. There aren't too many operations, as additional will
 * be left for homework.
 * 
 * The implementation uses a sum type because I have major null-phobia. Not
 * going to pay the billion-dollar mistake, not with this one.
 */
public sealed interface SimpleImmutableList permits EmptyList, ListNode {
    int size();

    Object head();

    SimpleImmutableList tail();

    static SimpleImmutableList of(Object... items) {
        SimpleImmutableList list = new EmptyList();
        for (var i = items.length - 1; i >= 0; i--) {
            list = new ListNode(items[i], list);
        }
        return list;
    }

    static SimpleImmutableList from(Object head, SimpleImmutableList tail) {
        return new ListNode(head, tail);
    }

    Object at(int index);

    void forEach(Consumer<Object> consumer);
}
    // existing methods

    SimpleImmutableList take(int n);

    SimpleImmutableList drop(int n);

    SimpleImmutableList reversed();

    SimpleImmutableList append(SimpleImmutableList other);

    <T> SimpleImmutableList map(Function<Object, T> f);

    SimpleImmutableList filter(Predicate<Object> p);
}

final record EmptyList() implements SimpleImmutableList {
    public int size() {
        return 0;
    }

    public Object head() {
        throw new NoSuchElementException();
    }

    public SimpleImmutableList tail() {
        throw new NoSuchElementException();
    }

    public Object at(int index) {
        throw new NoSuchElementException();
    }

    public void forEach(Consumer<Object> consumer) {
        // Intentionally empty: nothing to iterate
    }

    public SimpleImmutableList take(int n) {
        // Check if n is zero
        if (n == 0) {
            return new EmptyList();
        }

        throw new NoSuchElementException();
    }

    public SimpleImmutableList drop(int n) {
        // Check if n is zero
        if (n == 0) {
            return this;
        }

        throw new NoSuchElementException();
    }

    public SimpleImmutableList reversed() {
        return new EmptyList();
    }

    public SimpleImmutableList append(SimpleImmutableList other) {
        return other;
    }

    public <T> SimpleImmutableList map(Function<Object, T> f) {
        return new EmptyList();
    }

    public SimpleImmutableList filter(Predicate<Object> p) {
        return new EmptyList();
    }
}

final record ListNode(
        Object head, SimpleImmutableList tail) implements SimpleImmutableList {
    public int size() {
        return 1 + tail.size();
    }

    public Object head() {
        return head;
    }

    public SimpleImmutableList tail() {
        return tail;
    }

    public Object at(int index) {
        return index == 0 ? head : tail.at(index - 1);
    }

    public void forEach(Consumer<Object> consumer) {
        consumer.accept(head);
        tail.forEach(consumer);
    }

    public SimpleImmutableList take(int n) {
        // Check if the list is empty or if n is zero
        if (n == 0 || this instanceof EmptyList) {
            return new EmptyList();
        }

        // Check if n is greater than the size of the list
        if (n > size()) {
            return this;
        }

        // Create a new list with the first n elements of this list
        SimpleImmutableList list = new EmptyList();
        SimpleImmutableList current = this;
        for (int i = 0; i < n; i++) {
            list = new ListNode(current.head(), list);
            current = current.tail();
        }
        return list;
    }

    public SimpleImmutableList drop(int n) {
        // Check if the list is empty or if n is zero
        if (n == 0 || this instanceof EmptyList) {
            return this;
        }

        // Check if n is greater than the size of the list
        if (n > size()) {
            return new EmptyList();
        }

        // Create a new list with all but the first n elements of this list
        SimpleImmutableList current = this;
        for (int i = 0; i < n; i++) {
            current = current.tail();
        }
        return current;
    }

    public SimpleImmutableList reversed() {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return new EmptyList();
        }

        // Create a new list with the elements of this list in reverse order
        SimpleImmutableList list = new EmptyList();
        SimpleImmutableList current = this;
        while (!(current instanceof EmptyList)) {
            list = new ListNode(current.head(), list);
            current = current.tail();
        }
        return list;
    }

    public SimpleImmutableList append(SimpleImmutableList other) {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return other;
        }
    
        // Create a new list with all the elements of this list followed by all the elements of the other list
        SimpleImmutableList list = new ListNode(head(), tail().append(other));
        return list;
    }
    
    public <T> SimpleImmutableList map(Function<Object, T> f) {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return new EmptyList();
        }
    
        // Create a new list with the items in this list transformed by the function f
        SimpleImmutableList list = new ListNode(f.apply(head()), tail().map(f));
        return list;
    }
    
    public SimpleImmutableList filter(Predicate<Object> p) {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return new EmptyList();
        }
    
        // If the head of the list satisfies the predicate, add it to the new list
        SimpleImmutableList list = p.test(head()) ? new ListNode(head(), new EmptyList()) : new EmptyList();
        return list.append(tail().filter(p));
    }
    
    public Object last() {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            throw new NoSuchElementException();
        }
    
        // Check if the list only has one element
        if (tail() instanceof EmptyList) {
            return head();
        }
    
        // Recursively find the last element in the tail of the list
        return tail().last();
    }
    
    public boolean every(Predicate<Object> p) {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return true;
        }
    
        // If the head of the list does not satisfy the predicate, return false
        if (!p.test(head())) {
            return false;
        }
    
        // Recursively check the predicate for the tail of the list
        return tail().every(p);
    }
    
    public boolean some(Predicate<Object> p) {
        // Check if the list is empty
        if (this instanceof EmptyList) {
            return false;
        }
    
        // Check if the current element satisfies the predicate
        if (p.test(head())) {
            return true;
        }
    
        // Check if any of the remaining elements satisfy the predicate
        return tail().some(p);
    }
    


        public SimpleImmutableList drop(int n) {
            // Check if the list is empty or if n is zero
            if (n == 0 || this instanceof EmptyList) {
                return this;
            }
        
            // Check if n is greater than the size of the list
            if (n > size()) {
                return new EmptyList();
            }
        
            // Create a new list with all but the first n elements of this list
            SimpleImmutableList current = this;
            for (int i = 0; i < n; i++) {
                current = current.tail();
            }
            return current;
        }
        
        public SimpleImmutableList reversed() {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return new EmptyList();
            }
        
            // Create a new list with the elements of this list in reverse order
            SimpleImmutableList list = new EmptyList();
            SimpleImmutableList current = this;
            while (!(current instanceof EmptyList)) {
                list = new ListNode(current.head(), list);
                current = current.tail();
            }
            return list;
        }
        
        public SimpleImmutableList append(SimpleImmutableList other) {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return other;
            }
        
            // Create a new list with all the elements of this list followed by all the elements of the other list
            SimpleImmutableList list = new ListNode(head(), tail().append(other));
            return list;
        }
        
        public <T> SimpleImmutableList map(Function<Object, T> f) {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return new EmptyList();
            }
        
            // Create a new list with the items in this list transformed by the function f
            SimpleImmutableList list = new ListNode(f.apply(head()), tail().map(f));
            return list;
        }
        
        public SimpleImmutableList filter(Predicate<Object> p) {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return new EmptyList();
            }
        
            // Create a new list with only those elements in this list that satisfy the predicate p
            SimpleImmutableList list = tail().filter(p);
            if (p.test(head())) {
                list = new ListNode(head(), list);
            }
            return list;
        }
        
        public Object last() {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                throw new NoSuchElementException();
            }
        
            // Return the last element in the list
            SimpleImmutableList current = this;
            while (!(current.tail() instanceof EmptyList)) {
                current = current.tail();
            }
            return current.head();
        }
        
        public boolean every(Predicate<Object> p) {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return true;
            }
        
            // Return whether every item in the list satisfies the predicate p
            return p.test(head()) && tail().every(p);
        }
        
        public boolean some(Predicate<Object> p) {
            // Check if the list is empty
            if (this instanceof EmptyList) {
                return false;
            }
        
            // Check if the current item satisfies the predicate
            if (p.test(head())) {
                return true;
            }
        
            // Check the rest of the list
            return tail().some(p);
        }

    }
    
