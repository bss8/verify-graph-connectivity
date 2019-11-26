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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Value("${TARGET:World}")
    String message;

    @GetMapping("/hello")
    String hello() {
        return "Graphs says Hello " + message + "!";
    }

    @GetMapping("/api/executePredefinedSimulation")
    void executePredefinedSimulation() {
        // TODO: execute predefined simulation model with static values
    }

    @GetMapping("/api/passGraphParams")
    void passGraphParams(@RequestParam String numVertices, @RequestParam String isBiDirectional) {
        Logger logger = Logger.getLogger(BaseController.class.getName());
        logger.info("numVertices: " + numVertices);
        logger.info("isBiDirectional: " + isBiDirectional);
    }
}
