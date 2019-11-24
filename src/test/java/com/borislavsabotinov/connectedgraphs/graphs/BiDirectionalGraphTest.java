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

import org.junit.jupiter.api.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BiDirectionalGraphTest {
    BiDirectionalGraph<String> graph;

    @BeforeEach
    void init() {
        graph = new BiDirectionalGraph<>();
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

    @AfterEach
    void cleanUp() {
        graph = null;
    }

    @Test
    void addEdge() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void depthFirstTraversal() {
        Set<String> set = graph.depthFirstTraversal("Bob");
        String setStr = set.toString();
        System.out.println("If Rob is root: [Rob, Maria, Alice, Mark, Bob]");
        System.out.println("If Bob is root: [Bob, Rob, Maria, Alice, Mark]");
        System.out.println("actual, given Bob as root: " + setStr);
        assertEquals("[Bob, Rob, Maria, Alice, Mark]", setStr);
    }
}