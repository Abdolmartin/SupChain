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
	
	public ArrayList<ActionLog> search(String actorName){
		if (actorName.equals("")){
			return this.actionLogList;
		}
		else{
			ArrayList<ActionLog> resultList = new ArrayList<>();
			for (int i=0;i<actionLogList.size();i++){
				ActionLog currentLog = actionLogList.get(i);
				if (actorName.equals(currentLog.getActor())){
					resultList.add(currentLog);
				}
			}
			return resultList;
		}
	}
}
