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
    let runSimulationBtn = $('#runSimulationBtn');
    let stringType = $('#strType');
    let origin = window.location.origin;

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

    runSimulationBtn.click(function () {
        $.get(origin + "/api/executePredefinedSimulation")
            .done(function() {
                console.log( "Invoking /api/executePredefinedSimulation for a simulation with preset values.");
        });
    });

});