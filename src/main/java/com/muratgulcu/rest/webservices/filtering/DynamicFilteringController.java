package com.muratgulcu.rest.webservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dynamic")
public class DynamicFilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieveSomeBean(){

        SomeBeanDynamic someBeanDynamic = new SomeBeanDynamic("value1", "value2", "value3");


        MappingJacksonValue mapping = new MappingJacksonValue(someBeanDynamic);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        mapping.setFilters(filterProvider);

        return mapping;
    }

    @GetMapping(path="/filtering-list")
    public MappingJacksonValue retrieveListOfSomeBeans(){

        List<SomeBeanDynamic> someBeanDynamics = Arrays.asList(new SomeBeanDynamic("value1", "value2", "value3"),
                new SomeBeanDynamic("value12", "value222", "value333"));

        MappingJacksonValue mapping = new MappingJacksonValue(someBeanDynamics);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("DynamicFilter",filter);
        mapping.setFilters(filterProvider);

        return mapping;
    }
}
