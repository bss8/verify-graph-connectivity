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
import com.borislavsabotinov.connectedgraphs.graphs.BiDirectionalGraph;
import com.borislavsabotinov.connectedgraphs.graphs.UniDirectionalGraph;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import java.util.*;
import java.util.logging.Logger;

public class Driver {
    Logger logger = Logger.getLogger(Driver.class.getName());
    BasicGraph<String> graph;
    UniDirectionalGraph<String> uniGraph;
    BiDirectionalGraph<String> biGraph;

    public String executePredefinedSimulation() {
        int numRuns = 20;
        biGraph = new BiDirectionalGraph<>(String.class);
        uniGraph = new UniDirectionalGraph<>(String.class);
        ArrayList<Integer> biDirectionalResults = new ArrayList<>();
        ArrayList<Integer> uniDirectionalResults = new ArrayList<>();

        for (int i = 0; i < numRuns; i ++) {
            int numEdgesToConnectBiDirectionalGraph = biGraph.determineNumEdges(i);
            int numEdgesToConnectUniDirectionalGraph = uniGraph.determineNumEdges(i);

            biDirectionalResults.add(numEdgesToConnectBiDirectionalGraph);
            uniDirectionalResults.add(numEdgesToConnectUniDirectionalGraph);
        }

        Map<String, ArrayList<Integer>> simulationResults = new TreeMap<>();
        simulationResults.put("biDirectionalResults", biDirectionalResults);
        simulationResults.put("uniDirectionalResults", uniDirectionalResults);

        Gson gson = new Gson();
        return gson.toJson(simulationResults);
    }
} // end class Driver
