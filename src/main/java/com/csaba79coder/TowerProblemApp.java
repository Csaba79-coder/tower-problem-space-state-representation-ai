package com.csaba79coder;

import com.csaba79coder.core.BackTrack;
import com.csaba79coder.core.GraphSearch;
import com.csaba79coder.core.Node;
import com.csaba79coder.state.TowerState;

public class TowerProblemApp {

    public static void main(String[] args) {

        Node startNode;
        GraphSearch searcher;

        System.out.println("Solving the Tower Problem.");

        startNode = new Node(new TowerState());

        System.out.println(startNode);

        int limit = 15;
        System.out.println("Using a depth-limited, memory-based backtrack search with a depth limit of " + limit +".");
        searcher = new BackTrack(startNode, limit, true);
        searcher.printSolution(searcher.search());
    }
}
