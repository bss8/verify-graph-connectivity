package com.borislavsabotinov.connectedgraphs.simulation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {
    Driver driver;
    ArrayList<String> list;

    @BeforeEach
    void init() {
        driver = new Driver();
        list = new ArrayList<>();
        list.add("Boris");
        list.add("Melody");
        list.add("John");
        list.add("Terry");
        list.add("Mike");
        list.add("Corey");
        list.add("Sheryl");
    }

    @Test
    void findUniquePairs() {

        ArrayList<String[]> uniquePairs = driver.findUniquePairs(list);
        int idx = driver.getRandomNumberInRange(0, list.size()-1);
        System.out.println(uniquePairs.get(idx));
    }
}