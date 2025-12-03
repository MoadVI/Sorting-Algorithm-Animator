# Sorting Algorithm Animator

A JavaFX-based visual application that animates various sorting algorithms in real-time, helping users understand how different sorting techniques work through interactive visualization.

## Features

- *Multiple Sorting Algorithms*: Visualize Bubble Sort, Selection Sort, Merge Sort, Quick Sort, and Heap Sort
- *Interactive Controls*: Adjust array size, animation speed, and generate new random arrays
- *Color-Coded Visualization*:
  -  Blue: Default/unsorted elements
  -  Red: Elements being compared
  -  Yellow: Elements being swapped or set
  -  Green: Sorted elements
  -  Purple: Pivot elements (Quick Sort)

- *Real-time Status Updates*: Track each step of the sorting process
- *Responsive UI*: Built with JavaFX and FXML for a smooth user experience

## Prerequisites

- Java Development Kit (JDK) 17 or higher
- Apache Maven 3.6 or higher
- JavaFX SDK 17.0.1 (managed by Maven)

## Installation

1. *Clone the repository*
   ```
   git clone https://github.com/MoadVI/Sorting-Algorithm-Animator
   cd SortingAnimator

   ```

3. *Build the project*
   ```
   mvn clean install

   ```

5. *Run the application*
   ```
   mvn javafx:run

   ```

## Project Structure

```text
├── SortingAnimator
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── sortinganimator
│           │       ├── App.java                         # Main application 
│           │       ├── algorithm                        # Sorting algorithm implementations
│           │       │   ├── AbstractSort.java            # Base class for all sorting algorithms
│           │       │   ├── BubbleSort.java
│           │       │   ├── HeapSort.java
│           │       │   ├── MergeSort.java
│           │       │   ├── QuickSort.java
│           │       │   └── SelectionSort.java
│           │       ├── controller
│           │       │   └── AppController.java           # FXML controller for UI logic
│           │       ├── model
│           │       │   └── AnimationAction.java         # Data model for animation steps
│           │       └── view
│           │           └── Visualizer.java              # Visual component
│           └── resources
│               └── sortinganimator
│                   └── App.fxml

```



## Sorting Algorithms

### Bubble Sort
- *Time Complexity*: O(n²)
- *Space Complexity*: O(1)

### Selection Sort
- *Time Complexity*: O(n²)
- *Space Complexity*: O(1)

### Merge Sort
- *Time Complexity*: O(n log n)
- *Space Complexity*: O(n)

### Quick Sort
- *Time Complexity*: O(n log n) average, O(n²) worst
- *Space Complexity*: O(log n)

### Heap Sort
- *Time Complexity*: O(n log n)
- *Space Complexity*: O(1)


### Modifying Array Value Range
Edit AppController.java and change the MAX_VALUE constant:
```java
private static final int MAX_VALUE = 100; 

```

### Adjusting Animation Speed
The animation delay is calculated in animateNextStep() inside of AppController.java:
```java
double delayMs = 50 * speedFactor; 

```

*Authors*: Moad Chahti, Aymen Boubrik
