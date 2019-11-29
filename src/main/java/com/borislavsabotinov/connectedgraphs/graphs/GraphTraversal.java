package com.borislavsabotinov.connectedgraphs.graphs;

import java.util.Set;

public interface GraphTraversal<T extends Comparable<? super T>> {
    Set<T> depthFirstSearch(T root);
    Set<T> breadthFirstSearch(T root);
    boolean isConnected(T root);
}
