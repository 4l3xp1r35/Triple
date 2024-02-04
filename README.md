# README: Integer Triple Transformation Project
## Overview
This project involves the development of a solution to transform an integer into its triple using the lowest possible cost, with a predefined number of operations. It is implemented in Java and adheres to specific constraints with associated costs for each operation:

* Increment: Add one to the argument. Cost: 1.
* Decrement: Subtract one from the argument. Cost: 2.
* Doubling: Multiply the argument by 2. Cost: 3.

The primary objective of this project is to devise an efficient approach to identify the minimum number of moves necessary to transform the initial integer into its triple.

## Strategy Design Pattern and Algorithms
The project employs the strategy design pattern in Java, enhancing flexibility and efficiency in implementing different algorithms or strategies under the same interface. The project specifically includes three algorithms:

* A (A-Star) Algorithm:* A widely-used pathfinding and graph traversal algorithm known for its efficiency and accuracy.
* IDA (Iterative Deepening A-Star) Algorithm:* An optimized version of A* that combines depth-first search’s space-efficiency and breadth-first search's completeness.
* Uniform Cost Algorithm: A search algorithm that expands the least cost node first, ensuring the discovery of a least cost path to the goal.

## Input
* Accepts an integer in string format.
## Output
* Produces a sequence of numbers from the initial integer to its triple, each on a new line, representing the minimum cost transformation path.
* Outputs the total minimum cost achieved after the sequence.

## Usage
* Input the Integer: Input the integer as a string.
* Execute the Solution: Run the solution to initiate the transformation process.
* Analyze the Output: Review the transformation sequence and the computed minimum cost.
