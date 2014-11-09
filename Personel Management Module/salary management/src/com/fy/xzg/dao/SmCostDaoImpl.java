package com.fy.xzg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.fy.wetoband.tool.dbconnector.ConnectionManager;
import com.fy.xzg.entity.SmCost;

public class SmCostDaoImpl implements SmCostDao{

	@Override
	/*public Map<String, Object> showSmCostList(int currentPage, int pageSize,
			String name, String value) {
		Map<String,Object> result = new HashMap<String, Object>();
		StringBuilder SQL_rows = new StringBuilder();
		int count = 0;
		SQL_rows .append("SELECT * FROM hr_salary ");
		SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
		StringBuilder SQL_total = new StringBuilder();
		SQL_total.append("SELECT COUNT(*) total FROM hr_salary");
		//鑾峰緱鏁版嵁搴撹繛鎺�
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement ps_result = conn.prepareStatement(SQL_rows.toString());
			 ResultSet  resultrs = ps_result.executeQuery();
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 while(resultrs.next()){
				 Map<String,Object> record = new HashMap<String, Object>();
				    record.put("sID", resultrs.getInt("sID"));
					record.put("employee_no", resultrs.getString("employee_no"));
					record.put("basic_salary", resultrs.getDouble("basic_salary"));
					record.put("sDate", resultrs.getTimestamp("sDate"));
					record.put("remark", resultrs.getString("remark"));
					record.put("performanceSalary", resultrs.getDouble("performanceSalary"));
					record.put("attendSalary", resultrs.getDouble("attendSalary"));
					record.put("yearSalary", resultrs.getDouble("yearSarary"));
					record.put("otherSarary", resultrs.getDouble("otherSarary"));
				 list.add(record);
			 }
			 result.put("rows", JSONArray.fromObject(list));
			 //閲婃斁璧勬簮
			 resultrs.close();
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
	}*/
	public Map<String, Object> showSmCostList(int currentPage, int pageSize,
			String name, String value) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		Map<String, Object> map = new HashMap<String, Object>();
		PreparedStatement resultpsmt = null;
		PreparedStatement countpsmt = null;
		List<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		String resultsql = "select * from hr_salary order by employee_no desc limit "+(currentPage - 1) * pageSize + ", " + pageSize;
		String countsql = "select count(*) num from hr_salary";
		if(name!=null){//用于实现查询功能
		resultsql ="select hr_salary.* from hr_salary where "+name+"='"+value+"' order by employee_no desc limit "+(currentPage - 1) * pageSize + ", " + pageSize;
			countsql = "select count(*) num from hr_salary where "+name+"='"+value+"'";
		}
		try {
			resultpsmt = conn.prepareStatement(resultsql);
			countpsmt = conn.prepareStatement(countsql);
			ResultSet resultrs = resultpsmt.executeQuery();
			ResultSet countrs = countpsmt.executeQuery();
			countrs.next();
			map.put("total", countrs.getInt("num"));
			while(resultrs.next()){
//				String te = resultrs.getString("sDate");
				Map<String, Object> record = new HashMap<String, Object>();
				record.put("salary_id", resultrs.getString("salary_id"));
				record.put("employee_no", resultrs.getFloat("employee_no"));
				record.put("basic_salary", resultrs.getFloat("basic_salary"));
				record.put("performance_salary", resultrs.getFloat("performance_salary"));
				record.put("attend_salary", resultrs.getFloat("attend_salary"));
				record.put("year_sarary", resultrs.getFloat("year_sarary"));
				record.put("other_sarary", resultrs.getFloat("other_sarary"));
				record.put("remark", resultrs.getString("remark"));
				records.add(record);
			}
			map.put("rows", JSONArray.fromObject(records));
			resultpsmt.close();
			resultrs.close();
			countpsmt.close();
			countrs.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	public String delSmCost(String id) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
				PreparedStatement psmt = null;
				String sql = "delete from hr_salary where salary_id=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, id);
				psmt.executeUpdate();
				psmt.close();
				conn.close();
				return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public List<Map<String, Object>> getSaler() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String sql = "select employee_no,employee_name from hr_employee"; 
		try {
			Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs!=null&&rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", rs.getString("employee_no"));
				map.put("employee_name", rs.getString("employee_name"));
				result.add(map);
			}
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int getSmCostNum(String sel_client) {
		int num = 0;
		String sql ="select count(*) num from sale_cost where sel_client=?";
		try {
			Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, sel_client);
			ResultSet rs = psmt.executeQuery();
			while (rs!=null&&rs.next()){
				num = rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num+1;
	}

	@Override
	public String addSmCost(SmCost sc) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement psmt = null;
			String sql = "insert into hr_salary(salary_id,employee_no,basic_salary,performance_salary,attend_salary,year_sarary,other_sarary,remark) values(?,?,?,?,?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, sc.getSalary_id());
			psmt.setString(2, sc.getEmployee_no());
			psmt.setFloat(3, sc.getBasic_salary());
			psmt.setFloat(4, sc.getPerformance_salary());
			psmt.setFloat(5, sc.getAttend_salary());
			psmt.setFloat(6, sc.getYear_sarary());
			psmt.setFloat(7, sc.getOther_sarary());
			psmt.setString(8, sc.getRemark());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	@Override
	public Map<String, Object> getSmCostById(String id) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		Map<String, Object> record = new HashMap<String, Object>();
		String sql = "select * from hr_salary where employee_no=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet resultrs = pstmt.executeQuery();
			while (resultrs.next()) {
				record.put("salary_id", resultrs.getString("salary_id"));
				record.put("employee_no", resultrs.getString("employee_no"));
				record.put("basic_salary", resultrs.getFloat("basic_salary"));
				record.put("performance_salary", resultrs.getFloat("performance_salary"));
				record.put("attend_salary", resultrs.getFloat("attend_salary"));
				record.put("year_sarary", resultrs.getFloat("year_sarary"));
				record.put("other_sarary", resultrs.getFloat("other_sarary"));
				record.put("remark", resultrs.getString("remark"));
				//record.put("otherSarary", resultrs.getString("otherSarary"));		
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return record;
	}

	@Override
	public String updateSmCost(SmCost sc) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement psmt = null;
			String sql = "update hr_salary set basic_salary=?,performance_salary=?,attend_salary=?,year_sarary=?,other_sarary=?," +
					"remark=? where salary_id=?";
			psmt = conn.prepareStatement(sql);
			
			psmt.setFloat(1, sc.getBasic_salary());
			psmt.setFloat(2, sc.getPerformance_salary());
			psmt.setFloat(3, sc.getAttend_salary());
			psmt.setFloat(4, sc.getYear_sarary());
			psmt.setFloat(5, sc.getOther_sarary());
			psmt.setString(6, sc.getRemark());
			psmt.setString(7, sc.getSalary_id());
			psmt.executeUpdate();
			psmt.close();
			conn.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}


}
