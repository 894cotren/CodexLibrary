package com.gery.dcc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author grey
 */
@RestController
@RequestMapping("/health")
public class HealthController {


    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }
}
