# SimpleImmutableList

A simple immutable linked list implementation in Java. This implementation is a singly-linked structure and is completely from scratch. The list is implemented using a sum type to avoid potential null reference exceptions.

## Features

- `size()`: returns the size of the list
- `head()`: returns the first element of the list
- `tail()`: returns the list with the first element removed
- `of(Object... items)`: creates a new list with the given elements
- `from(Object head, SimpleImmutableList tail)`: creates a new list with the given head element and the given list as the tail
- `at(int index)`: returns the element at the specified index in the list
- `forEach(Consumer<Object> consumer)`: iterates over the elements of the list and performs the given action on each element
- `take(int n)`: returns a new list with the first `n` elements of the original list
- `drop(int n)`: returns a new list with the first `n` elements of the original list removed
- `reversed()`: returns a new list with the elements of the original list in reverse order
- `append(SimpleImmutableList other)`: returns a new list with the elements of the original list followed by the elements of the given list
- `map(Function<Object, T> f)`: returns a new list with the results of applying the given function to each element of the original list
- `filter(Predicate<Object> p)`: returns a new list with only the elements of the original list that satisfy the given predicate

# SimpleLinkedList

A simple linked list implementation in Java. This implementation is a singly-linked structure and is completely from scratch. The list is implemented using a sum type to avoid potential null reference exceptions.

## Features

- `size()`: returns the size of the list
- `head()`: returns the first element of the list
- `tail()`: returns the list with the first element removed
- `of(Object... items)`: creates a new list with the given elements
- `from(Object head, SimpleLinkedList tail)`: creates a new list with the given head element and the given list as the tail
- `at(int index)`: returns the element at the specified index in the list
- `forEach(Consumer<Object> consumer)`: iterates over the elements of the list and performs the given action on each element
- `take(int n)`: returns a new list with the first `n` elements of the original list
- `drop(int n)`: returns a new list with the first `n` elements of the original list removed
- `reversed()`: returns a new list with the elements of the original list in reverse order
- `append(SimpleLinkedList other)`: returns a new list with the elements of the original list followed by the elements of the given list
- `map(Function<Object, T> f)`: returns a new list with the results of applying the given function to each element of the original list
- `filter(Predicate<Object> p)`: returns a new list with only the elements of the original list that satisfy the given predicate





