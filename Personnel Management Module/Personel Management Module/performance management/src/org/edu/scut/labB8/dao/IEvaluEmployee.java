package org.edu.scut.labB8.dao;

import java.util.List;
import java.util.Map;

import org.edu.scut.labB8.entity.EvaluEmployeeModel;

public interface IEvaluEmployee {
	Map<String, Object> getMap(int pageSize, int currentPage);
	
	String delEmployeePerformance(String id);
	
	//��ѯ������Ա
	List<Map<String, Object>> getSaler();
	//��ѯ����Ա����֧������Ѵ��ڵļ�¼��
	int getEmployeePerformanceNum();
	//�������Ա����֧�����
	String addEmployeePerformance(EvaluEmployeeModel ep);
	////����id��ȡ����Ա����֧�����
	Map<String, Object> getEvaluEmployeeById(String id);
	//��������Ա����֧�����
	String updateEmployeePerformance(EvaluEmployeeModel ep);
	
	Map<String, Object> getQueryMap(int pageSize, int currentPage,String name,String value);
}
