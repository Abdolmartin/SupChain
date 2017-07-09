package ui;

import common.Constants;
import ui.handler.UserFacade;

public class MainPortalRedirectService {

	public void startRelevantMainPortal(int userID){
		UserFacade userManager = new UserFacade();
		String authLevel = userManager.getUserAuthenticationLevel(userID);
		switch (authLevel) {
			case Constants.ADMIN:
				new AdminPortal(userID);
				break;
			case Constants.MANAGER:
				new ManagerPortal(userID);
				break;
			case Constants.CUSTOMER:
				new CustomerPortal(userID);
				break;
			case Constants.EMPLOYEE:
				new EmployeePortal(userID);
				break;
			default:
				new ManagerPortal(userID);
				break;
		}
	}

}
