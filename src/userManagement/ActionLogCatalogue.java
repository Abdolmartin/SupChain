package userManagement;

import java.util.ArrayList;

public class ActionLogCatalogue {
	
	
	private ArrayList<ActionLog> actionLogList;
	
	private static ActionLogCatalogue actionLogCatalogue = new ActionLogCatalogue();
	public static ActionLogCatalogue getCatalogue(){
		return ActionLogCatalogue.actionLogCatalogue;
	}
	
	private ActionLogCatalogue(){
		this.actionLogList = new ArrayList<>();
	}
	
	public void addLog(ActionLog actionLog){
		this.actionLogList.add(actionLog);
	}
}
