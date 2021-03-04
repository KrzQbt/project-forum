package pl.edu.wszib.project.forum.services;

import org.springframework.stereotype.Service;
import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.view.ThreadModel;

import java.util.List;


public interface IThreadService {
    // show all threads, modify thread info, delete threads with all posts
    List<ForumThread> getAllThreadList();
    void createThread(ForumThread forumThread);
    ThreadModel displayThread(ForumThread forumThread);
    ThreadModel displayThread(int id);
    void deleteThread(int id);
    ThreadModel displayPaginatedThread(int id, int page);
    int calculatePages(int posts);
    int getFirst(int page);
    int getMax( int page);
    }
