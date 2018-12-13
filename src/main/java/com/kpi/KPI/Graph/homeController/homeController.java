package com.kpi.KPI.Graph.homeController;


import com.kpi.KPI.Graph.constant.TemplateNamesConstants;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
    public class homeController {
/*

    @RequestMapping(value = "/")
    public String index() {
        return TemplateNamesConstants.INDEX;
    }
*/



    @RequestMapping(value = "/")
    public String index() {


        return "index";
    }
    @RequestMapping(value = "/main")
    public String login() {


        return "main";
    }
}

