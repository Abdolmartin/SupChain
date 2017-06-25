package database;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import supplyManagement.SupplyPath;

public class SupplyPathRepository implements BasicDAO<SupplyPath>{

	@Override
	public ArrayList<SupplyPath> getAll() {
		Session session = null;
        ArrayList<SupplyPath> result = null;
        try {
            session = HibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SupplyPath> query = builder.createQuery(SupplyPath.class);
            result = (ArrayList<SupplyPath>)session.createQuery(query).getResultList();
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
	public SupplyPath getByID(int id) {
		Session session = null;
        SupplyPath sp = null;
        try {
            session = HibernateUtil.getSession();
            sp = session.get(SupplyPath.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return sp;
	}

	@Override
	public int save(SupplyPath obj) {
		Session session = null;
		Transaction tx = null;
		Integer spID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			spID = (Integer) session.save(obj);
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
		return spID;
	}

	@Override
	public boolean update(SupplyPath obj) {
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
