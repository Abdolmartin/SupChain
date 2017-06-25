package database;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import userManagement.ActionLog;

public class ActionLogRepository implements BasicDAO<ActionLog>{

	@Override
	public ArrayList<ActionLog> getAll() {
		// ArrayList<ActionLog> result = (ArrayList<ActionLog>) session.createCriteria(ActionLog.class);
		Session session = null;
        ArrayList<ActionLog> result = null;
        try {
            session = HibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ActionLog> query = builder.createQuery(ActionLog.class);
            result = (ArrayList<ActionLog>)session.createQuery(query).getResultList();
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
	public ActionLog getByID(int id) {
		Session session = null;
        ActionLog actionLog = null;
        try {
            session = HibernateUtil.getSession();
            actionLog = session.get(ActionLog.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return actionLog;
	
	}

	@Override
	public int save(ActionLog obj) {
		Session session = null;
		Transaction tx = null;
		Integer actionLogID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			actionLogID = (Integer) session.save(obj);
			tx.commit();
		}catch(HibernateException e){
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
		}
		return actionLogID;
	}

	@Override
	public boolean update(ActionLog obj) {
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
