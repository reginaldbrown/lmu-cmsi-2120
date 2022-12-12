# Expandable Array Queue and Stack

This Java project provides queue and stack data structures that use expandable arrays to store their elements. The arrays automatically resize when the queue or stack becomes full, and are also resized to a smaller size if they become less than 25% full, allowing the data structures to efficiently use memory while maintaining good performance.

## Features

The `ExpandableArrayQueue` class provides the following features:

- `enqueue(item)`: adds an item to the back of the queue.
- `dequeue()`: removes and returns the front item from the queue.
- `peek()`: returns the front item from the queue without removing it.
- `isEmpty()`: returns true if the queue is empty, and false otherwise.
- `size()`: returns the number of items currently in the queue.
- `capacity()`: returns the current capacity of the array used to store the queue elements.

The `ExpandableArrayStack` class provides the following features:

- `push(item)`: adds an item to the top of the stack.
- `pop()`: removes and returns the top item from the stack.
- `peek()`: returns the top item from the stack without removing it.
- `isEmpty()`: returns true if the stack is empty, and false otherwise.
- `size()`: returns the number of items currently in the stack.
- `capacity()`: returns the current capacity of the array used to store the stack elements.
