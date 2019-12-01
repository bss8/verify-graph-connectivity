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

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {
    UndirectedGraph<String> graph;

    @BeforeEach
    void init() {
        graph = new UndirectedGraph<>(String.class);
        graph.addVertex("Suresh");
        graph.addVertex("Meyyappan");
        graph.addVertex("Pawel");
        graph.addVertex("Carolyn");
        graph.addVertex("Stephanie");
        graph.addEdge("Suresh", "Meyyappan");
        graph.addEdge("Suresh", "Carolyn");
        graph.addEdge("Meyyappan", "Pawel");
        graph.addEdge("Carolyn", "Pawel");
        graph.addEdge("Meyyappan", "Stephanie");
        graph.addEdge("Carolyn", "Stephanie");
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
    void depthFirstSearch() {
        Set<String> set = graph.depthFirstSearch("Suresh");
        String setStr = set.toString();
        out.println("If Carolyn is root: [Carolyn, Stephanie, Meyyappan, Pawel, Suresh]");
        out.println("If Suresh is root: [Suresh, Carolyn, Stephanie, Meyyappan, Pawel]");
        out.println("actual, given Suresh as root: " + setStr);
        assertEquals("[Suresh, Carolyn, Stephanie, Meyyappan, Pawel]", setStr);
    }

    @Test
    void breadthFirstSearch() {
        Set<String> set = graph.breadthFirstSearch("Suresh");
        String setStr = set.toString();
        out.println("If Pawel is root: [Pawel, Meyyappan, Carolyn, Suresh, Stephanie]");
        out.println("If Suresh is root: [Suresh, Meyyappan, Carolyn, Pawel, Stephanie]");
        out.println("actual, given Suresh as root: " + setStr);
        assertEquals("[Suresh, Meyyappan, Carolyn, Pawel, Stephanie]", setStr);
    }
}