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

package com.borislavsabotinov.connectedgraphs.simulation;

import com.borislavsabotinov.connectedgraphs.graphs.BasicGraph;
import com.borislavsabotinov.connectedgraphs.graphs.UndirectedGraph;
import com.borislavsabotinov.connectedgraphs.graphs.DirectedGraph;
import com.google.gson.Gson;

import java.util.*;
import java.util.logging.Logger;

public class Driver {
    Logger logger = Logger.getLogger(Driver.class.getName());
    BasicGraph<String> graph;
    DirectedGraph<String> directedGraph;
    UndirectedGraph<String> undirectedGraph;

    public String executePredefinedSimulation(int numRuns) {
        Map<String, ArrayList<Integer>> simulationResults = new TreeMap<>();
        ArrayList<Integer> directedResults = new ArrayList<>();
        ArrayList<Integer> undirectedResults = new ArrayList<>();

        for (int i = 0; i <= numRuns; i ++) {
            undirectedGraph = new UndirectedGraph<>(String.class);
            directedGraph = new DirectedGraph<>(String.class);
            int numEdgesToConnectUndirectedGraph = undirectedGraph.determineNumEdges(i);
            int numEdgesToConnectDirectedGraph = directedGraph.determineNumEdges(i);

            directedResults.add(numEdgesToConnectDirectedGraph);
            undirectedResults.add(numEdgesToConnectUndirectedGraph);
        }

        simulationResults.put("directedResults", directedResults);
        simulationResults.put("undirectedResults", undirectedResults);

        Gson gson = new Gson();
        return gson.toJson(simulationResults);
    }

    public static void main(String...args) {
        Driver driver = new Driver();
        String simResult = driver.executePredefinedSimulation(25);
        System.out.println(simResult);
    }
} // end class Driver
