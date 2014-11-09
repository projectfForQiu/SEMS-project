package com.fy.wetoband.tool.dev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;
import org.edu.scut.labB8.entity.EvaluEmployeeModel;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.edu.scut.labB8.dao.IEvaluEmployee;
import org.edu.scut.labB8.dao.EvaluEmployeeDao;


import java.util.List;

import com.fy.wetoband.tool.manager.Tool;
import com.fy.wetoband.tool.parameter.Parameter;
import com.fy.xinzegu.IdGenerate;

public class EvaluEmployee extends Tool{
	private String page;
	private String rows;
	@Override
	public Object procedure(Parameter para) {
		//String name = (String) para.getParameter("name");
		String action = (String) para.getParameter("action");
		//ICountryDao countryDao = new CountryDaoImpl();
		IEvaluEmployee epDao = new EvaluEmployeeDao();
		
		if(action!=null&&!action.equals("")){
			if(action.equals("listEmployeePerformance")){
				String name=(String)para.getParameter("name");
				int currentPage = 1;  //
				int pageSize = 5;  //
				this.page = (String) para.getParameter("page"); 
				if(this.page != null){
					currentPage = Integer.parseInt(this.page);
				}

				this.rows = (String) para.getParameter("rows");
				if(this.rows != null){
					pageSize = Integer.parseInt(this.rows);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				if(name==null)
				{
					map =	epDao.getMap(pageSize, currentPage);
					System.out.println(JSONObject.fromObject(map));
					return JSONObject.fromObject(map);
				}
				else
				{
					String value=(String)para.getParameter("value");
					map =	epDao.getQueryMap(pageSize, currentPage,name,value);
					System.out.println(JSONObject.fromObject(map));
					return JSONObject.fromObject(map);
				}
			}
			
			else if(action.equals("EmployeePerformanceAdd")){
				String num="";
				try {
					num = new IdGenerate().GenerateId("eval");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//int id = Integer.parseInt(para.getParameter("evalue_id").toString());
				double itemOneScore = getDoubleParameter(para,"workPerformance",0);
				double itemTwoScore = getDoubleParameter(para,"qualityOfWork",0);
				double itemThreeScore = getDoubleParameter(para,"workAttitude",0);
				double itemFourScore = getDoubleParameter(para,"workSkills",0);
				double itemFiveScore = getDoubleParameter(para,"itemFiveScore",0);
				double total_score=itemOneScore+itemTwoScore+itemThreeScore+itemFourScore+itemFiveScore;
				String evalue_no = (String)para.getParameter("evalue_no");
				String demo = (String)para.getParameter("demo");
				Timestamp evalue_date = Timestamp.valueOf((String)para.getParameter("evalue_date")+" 00:00:00");
				EvaluEmployeeModel ep = new EvaluEmployeeModel(num, evalue_no,  evalue_date,total_score, demo, itemOneScore, itemTwoScore, itemThreeScore, itemFourScore, itemFiveScore);
				return new EvaluEmployeeDao().addEmployeePerformance(ep);
			}
			else if(action.equals("updateEmployeePerformance")){
				String evalue_id = (String)para.getParameter("id");
				double itemOneScore = getDoubleParameter(para,"workPerformance",0);
				double itemTwoScore = getDoubleParameter(para,"qualityOfWork",0);
				double itemThreeScore = getDoubleParameter(para,"workAttitude",0);
				double itemFourScore = getDoubleParameter(para,"workSkills",0);
				double itemFiveScore = getDoubleParameter(para,"itemFiveScore",0);
				double total_score=itemOneScore+itemTwoScore+itemThreeScore+itemFourScore+itemFiveScore;
				String evalue_no = (String)para.getParameter("evalue_no");
				String demo = (String)para.getParameter("demo");
				Timestamp evalue_date = Timestamp.valueOf((String)para.getParameter("evalue_date")+" 00:00:00");
				EvaluEmployeeModel ep = new EvaluEmployeeModel(evalue_id, evalue_no,  evalue_date,total_score, demo, itemOneScore, itemTwoScore, itemThreeScore, itemFourScore, itemFiveScore);
				return new EvaluEmployeeDao().updateEmployeePerformance(ep);
			}
			else if(action.equals("getEmployeePerformanceById")){
				String id = (String) para.getParameter("id");
				return JSONObject.fromObject(new EvaluEmployeeDao().getEvaluEmployeeById(id));
			}
			else if(action.equals("EmployeePerformanceDel")){
				String id = (String) para.getParameter("id");
				return new EvaluEmployeeDao().delEmployeePerformance(id);
			}
			else if(action.equals("getEmployeeNo")){
				List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
				records = new EvaluEmployeeDao().getEmployeeNo();
				return JSONArray.fromObject(records);
			}
		}
		return null;
	}
	
	private double getDoubleParameter(Parameter para,
			String name, double defaultNum) {
		String temp = (String) para.getParameter(name);
		if (temp != null && !temp.equals("")) {
			double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	
}
