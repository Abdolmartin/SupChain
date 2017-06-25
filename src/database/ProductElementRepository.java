package database;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import salesManagement.ProductElement;

public class ProductElementRepository implements BasicDAO<ProductElement>{

	@Override
	public ArrayList<ProductElement> getAll() {
		Session session = null;
        ArrayList<ProductElement> result = null;
        try {
            session = HibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductElement> query = builder.createQuery(ProductElement.class);
            result = (ArrayList<ProductElement>)session.createQuery(query).getResultList();
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
	public ProductElement getByID(int id) {
		Session session = null;
        ProductElement pe = null;
        try {
            session = HibernateUtil.getSession();
            pe = session.get(ProductElement.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return pe;
	}

	@Override
	public int save(ProductElement obj) {
		Session session = null;
		Transaction tx = null;
		Integer peID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			peID = (Integer) session.save(obj);
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
		return peID;
	}

	@Override
	public boolean update(ProductElement obj) {
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
