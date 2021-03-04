package pl.edu.wszib.project.forum.services;

import pl.edu.wszib.project.forum.model.Post;

public interface IPostService {
    void createPostInThread(Post post, int threadId, int userId);
    Post getPostById(int id);
    void deletePost(Post post);
    int countPost(int threadId);


}
