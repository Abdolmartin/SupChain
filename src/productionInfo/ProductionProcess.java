package productionInfo;

import java.util.ArrayList;

import salesManagement.ProductElement;

public class ProductionProcess {
	int id;
	ArrayList<ProductElement> inputs;
	ArrayList<ProductElement> outputs;
	String name;
	ArrayList<String> departments;
	
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

	public ArrayList<ProductElement> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<ProductElement> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<ProductElement> getOutputs() {
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

	public ArrayList<String> getDepartments() {
		return departments;
	}

	public void setDepartments(ArrayList<String> departments) {
		this.departments = departments;
	}
	
}
