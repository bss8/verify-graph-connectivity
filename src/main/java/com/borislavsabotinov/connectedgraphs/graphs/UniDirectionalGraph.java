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

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Is connected? " + isConnected);
        isConnected = graph.isConnected("Pawel");
        System.out.println("Is connected? " + isConnected);
    }
} // end class UniDirectionalGraph