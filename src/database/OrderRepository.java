package database;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import salesManagement.Order;
import salesManagement.ProductElementItem;

public class OrderRepository implements BasicDAO<Order>{


	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public ArrayList<Order> getAll() {
		Session session = null;
		Transaction tx = null;
        ArrayList<Order> result = null;
        try {
        	session = HibernateUtil.getSession();
        	tx = session.beginTransaction();
            result = (ArrayList<Order>) session.createCriteria(Order.class).list();
            for(Order ord: result){
            	Hibernate.initialize(ord.getOrderingUser());
            	Hibernate.initialize(ord.getOrderedItems());
            	List<ProductElementItem> orderedList = ord.getOrderedItems();
            	for(ProductElementItem peim: orderedList){
            		Hibernate.initialize(peim);
            		Hibernate.initialize(peim.getProductElementType());
            		Hibernate.initialize(peim.getStatusHistory());
            		System.out.println("statusHistory.size: ");
            		System.out.println(peim.getStatusHistory().size());
            	}
            }
            tx.commit();
        } catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
        	e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return result;
	}

	@Override
	public Order getByID(int id) {
		Session session = null;
        Order order = null;
        try {
            session = HibernateUtil.getSession();
            order = session.get(Order.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return order;
	}

	@Override
	public int save(Order obj) {
		Session session = null;
		Transaction tx = null;
		Integer orderID = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			orderID = (Integer) session.save(obj);
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
		return orderID;
	}

	@Override
	public boolean update(Order obj) {
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
