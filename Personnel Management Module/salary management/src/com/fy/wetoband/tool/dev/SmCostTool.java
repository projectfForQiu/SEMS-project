package com.fy.wetoband.tool.dev;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fy.wetoband.tool.manager.Tool;
import com.fy.wetoband.tool.parameter.Parameter;
import com.fy.xzg.dao.SmCostDao;
import com.fy.xzg.dao.SmCostDaoImpl;
import com.fy.xzg.entity.SmCost;

public class SmCostTool extends Tool{

	@Override
	public Object procedure(Parameter para) {
		String action = (String)para.getParameter("action");
		SmCostDao scDao = new SmCostDaoImpl();
		
		if(action.equals("SmCostList")){//查询所有销售员费用支出情况
			Map<String, Object> result = new HashMap<String, Object>();
			String start = (String) para.getParameter("page");
			String limit = (String) para.getParameter("rows");
			String name = (String)para.getParameter("name");
			String value = (String)para.getParameter("value");
			System.out.println("servlet:"+name+""+value);
			if((name==null||name.equals(null))||(value==null||value.equals(null))){
				if((start==null||start.equals(null))||(limit==null||limit.equals(null))){
					result = scDao.showSmCostList(1, 5, null, null);
				}else{
					result = scDao.showSmCostList(Integer.parseInt(start), Integer.parseInt(limit), null, null);					
				}
			}else{
				result = scDao.showSmCostList(Integer.parseInt(start), Integer.parseInt(limit), name, value);
			}
			return JSONObject.fromObject(result);
		}else if(action.equals("SmCostDel")){//删除销售员费用支出情况
			String id = (String) para.getParameter("id");
			return scDao.delSmCost(id);
		}else if(action.equals("getSaler")){//查询销售员
			List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
			records = scDao.getSaler();
			return JSONArray.fromObject(records);
		}else if(action.equals("SmCostAdd")){//添加销售员费用支出情况
			String  salary_id= createCode();
			String employee_no = (String)para.getParameter("employee_no");
			float basic_salary = getFloatParameter(para,"basic_salary",0);
			float performance_salary = getFloatParameter(para,"performance_salary",0);
			float attend_salary = getFloatParameter(para,"attend_salary",0);
			float year_sarary = getFloatParameter(para,"year_sarary",0);
			float other_sarary = getFloatParameter(para,"other_sarary",0); 
			String remark = (String)para.getParameter("remark");
			//int count = scDao.getSmCostNum(sel_client);
			//Timestamp otherSarary = Timestamp.valueOf((String)para.getParameter("sel_date")+" 00:00:00");
			//double otherSarary = getDoubleParameter(para,"otherSarary",0);
			//Timestamp re_date = Timestamp.valueOf((String)para.getParameter("re_date")+" 00:00:00");
			SmCost sc = new SmCost(salary_id,employee_no, basic_salary, performance_salary, attend_salary, year_sarary, other_sarary, remark);
			return scDao.addSmCost(sc);
		}else if(action.equals("getSmCostById")){//根据id获取销售员费用支出情况
			String id = (String) para.getParameter("employee_no");
			return JSONObject.fromObject(scDao.getSmCostById(id));
		}else if(action.equals("updateSmCost")){
			String salary_id = (String) para.getParameter("id");
			String employee_no = (String)para.getParameter("employee_no");
			float basic_salary = getFloatParameter(para,"basic_salary",0);
			float performance_salary = getFloatParameter(para,"performance_salary",0);
			float attend_salary = getFloatParameter(para,"attend_salary",0);
			float year_sarary = getFloatParameter(para,"year_sarary",0);
			float other_sarary = getFloatParameter(para,"other_sarary",0);
			String remark = (String)para.getParameter("remark");
			//int count = scDao.getSmCostNum(sel_client);
			//Timestamp otherSarary = Timestamp.valueOf((String)para.getParameter("sel_date")+" 00:00:00");
			//double otherSarary = getDoubleParameter(para,"otherSarary",0);
			SmCost sc = new SmCost(salary_id,employee_no,basic_salary,performance_salary,attend_salary,year_sarary,other_sarary,remark);
			return scDao.updateSmCost(sc);
		}
		return null;
	}
	
//	private float getFloatParameter(Parameter para, String string, int i) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	//用于产生编号
		private String createCode(){
				Random ran = new Random();
				Set<String> set = new HashSet<String>();
				set.add(String.valueOf(ran.nextInt(1000000000)));
				return set.toString().replace("[", "").replace("]", "");
			}
	//自定义获取double类型数据的方法	
		public float getFloatParameter(Parameter para,
				String name, float defaultNum) {
			String temp = (String) para.getParameter(name);
			if (temp != null && !temp.equals("")) {
				float num = defaultNum;
				try {
					num = Float.parseFloat(temp);
				} catch (Exception ignored) {
				}
				return num;
			} else {
				return defaultNum;
			}
		}

}
