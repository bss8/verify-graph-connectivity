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
import java.util.concurrent.ThreadLocalRandom;

/**
 * Undirected, graph.
 * When an edge from A to B is added, B to A is also added. This ensures
 * we can move in any direction between two given vertices with no restriction.
 * Number of edge in undirected graph is (N * (N-1)) / 2
 *
 * @param <T>
 */
public class UndirectedGraph<T extends Comparable<? super T>> extends BasicGraph<T> {

    public UndirectedGraph(Class<T> type) {
        super(type);
    }

    @Override
    public void addEdge(T value1, T value2) {
        Vertex v1 = new Vertex(value1);
        Vertex v2 = new Vertex(value2);
        adjacencyMap.get(v1).add(v2);
        adjacencyMap.get(v2).add(v1);
    }

    @Override
    public void removeEdge(T value1, T value2) {
        Vertex v1 = new Vertex(value1);
        Vertex v2 = new Vertex(value2);
        List<Vertex> edgeV1 = adjacencyMap.get(v1);
        List<Vertex> edgeV2 = adjacencyMap.get(v2);
        if (edgeV1 != null)
            edgeV1.remove(v2);
        if (edgeV2 != null)
            edgeV2.remove(v1);
    }

    public int determineNumEdges(int numVertices) {
        if (numVertices <= 1) {
            return 0;
        }

        int numEdgesToConnect = 0;
        this.setNumVertices(numVertices);

        ArrayList<T> listOfValues = new ArrayList<>();
        ArrayList<T[]> listOfUniquePairs;

        this.populateGraph(numVertices, listOfValues);

        listOfUniquePairs = this.findUniquePairs( listOfValues);

        while (!isConnected(listOfValues.get(0))) {
            System.out.println("UNDIRECTED GRAPH - LOOPING! uniqueList size: " + listOfUniquePairs.size());
            if (listOfUniquePairs.size() == 0) {
                return (numVertices * (numVertices -1))/2;
            }
            // nextInt is normally exclusive of the top value, can add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(0, listOfUniquePairs.size());
            T[] edgePair = listOfUniquePairs.get(randomNum);

            addEdge(edgePair[0], edgePair[1]);
            System.out.println("Added undirected edge!");
            numEdgesToConnect++;
            listOfUniquePairs.remove(edgePair);  // remove from unique pair list
        }

        return numEdgesToConnect;
    }


    public static void main(String...args) {
        UndirectedGraph<String> graph = new UndirectedGraph<>(String.class);
        graph.initGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Stephanie").toString());
        boolean isConnected = graph.isConnected("Suresh");
        System.out.println("Is connected? " + isConnected);

        int numEdges = graph.determineNumEdges(80);
        System.out.println("# edges to connect? " + numEdges);
    }
} // end class UndirectedGraph