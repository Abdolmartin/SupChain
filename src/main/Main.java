package main;


import common.Constants;
import exceptions.InvalidArgumentException;
import salesManagement.OrderCatalogue;
import salesManagement.ProductElementCatalogue;
import ui.InitialPortal;
import userManagement.AuthenticationType;
import userManagement.UserProfile;
import userManagement.UserProfileCatalogue;

public class Main {
	// private static SessionFactory factory;

	public static void main(String[] args) {
		
		try {
			UserProfileCatalogue.getCatalogue().createCustomer("a", "12345678", "a", "b", "1", "2", "3");
			UserProfileCatalogue.getCatalogue().createIntraOrganisationUser("m", "12345678", "m", "n", "1", "2", "3", AuthenticationType.MANAGER);
			UserProfile employee1 = UserProfileCatalogue.getCatalogue().createIntraOrganisationUser("ml", "12345679998", "m", "n", "1", "2", "3", AuthenticationType.EMPLOYEE);
			// ArrayList<UserProfile> list = new UserProfileRepository().getAll();
			// System.out.println("###########"+list.size());
			//user1.addNotification(new Notification(false, "sup", new Date(), Constants.SYSTEM_ACTOR));
			ProductElementCatalogue.getCatalogue().createProductElement(Constants.PRODUCT, "p1", -1, -1, "a product", true);
			OrderCatalogue.getCatalogue().createCustomerOrder(null, employee1, null, 0, null, "my very initial code");
			
			new InitialPortal();
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
		
	}

}
