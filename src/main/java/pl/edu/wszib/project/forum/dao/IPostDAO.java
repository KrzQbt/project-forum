package pl.edu.wszib.project.forum.dao;

import pl.edu.wszib.project.forum.model.Post;

import java.util.List;

public interface IPostDAO {
    //List<Post> getAllPosts();
    List<Post> getPostsInThread(int threadId);
    void createPost(Post post);
    Post getPostById(int id);
    void deletePost(Post post);
    List<Post> getPostsInThreadPage(int threadId, int first, int last);
    Integer countPosts(int ThreadId);
}
