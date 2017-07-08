package main;

import java.util.ArrayList;

import common.Constants;
import database.UserProfileRepository;
import salesManagement.AvailableItemStatus;
import salesManagement.OrderCatalogue;
import salesManagement.ProductElement;
import salesManagement.ProductElementCatalogue;
import ui.InitialPortal;
import userManagement.AuthenticationType;
import userManagement.UserProfile;
import userManagement.UserProfileCatalogue;

public class Main {
	// private static SessionFactory factory;

	public static void main(String[] args) {
		
		try {
			//Catalogue init
			UserProfileCatalogue.getCatalogue().initialise();
			ProductElementCatalogue.getCatalogue().initialise();
			OrderCatalogue.getCatalogue().intialise();
			
			//Comment this part on 2nd run and beyond.
			UserProfileCatalogue.getCatalogue().createCustomer("a", "12345678", "a", "b", "1", "2", "3");
			UserProfileCatalogue.getCatalogue().createIntraOrganisationUser("m", "12345678", "m", "n", "1", "2", "3", AuthenticationType.MANAGER);
			UserProfile employee1 = UserProfileCatalogue.getCatalogue().createIntraOrganisationUser("ml", "12345679998", "m", "n", "1", "2", "3", AuthenticationType.EMPLOYEE);
			ArrayList<UserProfile> list = new UserProfileRepository().getAll();
			System.out.println("###########"+list.size());
			//user1.addNotification(new Notification(false, "sup", new Date(), Constants.SYSTEM_ACTOR));
			ProductElement pe = ProductElementCatalogue.getCatalogue().createProductElement(Constants.PRODUCT, "p1", -1, -1, "a b c product", true);
			System.out.println("&&&&&&&&&&&&& get id is = " + pe.getId());
			ProductElementCatalogue.getCatalogue().createItems(pe.getId(), 4, new AvailableItemStatus(10));
			OrderCatalogue.getCatalogue().createCustomerOrder(null, employee1, null, 0, null, "my very initial code");
			
			new InitialPortal();
			
		} catch (Exception e) {
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
		
	}

}
