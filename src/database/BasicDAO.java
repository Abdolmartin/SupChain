package database;

import java.util.ArrayList;

public interface BasicDAO<T> {
	
	public ArrayList<T> getAll();
	public T getByID(int id);
	public int save(T obj);
	public void update(T obj);
}

