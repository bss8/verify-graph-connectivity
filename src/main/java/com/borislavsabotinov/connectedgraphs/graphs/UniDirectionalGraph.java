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
        boolean addedEdge = false;
        Vertex v1 = new Vertex(fromValue1);
        Vertex v2 = new Vertex(toValue2);
        if (!adjacencyMap.get(v1).contains(v2)) {
            adjacencyMap.get(v1).add(v2);
            addedEdge = true;
        }
        return addedEdge;
    }

    @Override
    public void removeEdge(T fromValue1, T toValue2) {
        Vertex v1 = new Vertex(fromValue1);
        Vertex v2 = new Vertex(toValue2);
        List<Vertex> eV1 = adjacencyMap.get(v1);
        if (eV1 != null)
            eV1.remove(v2);
    }


    public Class<T> getMyType() {
        return this.type;
    }

    public boolean isConnected(ArrayList<T> listOfVertices) {
        if (listOfVertices.size() <= 1) {
            return true;
        }

        boolean isConnected = false;

        for (T listOfVertex : listOfVertices) {
            Set<T> tmpSet = depthFirstSearch(listOfVertex);
            if (getNumVertices() == tmpSet.size()) {
                isConnected = true;
                break;
            }
        }

        return isConnected;
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

        // Try adding edges one way
        while (listOfUniquePairs.size() > 0) {
            // nextInt is normally exclusive of the top value, can add 1 to make it inclusive
            int randomNum = ThreadLocalRandom.current().nextInt(0, listOfUniquePairs.size());
            T[] edgePair = (T[]) listOfUniquePairs.get(randomNum);

            boolean isAddedTo = addEdge(edgePair[0], edgePair[1]);
            boolean isAddedFrom = addEdge(edgePair[1], edgePair[0]);
            if (isAddedTo) {
                numEdgesToConnect++;
                listOfUniquePairs.remove(edgePair);
                if (isConnected(listOfValues)) {
                    return numEdgesToConnect;
                }
            } else if (isAddedFrom) {
                numEdgesToConnect++;
                listOfUniquePairs.remove(new String[]{(String) edgePair[1], (String) edgePair[0]});
                if (isConnected(listOfValues)) {
                    return numEdgesToConnect;
                }
            }
        }

        System.out.println("If we see this, something went wrong! There is no graph connection!");
        return numEdgesToConnect;
    }

    public static void main(String...args) {
        UniDirectionalGraph<String> graph = new UniDirectionalGraph<>(String.class);
        graph.initGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Stephanie").toString());
        ArrayList<String> listOfKeys = new ArrayList<>();
        listOfKeys.add("Suresh");
        listOfKeys.add("Stephanie");
        listOfKeys.add("Carolyn");
        listOfKeys.add("Pawel");
        listOfKeys.add("Meyyappan");
        boolean isConnected = graph.isConnected(listOfKeys);
        System.out.println("Is connected - check all vertices as starting point? " + isConnected);
        isConnected = graph.isConnected("Pawel");
        System.out.println("Is connected starting at Pawel? " + isConnected);
        ArrayList<String[]> stringList = graph.findUniquePairs(listOfKeys);
        System.out.println("Unique pairs: " + Arrays.deepToString(stringList.toArray()));
        int numEdges = graph.determineNumEdges(50);
        System.out.println("# edges to connect? " + numEdges);
    }
} // end class UniDirectionalGraph