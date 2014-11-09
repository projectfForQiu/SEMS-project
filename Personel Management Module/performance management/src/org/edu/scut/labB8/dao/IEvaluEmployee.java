package org.edu.scut.labB8.dao;

import java.util.List;
import java.util.Map;

import org.edu.scut.labB8.entity.EvaluEmployeeModel;

public interface IEvaluEmployee {
	Map<String, Object> getMap(int pageSize, int currentPage);
	
	String delEmployeePerformance(String id);
	
	//查询销售人员
	List<Map<String, Object>> getSaler();
	//查询销售员费用支出情况已存在的记录数
	int getEmployeePerformanceNum();
	//添加销售员费用支出情况
	String addEmployeePerformance(EvaluEmployeeModel ep);
	////根据id获取销售员费用支出情况
	Map<String, Object> getEvaluEmployeeById(String id);
	//更新销售员费用支出情况
	String updateEmployeePerformance(EvaluEmployeeModel ep);
	
	Map<String, Object> getQueryMap(int pageSize, int currentPage,String name,String value);
}
