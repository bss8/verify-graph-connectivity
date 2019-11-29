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

import java.util.*;
import java.util.logging.Logger;

/**
 * Bi-directional, also referred to as undirected, graph.
 * When an edge from A to B is added, B to A is also added. This ensures
 * we can move in any direction between two given vertices with no restriction.
 * @param <T>
 */
public class BiDirectionalGraph<T extends Comparable<? super T>> extends BasicGraph<T> {

    public BiDirectionalGraph(Class<T> type) {
        super(type);
    }

    @Override
    public boolean addEdge(T value1, T value2) {
        boolean addedEdge = false;
        Vertex v1 = new Vertex(value1);
        Vertex v2 = new Vertex(value2);
        if (!adjacencyMap.get(v1).contains(v2) &&
                !adjacencyMap.get(v2).contains(v1)) {
            adjacencyMap.get(v1).add(v2);
            adjacencyMap.get(v2).add(v1);
            addedEdge = true;
        }
        return addedEdge;
    }

    @Override
    public void removeEdge(T value1, T value2) {
        Vertex v1 = new Vertex(value1);
        Vertex v2 = new Vertex(value2);
        List<Vertex> eV1 = adjacencyMap.get(v1);
        List<Vertex> eV2 = adjacencyMap.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    public static void main(String...args) {
        BiDirectionalGraph<String> graph = new BiDirectionalGraph<>(String.class);
        graph.initGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Stephanie").toString());
        boolean isConnected = graph.isConnected("Suresh");
        System.out.println("Is connected? " + isConnected);
    }


} // end class BiDirectionalGraph