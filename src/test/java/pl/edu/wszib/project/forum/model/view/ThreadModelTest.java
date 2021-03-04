package pl.edu.wszib.project.forum.model.view;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.project.forum.model.ForumThread;

public class ThreadModelTest {
    @Test
    public void threadInfoPrepareTest(){
        ThreadModel threadModel = new ThreadModel();

        String expectedName ="testName";
        int expectedId = 1;
        String desc = "testDesc";
        ForumThread forumThread = new ForumThread(expectedId,expectedName,desc);




        threadModel.threadInfoPrepare(forumThread);

        Assert.assertEquals(expectedName,threadModel.getThreadName());
        Assert.assertEquals(expectedId,threadModel.getThreadId());




    }


}
