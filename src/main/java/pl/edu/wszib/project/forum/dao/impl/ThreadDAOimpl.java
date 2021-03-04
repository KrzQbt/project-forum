package pl.edu.wszib.project.forum.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.project.forum.dao.IThreadDAO;
import pl.edu.wszib.project.forum.model.ForumThread;

import org.hibernate.query.Query;
import pl.edu.wszib.project.forum.model.view.ThreadModel;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ThreadDAOimpl implements IThreadDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<ForumThread> getAllThreadList() {
        Session session = this.sessionFactory.openSession();
        Query<ForumThread> query = session.createQuery("FROM ForumThread");
        List<ForumThread> allThreads = null;
        try{
            allThreads = query.getResultList();
        }catch (NoResultException e){
            System.out.println("no threads in db");
        }
        session.close();
        return allThreads;
    }

    @Override
    public void createThread(ForumThread forumThread) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx =session.beginTransaction();
            session.save(forumThread);
            tx.commit();
        }catch (Exception e){
            if (tx!=null)
                tx.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public ForumThread getThreadById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<ForumThread> query = session.createQuery("FROM ForumThread WHERE id = :id");
        query.setParameter("id",id);
        ForumThread forumThread = null;
        try{
            forumThread = query.getSingleResult();
        }catch (NoResultException e){
            System.out.println("no such thread");
        }
        return forumThread;
    }

    @Override
    public ThreadModel prepareThread(ForumThread forumThread) {
        return null;
    }


    @Override
    public void deleteThread(ForumThread forumThread) {
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try {
            tx=session.beginTransaction();
            session.delete(forumThread);
            tx.commit();
        }catch (Exception e){
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
