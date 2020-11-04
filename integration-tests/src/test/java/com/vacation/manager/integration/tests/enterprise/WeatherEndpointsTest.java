package com.vacation.manager.integration.tests.enterprise;

import com.intuit.karate.junit5.Karate;

public class WeatherEndpointsTest {


    @Karate.Test
    Karate test(){
        return Karate.run("./feature/enterprise").relativeTo(getClass());
    }
}
