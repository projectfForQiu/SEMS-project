package com.fy.wetoband.position.dao;

import java.sql.Connection;
import java.sql.Date;
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
import com.fy.xinzegu.IdGenerate;

public class PositionDaoImpl implements PositionDao{

	@Override
	public Map<String, Object> getMap(int pageSize, int currentPage,String name,String value) {
		Map<String,Object> result = new HashMap<String, Object>();
		StringBuilder SQL_rows = new StringBuilder();
		StringBuilder SQL_total = new StringBuilder();
		int count = 0;
		if(name!=null){
			SQL_rows .append("SELECT * FROM hr_position where "+name+"=\'"+value+"\'");
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			SQL_total.append("SELECT COUNT(*) total FROM hr_position where "+name+"="+value);
		}else{
			SQL_rows .append("SELECT * FROM hr_position");
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			
			SQL_total.append("SELECT COUNT(*) total FROM hr_position");
			
		}
		
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			
			PreparedStatement ps_result = conn.prepareStatement(SQL_rows.toString());
			 ResultSet  rs_rows = ps_result.executeQuery();
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 while(rs_rows.next()){
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("positionID", rs_rows.getString("position_id"));
				 map.put("positionName", rs_rows.getString("position_name"));
				 map.put("basicSarary", rs_rows.getString("basic_sarary"));
				 map.put("positionDesc", rs_rows.getString("position_sesc"));
				 map.put("deptNo", rs_rows.getString("department_id"));		 
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
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insert(Parameter request) throws Exception{
		ArrayList<Object> values = new ArrayList<Object>();
		
		String position_id = "";
		String positionName = (String) request.getParameter("positionName");
		String basicSarary = (String) request.getParameter("basicSarary");
		String positionDesc = (String) request.getParameter("positionDesc");
		String deptNo = (String) request.getParameter("deptNo");
		IdGenerate create = new IdGenerate();
		try{
			position_id=create.GenerateId("¸ÚÎ»");
		}catch(Exception e){
			System.out.println("IDÉú³ÉÊ§°Ü");
		}
		values.add(position_id);
		values.add(positionName);
		values.add(basicSarary);
		values.add(positionDesc);
		values.add(deptNo);
		StringBuilder SQL_rows = new StringBuilder();
		SQL_rows.append("insert into hr_position(position_id,position_name,basic_sarary,position_sesc,department_id)");
		String value = " values(";
		for(int count = 0;count<values.size();count++){
			
			value = value + "\'"+values.get(count).toString()+"\',";
			
		}
		value = value.substring(0,value.length()-1);
		value = value + ")";
		SQL_rows.append(value);
		
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			conn.createStatement().execute(SQL_rows.toString());
		} catch (SQLException e) {
			throw e;
		}
		try {
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
		
	}

	@Override
	public void delete(Parameter request) throws Exception{
		String id = (String) request.getParameter("positionID");
		String [] id_part = id.split("#");
		String id_sql = "";
		for(int count = 0;count<id_part.length;count++){
			id_sql = id_sql + "\'"+id_part[count].toString() + "\',";
		}
		id_sql = id_sql.substring(0,id_sql.length()-1);
		
		
			
		
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			String SQL_del="delete from hr_leavetab where employee_no in(select DISTINCT employee_no from hr_employee where position_id in("+
					id_sql+"))";
			conn.createStatement().execute(SQL_del);
			SQL_del="delete from bd_department where manager in("+id_sql+")";
			conn.createStatement().execute(SQL_del);
			SQL_del="delete from hr_employee where position_id in("+id_sql+")";
			conn.createStatement().execute(SQL_del);
			SQL_del = "delete from hr_transfer where org_position in ("+id_sql+")";
			conn.createStatement().execute(SQL_del);
			SQL_del="delete from hr_position where position_id in("+id_sql+")";
			conn.createStatement().execute(SQL_del);
			conn.close();
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public JSONArray getlistId() throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		String SQL_searchId = "select department_id,department_name from bd_department";
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			ResultSet rs = conn.createStatement().executeQuery(SQL_searchId);
			
			while(rs.next()){
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", rs.getString("department_id"));
				map.put("text", rs.getString("department_name"));
				list.add(map);
			}
			result.put("", JSONArray.fromObject(list));
			conn.close();
		} catch (SQLException e) {
			throw e;
		}
		return JSONArray.fromObject(list);
	}


	@Override
	public void change(Parameter request)throws Exception{
		String positionID = (String) request.getParameter("positionID");
		String positionName = (String) request.getParameter("positionName");
		String basicSarary = (String) request.getParameter("basicSarary");
		String positionDesc = (String) request.getParameter("positionDesc");
		String deptNo = (String) request.getParameter("deptNo");
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		String sql = "update hr_position set position_name=\'"+positionName+"\', "+
		"basic_sarary=\'"+basicSarary+"\',position_sesc=\'"+positionDesc+"\',"+
		"department_id=\'"+deptNo+"\' where position_id=\'"+positionID+"\'";
				;
		try {
			conn.createStatement().execute(sql);
			conn.close();
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
}
