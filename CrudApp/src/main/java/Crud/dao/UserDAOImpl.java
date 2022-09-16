package Crud.dao;

import Crud.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManger;
    private final SessionFactory sessionFactory;


    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        return entityManger.createQuery("select u from User u ", User.class).getResultList();
    }

    @Override
    @Transactional
    public User getUserbyId(int id) {
        return entityManger.find(User.class, id);

    }

    @Override
    @Transactional
    public void add(User user) {
        entityManger.persist(user);
    }

    @Override
    @Transactional
    public void edit(int id, User userUpdate) {
        entityManger.merge(userUpdate);

    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManger.remove(getUserbyId(id));

    }

}
