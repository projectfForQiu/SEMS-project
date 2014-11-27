package com.fy.wetoband.tool.dev;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.fy.wetoband.position.dao.PositionDaoImpl;
import com.fy.wetoband.tool.manager.Tool;
import com.fy.wetoband.tool.parameter.Parameter;

public class A_xzg_employee_post extends Tool{
	private String page;
	private String rows;
	
	@Override
	public Object procedure(Parameter request) {
		
		String action = (String) request.getParameter("action");
	
		
		if(action!=null&&!action.equals("")){
			if(action.equals("listPosition")){
				PositionDaoImpl lf = new PositionDaoImpl();
				int currentPage = 1;  
				int pageSize = 15; 
				String name = (String)request.getParameter("name");
				String value = (String)request.getParameter("value");
				
				this.page = (String) request.getParameter("page"); 
				if(this.page != null){
					currentPage = Integer.parseInt(this.page);
				}
				
			
				this.rows = (String) request.getParameter("rows");
				if(this.rows != null){
					pageSize = Integer.parseInt(this.rows);
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map = lf.getMap(pageSize, currentPage,name,value);
				
				return JSONObject.fromObject(map);
			}else if(action.equals("addPosition")){
				try{
				PositionDaoImpl lf = new PositionDaoImpl();
				lf.insert(request);
					return "添加成功";
				}catch (Exception e) {
					return "添加失败,由于填写格式不正确";
				}
				
			}else if(action.equals("deletePosition")){
				try{
					PositionDaoImpl lf = new PositionDaoImpl();
					lf.delete(request);
					return "删除成功";
				}catch(Exception e){
					return "error";
				}
			}else if(action.equals("getdepNo")){
				try{
					PositionDaoImpl lf = new PositionDaoImpl();
					JSONArray result = lf.getlistId();
					return result;
				}catch(Exception e){
					return "获取归属部门值出错!";
				}
			}else if(action.equals("changePosition")){
				try{
					PositionDaoImpl lf = new PositionDaoImpl();
					lf.change(request);
					return "修改成功";
				}catch(Exception e){
					return "修改失败!";
				}
			}
		}
		return null;
	}

}
