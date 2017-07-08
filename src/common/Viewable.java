package common;

import org.json.simple.JSONObject;

import exceptions.InvalidArgumentException;

public interface Viewable {
	public JSONObject showInfo() throws InvalidArgumentException;
}
