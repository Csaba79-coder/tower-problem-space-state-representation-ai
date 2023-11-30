package com.csaba79coder.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * A class implementing depth-first graph search.
 * This implementation assumes that the start node is not a terminal node.
 * Open nodes are stored in a stack.
 */

public class DepthFirstSearch extends GraphSearch {

    // For depth-first search, it's beneficial to store open nodes in a stack.
    // This way, the node with the maximum depth is always at the top of the stack.
    // There's no need to search for the node with the maximum depth to expand.
    private final Stack<Node> open; // Set of open nodes.
    private final List<Node> closed; // Set of closed nodes.
    private final boolean cycleDetection; // If false, it can fall into an infinite loop.

    public DepthFirstSearch(Node startNode, boolean cycleDetection) {
        super(startNode);
        open = new Stack<>();
        open.push(startNode); // Initially, only the start node is open.
        closed = new ArrayList<>(); // Initially, the set of closed nodes is empty.
        this.cycleDetection = cycleDetection;
    }

    // Default value for cycle detection is true.
    public DepthFirstSearch(Node startNode) {
        this(startNode, true);
    }

    // The search for a solution begins here.
    @Override
    public Node search() {
        // If cycle detection is not required, the algorithm is much faster.
        if (cycleDetection) {
            return searchWithCycleDetection();
        }
        return searchQuickly();
    }

    private Node searchWithCycleDetection() {
        // While the set of open nodes is not empty.
        while (!open.isEmpty()) {
            // This is the node with the maximum depth.
            Node currentNode = open.pop();
            // Expand this node.
            List<Node> newNodes = currentNode.expand();
            for (Node childNode : newNodes) {
                // If I found the terminal node, I'm done.
                if (childNode.isTerminalNode()) {
                    return childNode;
                }
                // Only add new nodes to the open set that have not been in the closed or open sets.
                // The Contains method calls the Equals method in the Node class.
                if (!closed.contains(childNode) && !open.contains(childNode)) {
                    open.push(childNode);
                }
            }
            // Reclassify the expanded node as closed.
            closed.add(currentNode);
        }
        return null;
    }

    // You should only use this when you're certain that there are no cycles in the state space graph.
    // Otherwise, it's likely to cause an infinite loop.
    private Node searchQuickly() {
        while (!open.isEmpty()) {
            Node currentNode = open.pop();
            List<Node> newNodes = currentNode.expand();
            for (Node childNode : newNodes) {
                if (childNode.isTerminalNode()) {
                    return childNode;
                }
                // If there are no cycles, there's no need to check if D was already in open or closed.
                open.push(childNode);
            }
            // If there are no cycles, there's no need to reclassify C as closed.
        }
        return null;
    }
}
