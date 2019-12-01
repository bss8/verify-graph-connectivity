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

/**
 * Adjacency list is used to represent the graph.
 * Generic T is used to ensure Graph can support multiple types, as long as T extends the Comparable class.
 *
 * {@code <? super T>} T is the lower bound for the wildcard ?
 * {@code extends Comparable<? super T>} type T must implement Comparable of T or one of its super classes
 *
 * Use @Override annotation to take advantage of the compiler checking to make sure you actually are overriding a method.
 * @param <T>
 */
public abstract class BasicGraph<T extends Comparable<? super T>> implements Graph<T>, GraphTraversal<T> {
    Map<Vertex, List<Vertex>> adjacencyMap;
    final Class<T> type;
    int numVertices;

    BasicGraph(Class<T> type) {
        this.adjacencyMap = new HashMap<Vertex, List<Vertex>>();
        this.type = type;
    }

    /**
     * Only add value if the specified key is not already associated with a value.
     * @param value
     */
    @Override
    public void addVertex(T value) {
        adjacencyMap.putIfAbsent(new Vertex(value), new ArrayList<>());
    }

    @Override
    public void removeVertex(T value) {
        Vertex v = new Vertex(value);
        adjacencyMap.values().stream().forEach(e -> e.remove(v));
        adjacencyMap.remove(new Vertex(value));
    }

    @Override
    public abstract boolean addEdge(T value1, T value2);

    @Override
    public abstract void removeEdge(T value1, T value2);

    @Override
    public List<Vertex> getAdjacentVertices(T value) {
        return adjacencyMap.get(new Vertex(value));
    }

    @Override
    public Set<T> depthFirstSearch(T root) {
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

    @Override
    public Set<T> breadthFirstSearch(T root) {
        Set<T> visited = new LinkedHashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(root);
        visited.add(root);
        while (!queue.isEmpty()) {
            T vertex = queue.poll();
            for (Vertex v : getAdjacentVertices(vertex)) {
                if (!visited.contains(v.value)) {
                    visited.add(v.value);
                    queue.add(v.value);
                }
            }
        }
        return visited;
    }

    @Override
    public boolean isConnected(T key) {
        if (getNumVertices() <= 1) {
            return true;
        }

        boolean isConnected = false;
        Set<T> tmpSet = depthFirstSearch(key);

        if (getNumVertices() == tmpSet.size()) {
            isConnected = true;
        }
        return isConnected;
    }


    @Override
    public String toString() {
        return this.adjacencyMap.toString();
    }

    @Override
    public Class<T> getMyType() {
        return this.type;
    }

    @Override
    public int getNumVertices() {
        return numVertices;
    }

    @Override
    public void setNumVertices(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * if j = i+1 then we generate unique pairs (i.e., ab and ba are considered the same)
     * if j = 0 then we generate all paris (i.e., ab and ba are considered different)
     * @param a
     * @return
     */
    public ArrayList<String[]> findUniquePairs(ArrayList<String> a) {
        final ArrayList<String[]> pairs = new ArrayList<>();
        for (int i = 0; i < a.size(); ++i) {
            for (int j = 0; j < a.size(); ++j) {
                if (a.get(i).equals(a.get(j))) continue;
                pairs.add(new String[]{a.get(i), a.get(j)});
            }
        }
        return pairs;
    }

    void populateGraph(int numVertices, ArrayList<T> listOfValues) {
        for (int i = 0; i < numVertices; i++) {
            Faker faker = new Faker();
            String name = faker.name().firstName();
            addVertex((T) name);
            listOfValues.add((T) name);
        }
    }

    /**
     * Helper method to populate the graph with test data
     * Five vertices and 6 edges
     * @param graph
     */
    void initGraph(BasicGraph<String> graph) {
        graph.addVertex("Suresh");
        graph.addVertex("Meyyappan");
        graph.addVertex("Pawel");
        graph.addVertex("Carolyn");
        graph.addVertex("Stephanie");
        graph.setNumVertices(5);
        graph.addEdge("Suresh", "Meyyappan");
        graph.addEdge("Suresh", "Carolyn");
        graph.addEdge("Meyyappan", "Pawel");
        graph.addEdge("Carolyn", "Pawel");
        graph.addEdge("Meyyappan", "Stephanie");
        graph.addEdge("Carolyn", "Stephanie");
    }

    class Vertex {
        T value;
        Vertex(T value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (value == null) {
                return other.value == null;
            } else return value.equals(other.value);
        }

        @Override
        public String toString() {
            return value.toString();
        }

        private BasicGraph<T> getOuterType() {
            return BasicGraph.this;
        }
    }
} // end abstract class BasicGraph
