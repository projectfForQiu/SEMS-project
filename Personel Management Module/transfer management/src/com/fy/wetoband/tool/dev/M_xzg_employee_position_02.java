package com.fy.wetoband.tool.dev;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.fy.wetoband.tool.dao.TransferDaoImpl;
import com.fy.wetoband.tool.manager.Tool;
import com.fy.wetoband.tool.parameter.Parameter;

public class M_xzg_employee_position_02 extends Tool{
	private String page;
	private String rows;
	
	@Override
	public Object procedure(Parameter request) {
		
		String action = (String) request.getParameter("action");
		
		
		if(action!=null&&!action.equals("")){
			if(action.equals("listTransfer")){
				TransferDaoImpl lf = new TransferDaoImpl();
				int currentPage = 1;  
				int pageSize = 5; 
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
			}else if(action.equals("pass")){
				try{
					TransferDaoImpl lf = new TransferDaoImpl();
					lf.pass(request);
					return "…Û∫À≥…π¶";
				}catch (Exception e) {
					return "error";
				}
				
			}
		}
		return null;
	}

}

