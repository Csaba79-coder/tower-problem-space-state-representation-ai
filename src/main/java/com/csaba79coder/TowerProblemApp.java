package com.csaba79coder;

import com.csaba79coder.core.BackTrack;
import com.csaba79coder.core.DepthFirstSearch;
import com.csaba79coder.core.GraphSearch;
import com.csaba79coder.core.Node;
import com.csaba79coder.state.TowerState;

public class TowerProblemApp {

    public static void main(String[] args) {

        Node startNode;
        GraphSearch searcher;

        System.out.println("Solving the Tower problem.");

        startNode = new Node(new TowerState(78, 42, 36, 30, 6));
        System.out.println(startNode);
        int limit = 15;
        System.out.println("Using a depth-limited, memory-based backtrack search with a depth limit of " + limit + ".");
        searcher = new BackTrack(startNode, limit, true);
        searcher.printSolution(searcher.search());

        System.out.println("Solving tower state problem using depth-first search with cycle detection.");
        searcher = new DepthFirstSearch(startNode, true);
        searcher.printSolution(searcher.search());
    }
}
