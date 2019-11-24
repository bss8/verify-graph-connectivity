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

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @param <T>
 */
public class BiDirectionalGraph<T extends Comparable<? super T>> extends BasicGraph<T> {


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
        List<Vertex> eV1 = adjacencyMap.get(v1);
        List<Vertex> eV2 = adjacencyMap.get(v2);
        if (eV1 != null)
            eV1.remove(v2);
        if (eV2 != null)
            eV2.remove(v1);
    }

    Set<T> depthFirstTraversal(T root) {
        Set<T> visited = new LinkedHashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (Vertex v : getAdjacentVertices(vertex)) {
                    stack.push(v.value);
                }
            }
        }
        return visited;
    }

    public static void main(String...args) {
        BiDirectionalGraph<String> graph = new BiDirectionalGraph<>();
        graph.createGraph(graph);
        System.out.println(graph.toString());
        System.out.println(graph.getAdjacentVertices("Maria").toString());
    }

    void createGraph(BasicGraph<String> graph) {
        graph.addVertex("Bob");
        graph.addVertex("Alice");
        graph.addVertex("Mark");
        graph.addVertex("Rob");
        graph.addVertex("Maria");
        graph.addEdge("Bob", "Alice");
        graph.addEdge("Bob", "Rob");
        graph.addEdge("Alice", "Mark");
        graph.addEdge("Rob", "Mark");
        graph.addEdge("Alice", "Maria");
        graph.addEdge("Rob", "Maria");
    }
} // end class BiDirectionalGraph