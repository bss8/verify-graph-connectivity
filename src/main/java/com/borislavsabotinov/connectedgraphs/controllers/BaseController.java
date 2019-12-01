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

package com.borislavsabotinov.connectedgraphs.controllers;

import java.util.logging.Logger;

import com.borislavsabotinov.connectedgraphs.graphs.*;
import com.borislavsabotinov.connectedgraphs.simulation.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    Logger logger = Logger.getLogger(BaseController.class.getName());
    @Value("${TARGET:World}")
    String message;

    @GetMapping("/hello")
    String hello() {
        return "Graphs says Hello " + message + "!";
    }

    @GetMapping("/api/executePredefinedSimulation")
    String executePredefinedSimulation(@RequestParam String numRuns) {
        Driver driver = new Driver();
        String simResults = driver.executePredefinedSimulation(Integer.parseInt(numRuns));
        logger.info("simResults: " + simResults);
        return simResults;
    }

    @GetMapping("/api/passGraphParams")
    int passGraphParams(@RequestParam String numVertices, @RequestParam String isUndirected) {

        logger.info("numVertices: " + numVertices);
        logger.info("isUndirected: " + isUndirected);

        int numEdgesToConnect = determineConnectivity(Boolean.parseBoolean(isUndirected), Integer.parseInt(numVertices));
        logger.info("# of (randomly generated) edges it takes to connect graph: " + numEdgesToConnect);
        return numEdgesToConnect;
    }

    private int determineConnectivity(boolean isUndirected, int numVertices) {
        int numEdgesToConnect;

        if (isUndirected) {
            UndirectedGraph<String> undirectedGraph = new UndirectedGraph<>(String.class);
            numEdgesToConnect = undirectedGraph.determineNumEdges(numVertices);
        } else {
            DirectedGraph<String> directedGraph = new DirectedGraph<>(String.class);
            numEdgesToConnect = directedGraph.determineNumEdges(numVertices);
        }

        return numEdgesToConnect;
    }
}
