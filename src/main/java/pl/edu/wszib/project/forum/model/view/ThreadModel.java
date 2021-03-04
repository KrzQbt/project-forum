package pl.edu.wszib.project.forum.model.view;

// klasa zbiera wszystkie skladniki do wyswietlenia wątku

import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.Post;

import java.util.List;

public class ThreadModel {
    private int threadId;
    private String threadName;
    private List<Post> postsInThread;

    public void threadInfoPrepare(ForumThread forumThread){
        this.setThreadId(forumThread.getId());
        this.setThreadName(forumThread.getName());
    };

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public List<Post> getPostsInThread() {
        return postsInThread;
    }

    public void setPostsInThread(List<Post> postsInThread) {
        this.postsInThread = postsInThread;
    }
}
