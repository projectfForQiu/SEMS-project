package com.fy.wetoband.tool.dao;

import java.util.Map;

import net.sf.json.JSONArray;

import com.fy.wetoband.tool.parameter.Parameter;

public interface TransferDao {
	
	Map<String, Object> getMap(int pageSize, int currentPage,String name,String value);
	void pass(Parameter request) throws Exception;
}

