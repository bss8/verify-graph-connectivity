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

/**
 * Canvas chart based on: http://www.tutorialspark.com/html5/HTML5_Canvas_Graphs_Charts.php
 *
 */

$(document).ready(function () {
    let numVerticesInput = $('#numVertices');
    let directedSelect = $('#directed');
    let connectVerticesBtn = $('#connectVerticesBtn');
    let predefinedSimulationBtn = $('#predefinedSimulationBtn');
    let downloadResultsBtn = $('#downloadResultsBtn');
    let numRunsInput = $('#numRuns');
    let origin = window.location.origin;
    let body = $("body");
    let directedRes;
    let undirectedRes;

    // Canvas variables
    let context ;
    let sections;
    let xScale;
    let yScale;

    console.log(origin);

    connectVerticesBtn.click(function () {
        let numVertices = numVerticesInput.val();
        let isUndirected = directedSelect.is(':checked');

        console.log("N: " + numVertices);
        console.log("isUndirected: " + isUndirected);

        body.addClass("loading");
        $.get(origin + "/api/passGraphParams", {
            "numVertices": numVertices,
            "isUndirected": isUndirected
        }) .done(function(data) {
            console.log( "Invoking /api/passGraphParams for a single graph. # edges to connect: " + data );
            $('#singleGraphResult').val(data.toString());
            body.removeClass("loading");
        });

    });

    downloadResultsBtn.click(function () {
        if (directedRes != null && undirectedRes != null) {
            console.log("Downloading results CSV file.");
            let data = '\n'; // workaround to separate <xml> start tag on first line
            data += 'Directed,Undirected' + '\n';
            for (let i = 0; i < directedRes.length; i++) {
                data += directedRes[i] + "," + undirectedRes[i] + "\n";
            }

            let hiddenElement = document.createElement('a');
            hiddenElement.href = 'data:text/csv;charset=utf-8,' + encodeURI(data);
            hiddenElement.target = '_blank';
            hiddenElement.download = 'simulation_results.csv';
            hiddenElement.click();
        } else {
            console.log("Must run simulation first to obtain a result set!");
            alert("Must run simulation first to obtain a result set!");
        }
    });

    predefinedSimulationBtn.click(function () {
        body.addClass("loading");
        let numRuns = numRunsInput.val();
        $.get(origin + "/api/executePredefinedSimulation", {
            "numRuns": numRuns,
        }) .done(function(data) {
                console.log( "Invoking /api/executePredefinedSimulation for a simulation with preset values. \n" +
                    "Results: " + data);
                let jsonData = JSON.parse(data);
                directedRes = jsonData.directedResults;
                undirectedRes = jsonData.undirectedResults;
                console.log("directed: " + jsonData.directedResults);
                context.strokeStyle="#1648ff";
                plotData(jsonData.directedResults);
                context.strokeStyle="#e39934";
                plotData(jsonData.undirectedResults);
                body.removeClass("loading");
        });
    });

    function init() {
        sections = 31;
        let maxVal = 130;
        let minVal = -1;
        const stepSize = 10;
        const columnSize = 50;
        const rowSize = 25;
        const margin = 5;
        const xAxis = [];
        for (let i = -1; i < 31; i++) {
            xAxis.push(i);
        }

        let canvas = document.getElementById("canvas");
        context = canvas.getContext("2d");
        context.fillStyle = "#ff000a";
        context.font = "20 pt Verdana";

        yScale = (canvas.height - columnSize - margin) / (maxVal - minVal);
        xScale = (canvas.width - rowSize) / sections;

        context.strokeStyle="#000000"; // color of grid lines
        context.beginPath();
        // print Parameters on X axis, and grid lines on the graph
        for (let i=1;i<=sections;i++) {
            let x = i * xScale;
            context.fillText(xAxis[i], x,columnSize - margin);
            context.moveTo(x, columnSize);
            context.lineTo(x, canvas.height - margin);
        }
        // print row header and draw horizontal grid lines
        let count =  0;
        for (scale = maxVal; scale >= minVal; scale = scale - stepSize) {
            let y = columnSize + (yScale * count * stepSize);
            context.fillText(scale, margin,y + margin);
            context.moveTo(rowSize, y);
            context.lineTo(canvas.width, y);
            count++;
        }
        context.stroke();

        context.translate(rowSize,canvas.height + minVal * yScale);
        context.scale(1,-1 * yScale);
    }
    init();

    function plotData(dataSet) {
        context.beginPath();
        context.moveTo(0, dataSet[0]);
        for (let i=1; i < sections; i++) {
            context.lineTo(i * xScale, dataSet[i]);
        }
        context.stroke();
    }
});