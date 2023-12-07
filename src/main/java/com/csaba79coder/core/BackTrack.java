package com.csaba79coder.core;

/**
 * A class implementing the Backtrack graph search algorithm.
 * It includes the three basic Backtrack algorithms:
 * - Basic Backtrack
 * - Depth-limited Backtrack
 * - Memory-based Backtrack
 * The branch-bound Backtrack is not implemented.
 */
public class BackTrack extends GraphSearch {

    private final int limit;
    private final boolean useMemory;

    public BackTrack(Node startNode, int limit, boolean useMemory) {
        super(startNode);
        this.limit = limit;
        this.useMemory = useMemory;
    }

    public BackTrack(Node startNode) {
        this(startNode, 0, false);
    }

    public BackTrack(Node startNode, int limit) {
        this(startNode, limit, false);
    }

    public BackTrack(Node startNode, boolean useMemory) {
        this(startNode, 0, useMemory);
    }

    @Override
    public Node search() {
        return search(getStartNode());
    }

    private Node search(Node currentNode) {
        int depth = currentNode.getDepth();

        if (limit > 0 && depth >= limit) {
            return null;
        }

        Node currentParent = null;

        if (useMemory) {
            currentParent = currentNode.getParent();
        }

        while (currentParent != null) {
            if (currentNode.equals(currentParent)) {
                return null;
            }

            currentParent = currentParent.getParent();
        }

        if (currentNode.isTerminalNode()) {
            return currentNode;
        }

        for (int i = 0; i < currentNode.getOperatorCount(); i++) {
            Node childNode = new Node(currentNode);

            if (childNode.isSuperOperator(i)) {
                Node terminalNode = search(childNode);

                if (terminalNode != null) {
                    return terminalNode;
                }
            }
        }

        return null;
    }
}
