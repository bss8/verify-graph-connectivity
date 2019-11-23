package com.borislavsabotinov.connectedgraphs.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewsController {
    @RequestMapping(value={"/graphs"})
    public String graphs() {
        return "graphs";
    }
}
