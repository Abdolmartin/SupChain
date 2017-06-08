package main;

import exceptions.InvalidArgumentException;
import ui.InitialPortal;
import userManagement.UserProfileCatalogue;

public class Main {

	public static void main(String[] args) {
		// Session session = factory.s
		try {
			UserProfileCatalogue.getCatalogue().createCustomer("a", "12345678", "a", "b", "1", "2", "3");
			InitialPortal initialPortal = new InitialPortal();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
	}

}
