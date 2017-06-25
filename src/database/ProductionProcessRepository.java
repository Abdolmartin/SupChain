package database;

import java.util.ArrayList;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import productionInfo.ProductionProcess;

public class ProductionProcessRepository implements BasicDAO<ProductionProcess>{

	@Override
	public ArrayList<ProductionProcess> getAll() {
		Session session = null;
        ArrayList<ProductionProcess> result = null;
        try {
            session = HibernateUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductionProcess> query = builder.createQuery(ProductionProcess.class);
            result = (ArrayList<ProductionProcess>)session.createQuery(query).getResultList();
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
	public ProductionProcess getByID(int id) {
		Session session = null;
        ProductionProcess pp = null;
        try {
            session = HibernateUtil.getSession();
            pp = session.get(ProductionProcess.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return pp;
	}

	@Override
	public int save(ProductionProcess obj) {
		Session session = null;
		Transaction tx = null;
		Integer ppID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			ppID = (Integer) session.save(obj);
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
		return ppID;
	}

	@Override
	public boolean update(ProductionProcess obj) {
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
