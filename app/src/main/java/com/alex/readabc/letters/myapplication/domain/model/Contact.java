package com.alex.readabc.letters.myapplication.domain.model;

/**
 * Created on 6/24/18.
 */

public class Contact {
    private String name;

    public Contact() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


//{"user":
//        {"created_at":"2017-03-21T121241.989Z",
//        "family":{"created_at":"2017-03-21T122428.328Z","created_by":"66d4fb14-7ec7-4ce6-9aa7-41c12d6d9de2","id":"47b4d072-f299-45f7-8bb2-4da30c104fcb",
//        "members":[{"first_name":"Fred","id":"8f619c4b-b9fe-48b1-b1bf-515eda28b13a","is_authenticated":true,"last_name":"Flintstone"},
//        {"first_name":"Pebbles","id":"5c1bfdc6-b0c8-49d9-b2fb-c9756a1ea5b6","is_authenticated":false,"last_name":"Rubble"}],
//        "name":"Flintstones","updated_at":"2017-03-21T122428.328Z"},"first_name":"Fred","id":"8f619c4b-b9fe-48b1-b1bf-515eda28b13a","is_authenticated":true,"last_name":"Flintstone","updated_at":"2017-03-21T121241.989Z"}}
