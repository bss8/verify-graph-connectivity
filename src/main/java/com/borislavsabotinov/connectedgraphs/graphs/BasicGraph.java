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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public abstract class BasicGraph<T extends Comparable<? super T>> implements Graph<T> {
    Map<Vertex, List<Vertex>> adjacencyMap;

    BasicGraph() {
        this.adjacencyMap = new HashMap<Vertex, List<Vertex>>();
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
    public abstract void addEdge(T value1, T value2);

    @Override
    public abstract void removeEdge(T value1, T value2);

    @Override
    public List<Vertex> getAdjacentVertices(T value) {
        return adjacencyMap.get(new Vertex(value));
    }

    @Override
    public String toString() {
        return this.adjacencyMap.toString();
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
