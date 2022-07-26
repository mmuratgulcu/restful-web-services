package com.muratgulcu.rest.webservices.versioning;

import com.muratgulcu.rest.webservices.versioning.withURIs.Name;
import com.muratgulcu.rest.webservices.versioning.withURIs.PersonV1;
import com.muratgulcu.rest.webservices.versioning.withURIs.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping("/v1/person")
    public PersonV1 personV1(){
        return new PersonV1("Murat Gülcü");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Murat","Gülcü"));
    }

    @GetMapping(value = "/person/param",params = "version=1")
    public PersonV1 paramv1(){
        return new PersonV1("Murat Gülcü");
    }

    @GetMapping(value = "/person/param",params = "version=2")
    public PersonV2 paramv2(){
        return new PersonV2(new Name("Murat","Gülcü"));
    }

    @GetMapping(value = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 headerv1(){
        return new PersonV1("Murat Gülcü");
    }

    @GetMapping(value = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 headerv2(){
        return new PersonV2(new Name("Murat","Gülcü"));
    }

    @GetMapping(value = "/person/produces",produces = "application/person-v1+json")
    public PersonV1 producesv1(){
        return new PersonV1("Murat Gülcü");
    }

    @GetMapping(value = "/person/produces",produces = "application/person-v2")
    public PersonV2 producesv2(){
        return new PersonV2(new Name("Murat","Gülcü"));
    }
}
