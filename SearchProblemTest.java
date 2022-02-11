package com.hasanzade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchProblemTest {

    @Test
    void noPathTest1() {
        int[] pitchers = {3, 6};
        int target = 2;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(-1, problem.solve());
    }

    @Test
    void noPathTest2() {
        int[] pitchers = {2};
        int target = 143;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(-1, problem.solve());
    }

    @Test
    void test1() {
        int[] pitchers = {3, 5};
        int target = 17;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(9, problem.solve());
    }

    @Test
    void test2() {
        int[] pitchers = {3, 5, 9};
        int target = 34;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(11, problem.solve());
    }

    @Test
    void test3() {
        int[] pitchers = {3, 5};
        int target = 14;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(8, problem.solve());
    }

    @Test
    void test4() {
        int[] pitchers = {2, 5, 6, 72};
        int target = 143;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(7, problem.solve());
    }

    @Test
    void test5() {
        int[] pitchers = {3, 5};
        int target = 4;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(6, problem.solve());
    }

    @Test
    void test6() {
        int[] pitchers = {2, 3, 5, 19, 121, 852};
        int target = 11443;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(36, problem.solve());
    }

    @Test
    void test7() {
        int[] pitchers = {3, 5, 8, 10};
        int target = 207;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(43, problem.solve());
    }

    @Test
    void test8() {
        int[] pitchers = {1, 4, 10, 15, 22};
        int target = 181;
        SearchProblem problem = new SearchProblem(pitchers, target);
        assertEquals(19, problem.solve());
    }
}