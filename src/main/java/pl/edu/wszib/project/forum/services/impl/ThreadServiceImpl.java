package pl.edu.wszib.project.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.project.forum.dao.IPostDAO;
import pl.edu.wszib.project.forum.dao.IThreadDAO;
import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.Post;
import pl.edu.wszib.project.forum.model.view.ThreadModel;
import pl.edu.wszib.project.forum.services.IThreadService;

import java.util.List;

@Service
public class ThreadServiceImpl implements IThreadService {
    @Autowired
    IThreadDAO threadDAO;
    @Autowired
    IPostDAO postDAO;

    @Override
    public List<ForumThread> getAllThreadList() {
        List<ForumThread> allThreads = threadDAO.getAllThreadList();
        return allThreads;
    }

    @Override
    public void createThread(ForumThread forumThread) {
        this.threadDAO.createThread(forumThread);
    }

    @Override
    public ThreadModel displayThread(ForumThread forumThread) {
        ThreadModel tm = new ThreadModel();
        tm.threadInfoPrepare(forumThread);
        List<Post> posts = this.postDAO.getPostsInThread(tm.getThreadId());
        tm.setPostsInThread(posts);
        return tm;
    }

    @Override
    public ThreadModel displayThread(int id) {
        ThreadModel tm = new ThreadModel();
        tm.threadInfoPrepare(this.threadDAO.getThreadById(id));
        List<Post> posts = this.postDAO.getPostsInThread(tm.getThreadId());
        tm.setPostsInThread(posts);
        return tm;
    }


    @Override
    public ThreadModel displayPaginatedThread(int threadId, int page) {

        ThreadModel tm = new ThreadModel();
        tm.threadInfoPrepare(this.threadDAO.getThreadById(threadId));

        int postsNumber = postDAO.countPosts(threadId).intValue();
        int pages = this.calculatePages(postsNumber);
        int first=this.getFirst(page), last =this.getMax(page);
        // policzyc start i max
        // policzyc ile, pobrac, jesli size == to wolamy display trhed


        List<Post> posts = this.postDAO.getPostsInThreadPage(threadId,first, last);
//        List<Post> posts = this.postDAO.getPostsInThread(tm.getThreadId());
//        calculatePages();
//        posts.size();


        tm.setPostsInThread(posts);
        return tm;
    }

    @Override
    public int getFirst( int page) {
        return 5*(page-1);
    }

    @Override
    public int getMax(int page) {
        // default pagination 5 per site
        return 5 *page ;
    }

    @Override
    public int calculatePages(int posts) {
        if(posts <= 0)
            return 0;

        int pageSize = 5;   // default page size 5
        if (posts <= pageSize)
            return 1;

        int lastPageSize = posts%pageSize;
        int pages;
        if (lastPageSize ==0) {
            pages = posts / pageSize;
            return pages;
        }

        pages = (posts/pageSize) + 1;
        return pages;


    }


    public void deleteThread(int id){
        List<Post> posts = this.postDAO.getPostsInThread(id);
        for (Post post: posts)
            this.postDAO.deletePost(post);

        ForumThread thread = this.threadDAO.getThreadById(id);
        this.threadDAO.deleteThread(thread);
    };
}
