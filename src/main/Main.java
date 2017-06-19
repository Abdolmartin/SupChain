package main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import common.Constants;
import exceptions.InvalidArgumentException;
import ui.InitialPortal;
import userManagement.AuthenticationType;
import ui.InitialPortal;
import userManagement.ContactInformation;
import userManagement.UserProfile;
import userManagement.Manager;
import userManagement.Notification;
import userManagement.UserProfileCatalogue;

public class Main {
	// private static SessionFactory factory;

	public static void main(String[] args) {
		
		try {
			UserProfile user1 = UserProfileCatalogue.getCatalogue().createCustomer("a", "12345678", "a", "b", "1", "2", "3");
			user1.addNotification(new Notification(false, "sup", new Date(), Constants.SYSTEM_ACTOR));
			InitialPortal initialPortal = new InitialPortal();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
		// building factory
		try
		{
			// factory = new Configuration().configure().buildSessionFactory();
	    }catch (Throwable ex) {
	        System.err.println("Failed to create sessionFactory object." + ex);
	        throw new ExceptionInInitializerError(ex);
	        }
		
		// ContactInformation conInf = addContactInformationToDb();
		// UserProfile usr = addManagerToDb(conInf);
		// System.out.println("usr ID = "+ usr.getId());
		// addNotif(usr);
		
	}
	/***
	
	public static ContactInformation addContactInformationToDb(){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer contactID = null;
		ContactInformation ci = null;
		try{
	         tx = session.beginTransaction();
	         ci = new ContactInformation("abbas@gmail.com", "091233", "azadi");
	         System.out.println("1111111");
	         contactID = (Integer) session.save(ci);
	         System.out.println("contactID " + contactID);
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null){
	        	 tx.rollback();
	         }
	         e.printStackTrace();
	      }finally {
	         session.close();
	      }
		return ci;
	}
	
	public static UserProfile addManagerToDb(ContactInformation conInf){
		Session session = factory.openSession();
		Transaction tx = null;
		Integer userId = 5;
		Manager manager = null;
		try{
			tx = session.beginTransaction();
			System.out.println("ghable vaghe'e");
			
			manager = new Manager("abbas_joon", "4422", "abbas", "abbassii", conInf, AuthenticationType.MANAGER);
			// session.flush();
			// session.clear();
			
			userId = (Integer) session.save(manager);
			System.out.println("jjjjj userId = " + userId);
			tx.commit();
		}catch(HibernateException e){
			System.out.println("injaaaaaakkkkkk");
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			
			session.close();
		}
		return manager;
	}
	
	public static void addNotif(UserProfile manager){
		Session session = factory.openSession();
		Transaction tx = null;
		try{
			tx = session.beginTransaction();
			Notification notif = new Notification(false, "salam notif", new Date(), "khodam");
			List<Notification> notifs = new ArrayList<Notification>();
			notifs.add(notif);
			manager.setNotifications(notifs);
			
			// notif.setId(20000);
			
			session.update(manager);
			int notifId = (Integer) session.save(notif);
			// System.out.println("injkkkkk" + notifId);

			System.out.println("*******"+notifId);
			
			tx.commit();
		}catch(HibernateException e){
			System.out.println("injaaaaaakkkkkk");
			if (tx != null){
				tx.rollback();
			}
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	***/
	

}
