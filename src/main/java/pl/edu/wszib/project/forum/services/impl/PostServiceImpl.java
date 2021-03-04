package pl.edu.wszib.project.forum.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.project.forum.dao.IPostDAO;
import pl.edu.wszib.project.forum.dao.IThreadDAO;
import pl.edu.wszib.project.forum.dao.IUserDAO;
import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.Post;
import pl.edu.wszib.project.forum.services.IPostService;

import java.util.Date;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostDAO postDAO;
    @Autowired
    IThreadDAO threadDAO;
    @Autowired
    IUserDAO userDAO;

    @Override
    public void createPostInThread(Post post, int threadId, int userId) {
        System.out.println(post.getContent() + "service post");
        post.setUser(this.userDAO.getUserById(userId));
        post.setDateTime(new Date());
        post.setForumThread(this.threadDAO.getThreadById(threadId));
        this.postDAO.createPost(post);
    }

    @Override
    public Post getPostById(int id) {
        return this.postDAO.getPostById(id);
    }

    @Override
    public void deletePost(Post post) {
        this.postDAO.deletePost(post);
    }

    @Override
    public int countPost(int threadId) {
        return this.postDAO.countPosts(threadId);
    }
}
