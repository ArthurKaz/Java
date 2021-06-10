package com.company;

import org.junit.Assert;

public class MainTest {

    @org.junit.Test
    public void MinTest() throws Exception {
        int[] mass = {-5, -9, 4, 5, 9, -1, 8};

        Assert.assertEquals(Main.min(mass), 5);
    }

    @org.junit.Test
    public void MinFailTest() throws Exception {
        //int [] mass = {2,5,9};
        int[] mass = new int[0];
        try {
            Assert.assertNull(Main.min(mass));

        } catch (Exception e) {
        }
    }

    @org.junit.Test
    public void productTest() throws Exception {
        int[] mass = { 5,4,0,8,6,7,0,5};
        int result = 8*6*7;
        Assert.assertEquals(Main.product(mass),result);

    }

    @org.junit.Test
    public void productFailTest1() throws Exception {
        int[] mass = new int[0];
        try {
            Assert.assertNull(Main.product(mass));

        } catch (Exception e) {
        }
        mass = new int[]{5,0,5};
        try {
            Assert.assertNull(Main.product(mass));

        } catch (Exception e) {
        }
        mass = new int[]{5,0,0};
        try {
            Assert.assertNull(Main.product(mass));

        } catch (Exception e) {
        }
        mass = new int[]{5,8,4};
        try {
            Assert.assertNull(Main.product(mass));

        } catch (Exception e) {
        }
    }


    @org.junit.Test
    public void wrapTest() throws Exception {
        int [] mass = {2,5,9,4,4,9,5,8};
        int [] expectedResult = {4,9,5,8,2,5,9,4};
        int [] result = Main.wrap(mass);
        for (int i = 0;i < mass.length;i++) {
            Assert.assertEquals(result[i],expectedResult[i]);
        }
    }

    @org.junit.Test
    public void wrapFailTest() throws Exception {
        int [] mass = {2,5,9};
        //int[] mass = new int[0];

        try {
            Assert.assertNull(Main.wrap(mass));

        } catch (Exception e) {
        }

        mass = new int[0];
        try {
            Assert.assertNull(Main.wrap(mass));

        } catch (Exception e) {
        }
    }
}