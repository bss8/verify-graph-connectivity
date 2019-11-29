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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.System.currentTimeMillis;

public class Driver {
    Logger logger = Logger.getLogger(Driver.class.getName());
    BiDirectionalGraph<String> biDirectionalGraph;
    UniDirectionalGraph<String> uniDirectionalGraph;

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
        logger.info(biDirectionalGraph.toString());
        logger.info("arrayList size: " + arrayList.size());

        while (! biDirectionalGraph.isConnected(arrayList.get(0))) {
            String name1 = arrayList.get(getRandomNumberInRange(0, arrayList.size()-1));
            String name2 = arrayList.get(getRandomNumberInRange(0, arrayList.size()-1));
            logger.info(name1 + name2);

            if (!name1.equals(name2)) {
                boolean isAdded = biDirectionalGraph.addEdge(name1, name2);
                if(isAdded) {
                    numEdges++;
                }
            }
        }

        logger.info(biDirectionalGraph.toString());

        return numEdges;
    }

    public int determineUniConnectivity(int numVertices) {
        return 0;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
} // end class Driver
