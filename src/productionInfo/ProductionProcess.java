package productionInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import common.Constants;
import common.Summarizable;
import common.Viewable;
import salesManagement.ProductElement;
import salesManagement.ProductElementPrinterService;

public class ProductionProcess implements Summarizable, Viewable{
	private int id;
	List<ProductElement> inputs;
	List<ProductElement> outputs;
	String name;
	List<String> departments;
	
	public ProductionProcess(){}
	
	public ProductionProcess(ArrayList<ProductElement> inputs, ArrayList<ProductElement> outputs, String name,
			ArrayList<String> departments) {
		super();
		this.inputs = inputs;
		this.outputs = outputs;
		this.name = name;
		this.departments = departments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<ProductElement> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<ProductElement> inputs) {
		this.inputs = inputs;
	}

	public List<ProductElement> getOutputs() {
		return outputs;
	}

	public void setOutputs(ArrayList<ProductElement> outputs) {
		this.outputs = outputs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList<String> departments) {
		this.departments = departments;
	}

	@Override
	public JSONObject showInfo() {
		HashMap<String, String> map = new HashMap<>();
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("name", this.name);
		List<String> inputsStringList = new ProductElementPrinterService().getNamesList(inputs);
		List<String> outputsStringList = new ProductElementPrinterService().getNamesList(outputs);
		String inputsConcat = String.join(", ", inputsStringList);
		String outputsConcat = String.join(", ", outputsStringList);
		String deptsConcat = String.join(", ", departments);
		map.put("inputs", inputsConcat);
		map.put("outputs", outputsConcat);
		map.put("departments", deptsConcat);
		return new JSONObject(map);
	}

	@Override
	public JSONObject showSummary() {
		HashMap<String, String> map = new HashMap<>();
		map.put(Constants.ID, String.valueOf(this.getId()));
		map.put("name", this.name);
		return new JSONObject(map);
	}
	
}
