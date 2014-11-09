package com.fy.xzg.dao;

import java.util.List;
import java.util.Map;

import com.fy.xzg.entity.SmCost;

public interface SmCostDao {
	//查询所有销售员费用支出情况
	Map<String, Object> showSmCostList(int currentPage, int pageSize,String name,String value);
	//根据编号删除销售员费用支出情况
	String delSmCost(String id);
	//查询销售人员
	List<Map<String, Object>> getSaler();
	//查询销售员费用支出情况已存在的记录数
	int getSmCostNum(String sel_client);
	//添加销售员费用支出情况
	String addSmCost(SmCost sc);
	////根据id获取销售员费用支出情况
	Map<String, Object> getSmCostById(String id);
	//更新销售员费用支出情况
	String updateSmCost(SmCost sc);
}
