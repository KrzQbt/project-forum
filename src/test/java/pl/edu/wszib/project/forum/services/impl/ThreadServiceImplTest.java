package pl.edu.wszib.project.forum.services.impl;

import org.junit.Assert;
import org.junit.Test;

public class ThreadServiceImplTest {

    // for default page size: 5
    @Test
    public void calculatePagesTest(){
        int posts = 27;
        int expectedResult = 6;
        ThreadServiceImpl threadService = new ThreadServiceImpl();

        int result = threadService.calculatePages(posts);

        Assert.assertEquals(expectedResult,result);
    }

    @Test
    public void  getFirstTest(){
        int a = 5;
        int expected = 20;
        ThreadServiceImpl threadService = new ThreadServiceImpl();
        int result = threadService.getFirst(a);

        Assert.assertEquals(expected,result);

    }

    @Test
    public void  getMaxTest(){
        int a =5;
        int expected = 25;
        ThreadServiceImpl threadService = new ThreadServiceImpl();
        int result =threadService.getMax(a);

        Assert.assertEquals(expected,result);
    }





}
