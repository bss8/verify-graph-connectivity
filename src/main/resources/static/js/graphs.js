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

$(document).ready(function () {
    let numVerticesInput = $('#numVertices');
    let biDirectionalSelect = $('#biDirectional');
    let connectVerticesBtn = $('#connectVerticesBtn');
    let predefinedSimulationBtn = $('#predefinedSimulationBtn');
    let origin = window.location.origin;
    let body = $("body");

    // Canvas variables
    let context ;
    let sections;
    let xScale;
    let yScale;

    console.log(origin);

    connectVerticesBtn.click(function () {
        let numVertices = numVerticesInput.val();
        let isBiDirectional = biDirectionalSelect.is(':checked');

        console.log("N: " + numVertices);
        console.log("isBiDirectional: " + isBiDirectional);

        $.get(origin + "/api/passGraphParams", {
            "numVertices": numVertices,
            "isBiDirectional": isBiDirectional
        }) .done(function(data) {
            console.log( "Invoking /api/passGraphParams for a single graph. # edges to connect: " + data );
            $('#singleGraphResult').val(data.toString());
        });

    });

    predefinedSimulationBtn.click(function () {
        body.addClass("loading");
        $.get(origin + "/api/executePredefinedSimulation")
            .done(function(data) {
                console.log( "Invoking /api/executePredefinedSimulation for a simulation with preset values. \n" +
                    "Results: " + data);
                let jsonData = JSON.parse(data);
                console.log("biDirectional: " + jsonData.biDirectionalResults);
                context.strokeStyle="#FF0066";
                plotData(jsonData.biDirectionalResults);
                context.strokeStyle="#9933FF";
                plotData(jsonData.uniDirectionalResults);
                body.removeClass("loading");
        });
    });

    function init() {
        // set these values for your data
        sections = 4;
        let maxVal = 100;
        let minVal = 0;
        const stepSize = 10;
        const columnSize = 25;
        const rowSize = 25;
        const margin = 10;
        const xAxis = [" ", "5", "10", "15", "20"];

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
        for (let i=1; i<dataSet.length; i++) {
            context.lineTo(i * xScale, dataSet[i]);
        }
        context.stroke();
    }
});