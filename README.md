README: Integer Triple Transformation Program
Overview
This program is designed to transform an integer into its triple using the lowest possible cost, with a predefined number of operations. It operates under specific constraints with associated costs for each operation:

Increment: Add one to the argument. Cost: 1.
Decrement: Subtract one from the argument. Cost: 2.
Doubling: Multiply the argument by 2. Cost: 3.
The primary objective is to identify the minimum number of moves necessary to transform the initial integer into its triple, adhering to these operation constraints.

Strategy Design Pattern and Algorithms
The program is developed using the strategy design pattern. This approach allows for the flexibility of employing different algorithms or strategies for the transformation process under the same interface. Specifically, the program incorporates three algorithms to resolve the problem:

A (A-Star) Algorithm:* A widely-used pathfinding and graph traversal algorithm known for its efficiency and accuracy.
IDA (Iterative Deepening A-Star) Algorithm:* An optimized version of A* that combines depth-first search’s space-efficiency and breadth-first search's completeness.
Uniform Cost Algorithm: A search algorithm that expands the least cost node first, ensuring the discovery of a least cost path to the goal.
A detailed explanation of this design choice will be provided in the chapter “Opção de design tomada.”

Input
The program accepts a single line input, containing an integer in string format.

Output
The output consists of a sequence of numbers, starting from the initial integer and ending with its triple, each on a new line. This sequence represents the path of minimum cost transformation. Following this sequence, and separated by a blank line, the program outputs a positive integer indicating the minimum cost achieved.

Usage
Input the Integer: Enter the integer in string format.
Run the Program: Execute the program to start the transformation process.
Review Output: The output will display the step-by-step transformation sequence, followed by the total minimum cost.
Additional Notes
The program is designed to be efficient and user-friendly.
It's important to ensure that the input is a valid integer in string format.
The strategy design pattern provides an efficient and flexible approach to handle various transformation strategies.
