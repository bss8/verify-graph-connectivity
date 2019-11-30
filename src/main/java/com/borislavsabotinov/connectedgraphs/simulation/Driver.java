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

import com.borislavsabotinov.connectedgraphs.graphs.BiDirectionalGraph;
import com.borislavsabotinov.connectedgraphs.graphs.UniDirectionalGraph;
import com.github.javafaker.Faker;
import com.google.gson.Gson;

import java.util.*;
import java.util.logging.Logger;

public class Driver {
    Logger logger = Logger.getLogger(Driver.class.getName());
    BiDirectionalGraph<String> biDirectionalGraph;
    UniDirectionalGraph<String> uniDirectionalGraph;

    public Driver() {
    }

    public Driver(BiDirectionalGraph<String> biDirectionalGraph) {
        this.biDirectionalGraph = biDirectionalGraph;
    }

    public Driver(UniDirectionalGraph<String> uniDirectionalGraph) {
        this.uniDirectionalGraph = uniDirectionalGraph;
    }

    public int determineBiConnectivity(int numVertices) {
        int numEdges = 0;
        biDirectionalGraph.setNumVertices(numVertices);
        ArrayList<String> arrayList = new ArrayList<>();

        //populate graph with vertices
        for (int i = 0; i < numVertices; i++) {
            Faker faker = new Faker();
            String randomName = faker.name().firstName();
            biDirectionalGraph.addVertex(randomName);
            arrayList.add(i, randomName);
        }

        logger.info("array: " + arrayList.toString());
        logger.info("BiDirectional Graph obj: " + biDirectionalGraph.toString());
        logger.info("arrayList size: " + arrayList.size());

        for (int i = 0; i < arrayList.size(); i++) {
            for (int j = 0; j < arrayList.size(); j++) {

            }
        }

        logger.info(biDirectionalGraph.toString());

        return numEdges;
    }

    public int determineUniConnectivity(int numVertices) {

        int numEdges = 0;
        uniDirectionalGraph.setNumVertices(numVertices);
        ArrayList<String> arrayList = new ArrayList<>();

        //populate graph with vertices
        for (int i = 0; i < numVertices; i++) {
            Faker faker = new Faker();
            String randomName = faker.name().firstName();
            uniDirectionalGraph.addVertex(randomName);
            arrayList.add(i, randomName);
        }

        logger.info("array: " + arrayList.toString());
        logger.info(uniDirectionalGraph.toString());
        logger.info("arrayList size: " + arrayList.size());

        ArrayList<String[]> uniquePairs = findUniquePairs(arrayList);
        logger.info("uniquePairs: " + Arrays.deepToString(uniquePairs.toArray()));

        while ((arrayList.size() > 0) &&
                (!uniDirectionalGraph.isConnected(arrayList)) ) {
            int randIntInRange = getRandomNumberInRange(0, arrayList.size() - 1);
            String[] randomUniquePair = uniquePairs.get(randIntInRange);
            uniquePairs.remove(randomUniquePair);
            boolean isAdded = uniDirectionalGraph.addEdge(randomUniquePair[0], randomUniquePair[1]);
            numEdges++;
        }

        return numEdges;
    }

    public String executePredefinedSimulation() {
        int numRuns = 20;
        biDirectionalGraph = new BiDirectionalGraph<>(String.class);
        uniDirectionalGraph = new UniDirectionalGraph<>(String.class);
        ArrayList<Integer> biDirectionalResults = new ArrayList<>();
        ArrayList<Integer> uniDirectionalResults = new ArrayList<>();

        for (int i = 0; i < numRuns; i ++) {
            int numEdgesToConnectBiDirectionalGraph = determineBiConnectivity(i);
            int numEdgesToConnectUniDirectionalGraph = determineUniConnectivity(i);

            biDirectionalResults.add(numEdgesToConnectBiDirectionalGraph);
            uniDirectionalResults.add(numEdgesToConnectUniDirectionalGraph);
        }

        Map<String, ArrayList<Integer>> simulationResults = new TreeMap<>();
        simulationResults.put("biDirectionalResults", biDirectionalResults);
        simulationResults.put("uniDirectionalResults", uniDirectionalResults);

        Gson gson = new Gson();
        return gson.toJson(simulationResults);
    }

    public int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public ArrayList<String[]> findUniquePairs(ArrayList<String> a) {
        final ArrayList<String[]> pairs = new ArrayList<>();
        for (int i = 0; i < a.size(); ++i) {
            for (int j = i + 1; j < a.size(); ++j) {
                pairs.add(new String[]{a.get(i), a.get(j)});
            }
        }
        return pairs;
    }
} // end class Driver
