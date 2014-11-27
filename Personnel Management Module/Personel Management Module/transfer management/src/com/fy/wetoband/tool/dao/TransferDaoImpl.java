package com.fy.wetoband.tool.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.fy.wetoband.tool.dbconnector.ConnectionManager;
import com.fy.wetoband.tool.parameter.Parameter;

public class TransferDaoImpl implements TransferDao{

	@Override
	public Map<String, Object> getMap(int pageSize, int currentPage,String name,String value) {
		Map<String,Object> result = new HashMap<String, Object>();
		StringBuilder SQL_rows = new StringBuilder();
		StringBuilder SQL_total = new StringBuilder();
		int count = 0;
		if(name!=null){
			SQL_rows.append("select * from hr_transfer,hr_employee,hr_position where hr_transfer.employee_no=hr_employee.employee_no and hr_employee.position_id=hr_position.position_id and hr_transfer.remark!='1' and hr_employee."+name+"=\'"+value+"\'");
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			SQL_total.append("SELECT COUNT(*) total FROM hr_transfer,hr_employee,hr_position where hr_transfer.employee_no=hr_employee.employee_no and hr_employee.position_id=hr_position.position_id and hr_transfer.remark!='1' and hr_employee."+name+"="+value);
		}else{
			SQL_rows .append("SELECT * FROM hr_transfer,hr_employee,hr_position where hr_transfer.employee_no=hr_employee.employee_no and hr_employee.position_id=hr_position.position_id and hr_transfer.remark!='1'");
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			
			SQL_total.append("SELECT COUNT(*) total FROM hr_transfer,hr_employee,hr_position where hr_transfer.employee_no=hr_employee.employee_no and hr_employee.position_id=hr_position.position_id and hr_transfer.remark!='1'");
			
		}
		

		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			
			PreparedStatement ps_result = conn.prepareStatement(SQL_rows.toString());
			 ResultSet  rs_rows = ps_result.executeQuery();
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 
			 while(rs_rows.next()){
				 String sql = "select * from hr_position where position_id =\'"+rs_rows.getString("now_position")+"\'";
				 ResultSet  rs = conn.createStatement().executeQuery(sql);
				 String tranpositionname = "";
				 String nowid = "";
				 while(rs.next()){
					 tranpositionname=rs.getString("position_name");
					 nowid = rs_rows.getString("now_position");
				 }
				 Map<String,Object> map = new HashMap<String, Object>();
				 	map.put("transfer_id", rs_rows.getString("transfer_id"));
					map.put("employee_no", rs_rows.getString("employee_no"));
					map.put("employee_name", rs_rows.getString("employee_name"));
					map.put("orgposition", rs_rows.getString("position_name"));
					map.put("nowposition", tranpositionname);
					map.put("nowpositionid", nowid);
					map.put("positionID", rs_rows.getString("hr_employee.position_id"));
					map.put("transfer_reason", rs_rows.getString("transfer_reason"));
					map.put("remark", rs_rows.getString("remark"));
					list.add(map);
			 }
			 result.put("rows", JSONArray.fromObject(list));
			 
			 rs_rows.close();
			 ps_result.close();
			 PreparedStatement ps_total = conn.prepareStatement(SQL_total.toString());
			 ResultSet  rs_total =  ps_total.executeQuery();
			 if(rs_total.next()){
				count = rs_total.getInt("total") ;
				result.put("total", count);
			 }
			 rs_total.close();
			 ps_total.close();
			 conn.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void pass(Parameter request) throws Exception{
		
		String transfer_id = (String) request.getParameter("transfer_id");
		String employee_id = (String) request.getParameter("employee_id");
		String position_id = (String) request.getParameter("position_id");
		String remark = "1";
		StringBuilder SQL_rows = new StringBuilder();
		SQL_rows.append("update hr_transfer set");
		String value = " remark=\'"+remark+"\' where transfer_id=\'"+transfer_id+"\'";
		SQL_rows.append(value);
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			String sql = "update hr_employee set position_id=\'"+position_id+"\' where employee_no=\'"+employee_id+"\'";
			conn.createStatement().execute(sql);
			conn.createStatement().execute(SQL_rows.toString());
			conn.close();
		} catch (Exception e) {
			throw e;
		}
	}

	

}
