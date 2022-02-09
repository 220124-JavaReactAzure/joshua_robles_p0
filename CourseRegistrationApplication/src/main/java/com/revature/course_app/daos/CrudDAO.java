package com.revature.course_app.daos;

import com.revature.course_app.util.collections.ArrayList;

public interface CrudDAO<T> {
	
	T create(T newObject);
	
	ArrayList<T> findAll();
	T findById(int id);
	
	boolean update(T updatedObj);
	
	boolean delete(String id);

}
