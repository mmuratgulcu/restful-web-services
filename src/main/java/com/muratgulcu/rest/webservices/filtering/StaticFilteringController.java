package com.muratgulcu.rest.webservices.filtering;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/static")
public class StaticFilteringController {

    @RequestMapping(method = RequestMethod.GET, path = "/filtering")
    public SomeBean retrieveSomeBean(){
        return new SomeBean("value1","value2","value3");
    }

    @GetMapping(path="/filtering-list")
    public List<SomeBean> retrieveListOfSomeBeans(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value1","value2","value3"));
    }
}
