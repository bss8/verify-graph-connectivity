/*
 * Copyright (c) 2019. Borislav S. Sabotinov
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.borislavsabotinov.connectedgraphs.graphs;

import java.util.Set;

/**
 * Uni-directional, also referred to as a directed, graph.
 * When an edge is added from A to B, <strong>NO</strong> edge is added from B to A.
 * This means we cannot reach A if we start from B - movement is in one direction only.
 * @param <T>
 */
public class UniDirectionalGraph<T extends Comparable<? super T>> extends BasicGraph<T>  {

    public UniDirectionalGraph(Class<T> type) {
        super(type);
    }

    @Override
    public boolean addEdge(T fromValue1, T toValue2) {
        return false;
    }

    @Override
    public void removeEdge(T fromValue1, T toValue2) {

    }

    @Override
    public Set<T> depthFirstSearch(T root) {
        return null;
    }

    @Override
    public Set<T> breadthFirstSearch(T root) {
        return null;
    }

    public Class<T> getMyType() {
        return this.type;
    }

    @Override
    public boolean isConnected(T root) {
        return false;
    }

    public static void main(String...args) {
        UniDirectionalGraph<String> graph = new UniDirectionalGraph<>(String.class);
        graph.initGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Stephanie").toString());
        boolean isConnected = graph.isConnected("Suresh");
        System.out.println("Is connected? " + isConnected);
    }
} // end class UniDirectionalGraph