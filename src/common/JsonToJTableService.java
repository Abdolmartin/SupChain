package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.json.simple.JSONObject;

public class JsonToJTableService {

	public JsonToJTableService() {
		
	}
	
	public DefaultTableModel createJTableFromJSON(ArrayList<JSONObject> jsonList){
		assert jsonList.size() > 0;
		
		JSONObject firstJSON = jsonList.get(0);
		Set<String> columnSet = (Set<String>)firstJSON.keySet();
		String[] cols = Arrays.copyOf(columnSet.toArray(), columnSet.size(), String[].class);
		String[][] data = new String[jsonList.size()][cols.length];
		for (int i=0;i<jsonList.size();i++){
			for (int j=0;j<cols.length;j++){
				data[i][j] = (String)jsonList.get(i).get(cols[j]);
			}
		}
		return new DefaultTableModel(data, cols);
	}
}
