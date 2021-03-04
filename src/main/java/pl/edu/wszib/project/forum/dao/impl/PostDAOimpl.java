package pl.edu.wszib.project.forum.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.project.forum.dao.IPostDAO;
import pl.edu.wszib.project.forum.model.Post;


import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class PostDAOimpl implements IPostDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Post> getPostsInThread(int threadId) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM pl.edu.wszib.project.forum.model.Post WHERE forumthread_id = :id");
        query.setParameter("id",threadId);
        List<Post> posts = null;
        try {
            posts = query.getResultList();
            System.out.println(posts.size() + "list size");
        } catch (NoResultException e){
            System.out.println("no posts in the thread yet");
        }
        session.close();
        return posts;
    }
    @Override
    public List<Post> getPostsInThreadPage(int threadId, int first, int last) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM pl.edu.wszib.project.forum.model.Post WHERE forumthread_id = :id");
        query.setParameter("id",threadId);
        query.setFirstResult(first);
        query.setMaxResults(last);
        List<Post> posts = null;
        try {
            posts = query.getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        session.close();
        return posts;
    }


    @Override
    public Integer countPosts(int threadId) {
        Session session = this.sessionFactory.openSession();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM pl.edu.wszib.project.forum.model.Post WHERE forumthread_id = :id");
        query.setParameter("id",threadId);
        // Problems with return value type Long, solved by casting to int
        Long posts = null;
        try {
            posts =  query.getSingleResult();
        }catch (NoResultException e){
            e.printStackTrace();
        }
        session.close();
        int postInt = posts.intValue();
        return postInt;
    }

    @Override
    public void createPost(Post post) {
        Session session = this.sessionFactory.openSession();
        System.out.println();
        System.out.println(post.getContent() + "dao post/n");
        System.out.println("poscik " + post.getDateTime());
        Transaction tx = null;
        try {
            System.out.println();
            System.out.println("in dao");
            System.out.println();
            tx = session.beginTransaction();
            session.save(post);
            tx.commit();
        }catch (Exception e){
            System.out.println(e);
            if (tx !=null)
                tx.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public Post getPostById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Post> query = session.createQuery("FROM pl.edu.wszib.project.forum.model.Post WHERE id = :id");
        query.setParameter("id",id);
        Post post = null;
        try{
            post = query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        session.close();
        return post;
    }

    @Override
    public void deletePost(Post post) {
        Session session = this.sessionFactory.openSession();
        Transaction tx =null;
        try {
            tx = session.beginTransaction();
            session.delete(post);
            tx.commit();
        }catch (Exception e){
            if (tx!=null)
                tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


}
