package userManagement;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import database.ActionLogRepository;

public class ActionLogCatalogue {
	
	
	private List<ActionLog> actionLogList;
	private ActionLogRepository repo = new ActionLogRepository();
	private static ActionLogCatalogue actionLogCatalogue = new ActionLogCatalogue();
	public static ActionLogCatalogue getCatalogue(){
		return ActionLogCatalogue.actionLogCatalogue;
	}
	
	private ActionLogCatalogue(){
		this.actionLogList = new ArrayList<>();
	}
	
	public void addLog(ActionLog actionLog){
		this.actionLogList.add(actionLog);
		actionLog.setId(repo.save(actionLog));
	}
	
	public List<ActionLog> search(String actorName){
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
	
	public List<JSONObject> showSearchResults(String actorName){
		List<ActionLog> logs = this.search(actorName);
		List<JSONObject> results = new ArrayList<>();
		for (int i=0;i<logs.size();i++){
			results.add(logs.get(i).showInfo());
		}
		return results;
	}
}
