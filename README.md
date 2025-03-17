# Advanced Data Structures in Java: Doubly Linked List Iterators and 2D Binary Search Tree Implementation

## Table of Contents
1. [Project Description](#project-description)
2. [Exercise 1: Iterators and Doubly Linked List](#exercise-1-iterators-and-doubly-linked-list)
   - [Implementation Details](#implementation-details)
   - [Class Specifications](#class-specifications)
   - [Sorting Algorithm](#sorting-algorithm)
3. [Exercise 2: Two-Dimensional Binary Search Tree](#exercise-2-two-dimensional-binary-search-tree)
   - [Implementation Details](#implementation-details-1)
   - [Class Specifications](#class-specifications-1)
   - [Supported Operations](#supported-operations)
4. [Installation Guide](#installation-guide)
5. [Usage Instructions](#usage-instructions)
6. [Additional Information](#additional-information)

---

## Project Description
This repository contains coursework for the **Data Structures** course at the University of Piraeus. It includes two programming exercises implemented in Java:
1. **Iterators for a Doubly Linked List** with insertion sort.
2. **Two-Dimensional Binary Search Tree** for handling 2D keys.

The objective of these exercises is to apply advanced data structure concepts and develop efficient algorithms for handling dynamic data.

---

## Exercise 1: Iterators and Doubly Linked List
### Implementation Details
- Implements **iterators** for a **doubly linked list**, allowing traversal in forward and backward directions.
- Provides **Insertion Sort** using iterators.
- Avoids using built-in Java collections like `ArrayList`.

### Class Specifications
- `List`: Encapsulates the linked list.
- `Node`: Represents an element of the list.
- `Iter`: Implements iterator functionality for traversing the list.

### Sorting Algorithm
- The **insertion sort** method sorts the list in ascending order based on key values.
- Utilizes iterators for efficient insertion and traversal.

---

## Exercise 2: Two-Dimensional Binary Search Tree
### Implementation Details
- Implements a **binary search tree (BST)** that stores **2D integer keys (x, y)**.
- Each node contains an **x-value** and a secondary BST for storing **y-values**.

### Class Specifications
- `TwoDimTree`: Represents the hierarchical structure of nodes.
- `Node`: Defines the structure of each tree node.

### Supported Operations
- **Insertion**: Adds new (x, y) keys while maintaining BST properties.
- **Search**: Finds if a (x, y) pair exists in the tree.
- **Deletion**: Removes keys and updates the structure accordingly.
- **Range Query**: Retrieves all (x, y) pairs within a given range.
- **Lowest Common Ancestor**: Finds the lowest common ancestor for two x-values.

---

## Installation Guide
### Prerequisites:
- **Java Development Kit (JDK 8 or later)**
- **Eclipse IDE (or any Java IDE)**

### Installation Steps:
1. Clone the repository:
   ```sh
   git clone https://github.com/starfastasos/Java_Language-coursework.git
   ```
2. Open the project in **Eclipse**:
   - Go to `File` > `Import` > `Existing Projects into Workspace`.
   - Select the project folder.
   - Click `Finish`.
3. Compile and run the program:
   - Compile `ListDemo.java` or  `TwoDimTree.java` file.
   - Execute the `ListDemo.java` or  `TwoDimTree.java` file.

---

## Usage Instructions
- Run `ListDemo.java` to test **doubly linked list iterators** and **insertion sort**.
- Run `TwoDimTree.java` to test **2D binary search tree operations**.

---

## Additional Information
- All **data structures are implemented from scratch** without Java’s built-in collections.
- The project strictly follows **OOP principles** with encapsulation and modularity.
