package database;

import java.util.ArrayList;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import userManagement.UserProfile;

public class UserProfileRepository implements BasicDAO<UserProfile>{

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<UserProfile> getAll() {
		Session session = null;
        ArrayList<UserProfile> result = null;
        try {
            session = HibernateUtil.getSession();
            // CriteriaBuilder builder = session.getCriteriaBuilder();
            // CriteriaQuery<UserProfile> query = builder.createQuery(UserProfile.class);
            // result = (ArrayList<UserProfile>)session.createQuery(query).getResultList();
            result = (ArrayList<UserProfile>) session.createCriteria(UserProfile.class).list();
            for(UserProfile usrPr: result){
            	Hibernate.initialize(usrPr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
	}

	@Override
	public UserProfile getByID(int id) {
		Session session = null;
        UserProfile user = null;
        try {
            session = HibernateUtil.getSession();
            user = session.get(UserProfile.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
	}

	@Override
	public int save(UserProfile obj) {
		Session session = null;
		Transaction tx = null;
		Integer userID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			userID = (Integer) session.save(obj);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if (session!=null && session.isOpen()){
				session.close();
			}
		}
		return userID;
	}

	@Override
	public boolean update(UserProfile obj) {
		Session session = null;
		Transaction tx = null;
		boolean result = true;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.update(obj);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
			result = false;
		}finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return result;
	}

}
