package org.test.jwttest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "say hello";
    }
}
