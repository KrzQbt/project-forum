package pl.edu.wszib.project.forum.dao;

import pl.edu.wszib.project.forum.model.ForumThread;
import pl.edu.wszib.project.forum.model.view.ThreadModel;

import java.util.List;

public interface IThreadDAO {
    List<ForumThread> getAllThreadList();
    void createThread(ForumThread forumThread);
    ThreadModel prepareThread(ForumThread forumThread);
    ForumThread getThreadById(int id);
    void deleteThread(ForumThread forumThread);

}
