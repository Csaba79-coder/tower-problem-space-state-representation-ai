package com.csaba79coder.core;

import java.util.ArrayList;
import java.util.List;

public class Node {

    // A node contains a state, its depth, and its parent node.
    private final AbstractState state;
    private final int depth;
    private final Node parent; // Going up the parents leads to the start node.

    // Constructor:
    // Sets the internal state to the start state.
    // The caller's responsibility is to call it with the initial state.
    // The depth of the start node is 0, and it has no parent.
    public Node(AbstractState initialState) {
        state = initialState;
        depth = 0;
        parent = null;
    }

    // Creates a new child node.
    // An applicable operator needs to be called afterward to complete it.
    public Node(Node parent) {
        state = (AbstractState) parent.getState().clone();
        depth = parent.getDepth() + 1;
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    public boolean isTerminalNode() {
        return state.isGoalState();
    }

    public int getOperatorCount() {
        return state.getOperatorCount();
    }

    public boolean isSuperOperator(int i) {
        return state.isSuperOperator(i);
    }

    public AbstractState getState() {
        return state;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node node = (Node) obj;
        return state.equals(node.state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }

    @Override
    public String toString() {
        return state.toString();
    }

    // Applies all applicable operators and returns the resulting new nodes.
    public List<Node> expand() {
        List<Node> newNodes = new ArrayList<>();
        for (int i = 0; i < getOperatorCount(); i++) {
            // Create a new child node.
            Node newNode = new Node(this);
            // Try the i-th superoperator. Is it applicable?
            if (newNode.isSuperOperator(i)) {
                // If yes, add it to the new nodes.
                newNodes.add(newNode);
            }
        }
        return newNodes;
    }
}