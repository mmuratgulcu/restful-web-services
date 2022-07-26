package com.muratgulcu.rest.webservices.versioning.withURIs;

public class Name {

    private String name;
    private String surname;

    public Name() {
    }

    public Name(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
