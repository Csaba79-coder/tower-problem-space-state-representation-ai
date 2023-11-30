package com.csaba79coder.model;

import lombok.RequiredArgsConstructor;

import java.util.Stack;

/**
 * Ancestor of all graph search algorithms.
 * Graph search algorithms only need to implement the Search method.
 * It returns a terminal node if a solution is found, otherwise, it returns null.
 * The solution is built by traversing parent references upward from the terminal node.
 */

@RequiredArgsConstructor
public abstract class GraphSearch {

    private final Node startNode; // The start node for the graph search.

    // Better to keep startNode private, but child classes can access it.
    protected Node getStartNode() {
        return startNode;
    }

    /**
     * If a solution exists, i.e., there is a path in the state space graph
     * from the start node to a terminal node, it returns a solution;
     * otherwise, it returns null.
     * The solution is returned as a terminal node.
     * By following the parent references of this node upwards, the solution is reconstructed in reverse order.
     */
    public abstract Node search();

    /**
     * Prints the solution based on a terminal node.
     * It assumes that by following parent references of the terminal node,
     * you can reach the start node. It reverses the order of nodes to print the solution correctly.
     * If the node is null, it prints "No solution."
     */
    public void printSolution(Node terminalNode) {
        if (terminalNode == null) {
            System.out.println("No solution.");
            return;
        }

        // Reverse the order of nodes.
        Stack<Node> solution = new Stack<>();
        Node currentNode = terminalNode;
        while (currentNode != null) {
            solution.push(currentNode);
            currentNode = currentNode.getParent();
        }

        // Now, print the solution.
        for (Node node : solution) {
            System.out.println(node);
        }
    }
}
