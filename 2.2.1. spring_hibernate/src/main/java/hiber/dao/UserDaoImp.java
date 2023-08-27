package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public User getUserAndCar(String model, int series) {
      try {


         Query query = sessionFactory.getCurrentSession()
                 .createQuery("FROM User AS u WHERE  u.bmwCar.model = :model AND u.bmwCar.series = :series");
         query.setParameter("model", model);
         query.setParameter("series", series);
         return (User) query.getSingleResult();
      } catch (NoResultException e) {
         return null;
      }
   }
   @Override
   public void clearUser(Long id){
      String hql = "DELETE FROM User WHERE id = :hui";
      Session session = sessionFactory.getCurrentSession();
      User user = session.get(User.class, id);
      session.delete(user);

   }



}
