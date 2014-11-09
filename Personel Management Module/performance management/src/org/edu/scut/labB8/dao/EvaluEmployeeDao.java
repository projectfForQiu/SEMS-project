package org.edu.scut.labB8.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.edu.scut.labB8.entity.EvaluEmployeeModel;

import net.sf.json.JSONArray;

import com.fy.wetoband.tool.dbconnector.ConnectionManager;

public class EvaluEmployeeDao implements IEvaluEmployee {

	@Override
	public Map<String, Object> getMap(int pageSize, int currentPage) {
		Map<String,Object> result = new HashMap<String, Object>();
		StringBuilder SQL_rows = new StringBuilder();
		int count = 0;
		SQL_rows .append("SELECT * FROM hr_evaluemployee ");
		SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
		StringBuilder SQL_total = new StringBuilder();
		SQL_total.append("SELECT COUNT(*) total FROM hr_evaluemployee");
		
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement ps_result = conn.prepareStatement(SQL_rows.toString());
			 ResultSet  rs_rows = ps_result.executeQuery();
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 while(rs_rows.next()){
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("evalue_id", rs_rows.getString("evalue_id"));
				 map.put("evalue_no", rs_rows.getString("employee_no"));
				 map.put("evalue_date", rs_rows.getString("evalue_date"));
				 map.put("totalScore", rs_rows.getString("total_score"));
				 map.put("demo", rs_rows.getString("demo"));
				 map.put("workPerformance", rs_rows.getString("itemOneScore"));
				 map.put("qualityOfWork", rs_rows.getString("itemTwoScore"));
				 map.put("workAttitude", rs_rows.getString("itemThreeScore"));
				 map.put("workSkills", rs_rows.getString("itemFourScore"));
				 map.put("itemFiveScore", rs_rows.getString("itemFiveScore"));
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
	
	public void AddModel(Object[] obj) {
		String sqlText="insert into hr_evaluemployee(employee_no,evalue_date,total_score,demo,itemOneScore,itemTwoScore,itemThreeScore,itemFourScore,itemFiveScore)values"+
				"(?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement psta;
		try {
			psta = conn.prepareStatement(sqlText);
			psta.setObject(1, obj[0]);
			psta.setObject(2, obj[1]);
			psta.setObject(3, obj[2]);
			psta.setObject(4, obj[3]);
			psta.setObject(5, obj[4]);
			psta.setObject(6, obj[5]);
			psta.setObject(7, obj[6]);
			psta.setObject(8, obj[7]);
			psta.setObject(9, obj[8]);
			psta.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String delEmployeePerformance(String id) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
				PreparedStatement psmt = null;
				String sql = "delete from hr_evaluemployee where evalue_id='"+id+"'";
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate();
				psmt.close();
				conn.close();
				return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

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

	public int getEmployeePerformanceNum() {
		int num = 0;
		String sql ="select count(*) num from hr_evaluemployee where";
		try {
			Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs!=null&&rs.next()){
				num = rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num+1;
	}

	public String addEmployeePerformance(EvaluEmployeeModel ep) {
		String sqlText="insert into hr_evaluemployee(evalue_id,employee_no,evalue_date,total_score,demo,itemOneScore,itemTwoScore,itemThreeScore,itemFourScore,itemFiveScore)values"+
					"(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement psta;
		try {
			psta = conn.prepareStatement(sqlText);
			psta.setObject(1, ep.getevalue_id());
			psta.setObject(2, ep.getevalue_no());
			psta.setObject(3, ep.getevalue_date());
			psta.setObject(4, ep.gettotal_score());
			psta.setObject(5, ep.getdemo());
			psta.setObject(6, ep.getitemOneScore());
			psta.setObject(7, ep.getitemTwoScore());
			psta.setObject(8, ep.getitemThreeScore());
			psta.setObject(9, ep.getitemFourScore());
			psta.setObject(10, ep.getitemFiveScore());
			psta.executeUpdate();
			psta.close();
			conn.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}

	public Map<String, Object> getEvaluEmployeeById(String id) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");Map<String,Object> map = new HashMap<String, Object>();
		String sql = "select * from hr_evaluemployee where evalue_id='"+id+"'";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet resultrs = pstmt.executeQuery();
			while (resultrs.next()) {
				 map.put("evalue_id", resultrs.getString("evalue_id"));
				 map.put("evalue_no", resultrs.getString("employee_no"));
				 map.put("evalue_date", resultrs.getString("evalue_date"));
				 map.put("totalScore", resultrs.getString("total_score"));
				 map.put("demo", resultrs.getString("demo"));
				 map.put("workPerformance", resultrs.getString("itemOneScore"));
				 map.put("qualityOfWork", resultrs.getString("itemTwoScore"));
				 map.put("workAttitude", resultrs.getString("itemThreeScore"));
				 map.put("workSkills", resultrs.getString("itemFourScore"));
				 map.put("itemFiveScore", resultrs.getString("itemFiveScore"));
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public String updateEmployeePerformance(EvaluEmployeeModel ep) {
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement psta = null;
			String sql = "update hr_evaluemployee set employee_no=?,evalue_date=?,total_score=?,demo=?,itemOneScore=?,itemTwoScore=?,itemThreeScore=?,itemFourScore=?,itemFiveScore=? where evalue_id=?";
			psta = conn.prepareStatement(sql);
			psta.setObject(1, ep.getevalue_no());
			psta.setObject(2, ep.getevalue_date());
			psta.setObject(3, ep.gettotal_score());
			psta.setObject(4, ep.getdemo());
			psta.setObject(5, ep.getitemOneScore());
			psta.setObject(6, ep.getitemTwoScore());
			psta.setObject(7, ep.getitemThreeScore());
			psta.setObject(8, ep.getitemFourScore());
			psta.setObject(9, ep.getitemFiveScore());
			psta.setObject(10, ep.getevalue_id());
			psta.executeUpdate();
			psta.close();
			conn.close();
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	public Map<String, Object> getQueryMap(int pageSize, int currentPage,String name,String value) {
		Map<String,Object> result = new HashMap<String, Object>();
		StringBuilder SQL_rows = new StringBuilder();
		StringBuilder SQL_total = new StringBuilder();
		int count = 0;
		if(name.equals("id")){
			SQL_rows .append("SELECT * FROM hr_evaluemployee where evalue_id="+value);
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			SQL_total.append("SELECT COUNT(*) total FROM hr_evaluemployee where evalue_id="+value);
		}else{
			SQL_rows .append("SELECT * FROM hr_evaluemployee where employee_no='"+value+"'");
			SQL_rows.append(" LIMIT "+(currentPage-1)*pageSize+" ,"+pageSize+" ;");
			SQL_total.append("SELECT COUNT(*) total FROM hr_evaluemployee where employee_no='"+value+"'");
		}
		
		Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
		try {
			PreparedStatement ps_result = conn.prepareStatement(SQL_rows.toString());
			 ResultSet  rs_rows = ps_result.executeQuery();
			 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			 while(rs_rows.next()){
				 Map<String,Object> map = new HashMap<String, Object>();
				 map.put("evalue_id", rs_rows.getString("evalue_id"));
				 map.put("evalue_no", rs_rows.getString("employee_no"));
				 map.put("evalue_date", rs_rows.getString("evalue_date"));
				 map.put("total_score", rs_rows.getString("total_score"));
				 map.put("demo", rs_rows.getString("demo"));
				 map.put("itemOneScore", rs_rows.getString("itemOneScore"));
				 map.put("itemTwoScore", rs_rows.getString("itemTwoScore"));
				 map.put("itemThreeScore", rs_rows.getString("itemThreeScore"));
				 map.put("itemFourScore", rs_rows.getString("itemFourScore"));
				 map.put("itemFiveScore", rs_rows.getString("itemFiveScore"));
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
	
	public List<Map<String, Object>> getEmployeeNo() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String sql = "select employee_no,employee_name from hr_employee"; 
		try {
			Connection conn = ConnectionManager.getInstance().getConnection("mydb1");
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs!=null&&rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", rs.getString("employee_no"));
				//map.put("employee_name", rs.getString("employee_name"));
				map.put("employee_name", rs.getString("employee_no"));
				result.add(map);
			}
			psmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
