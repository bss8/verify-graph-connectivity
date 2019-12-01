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

import com.github.javafaker.Faker;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
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

    public int determineNumEdges(int numVertices) {
        if (numVertices == 0) {
            return 0;
        } else if (numVertices == 1) {
            return 1;
        }

        int numEdgesToConnect = 0;
        setNumVertices(numVertices);

        ArrayList<T> listOfValues = new ArrayList<>();
        ArrayList<String[]> listOfUniquePairs = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            addVertex((T) name);
            listOfValues.add((T) name);
        }

        listOfUniquePairs = findUniquePairs((ArrayList<String>) listOfValues);
        ArrayList<String[]> copyListOfPairs = new ArrayList<>(listOfUniquePairs);

        while (listOfUniquePairs.size() > 0) {
            // nextInt is normally exclusive of the top value, can add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(0, listOfUniquePairs.size());
            T[] edgePair = (T[]) listOfUniquePairs.get(randomNum);

            boolean isAddedTo = addEdge(edgePair[0], edgePair[1]);
            if (isAddedTo) {
                numEdgesToConnect++;
                listOfUniquePairs.remove(edgePair);
                if (isConnected(listOfValues.get(0))) {
                    return numEdgesToConnect;
                }
            }
        }
        System.out.println("If we see this, something went wrong! There is no graph connection!");
        return numEdgesToConnect;
    }


    public static void main(String...args) {
        BiDirectionalGraph<String> graph = new BiDirectionalGraph<>(String.class);
        graph.initGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Stephanie").toString());
        boolean isConnected = graph.isConnected("Suresh");
        System.out.println("Is connected? " + isConnected);

        int numEdges = graph.determineNumEdges(50);
        System.out.println("# edges to connect? " + numEdges);
    }
} // end class BiDirectionalGraph