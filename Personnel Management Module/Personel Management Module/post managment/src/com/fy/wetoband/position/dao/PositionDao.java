package com.fy.wetoband.position.dao;

import java.util.Map;
import net.sf.json.JSONArray;

import com.fy.wetoband.tool.parameter.Parameter;

public interface PositionDao {
	
	Map<String, Object> getMap(int pageSize, int currentPage,String name,String value);
	void insert(Parameter request) throws Exception;
	void delete(Parameter request) throws Exception;
	JSONArray getlistId() throws Exception;
	void change(Parameter request) throws Exception;
}

