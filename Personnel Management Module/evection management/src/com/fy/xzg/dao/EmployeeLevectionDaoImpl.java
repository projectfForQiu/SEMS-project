package com.fy.xzg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fy.wetoband.tool.dbconnector.ConnectionManager;
import com.fy.xzg.entity.EmployeeLevection;
/**
 * 数据表hr_leavetab的dao类，实现对该表的各种操作
 * @author yuezhengling
 *
 */
public class EmployeeLevectionDaoImpl implements EmployeeLevectionDao {

	@Override
	public Map<String, Object> getList(int page, int size, String query) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		int count = 0;
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		PreparedStatement pre2 = null;
		ResultSet rs = null;
		String sql = "select a.*,b.employee_name as approverName,c.employee_name " +
				"as employeeName from hr_leavetab as a left join hr_employee as b " +
				"on a.approver_id = b.employee_no left join hr_employee as " +
				"c on a.employee_no = c.employee_no "+query+" order by start_date " +
				"desc limit "+(page-1)*size+" , "+size;
		String sql2 = "select count(*) as num from hr_leavetab as a left join hr_employee as b " +
				"on a.approver_id = b.employee_no left join hr_employee as " +
				"c on a.employee_no = c.employee_no "+query;
		try{
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("leavetabId", rs.getString("leavetab_id"));
				map1.put("approverId", rs.getString("approver_id"));
				map1.put("approverName", rs.getString("approverName"));
				map1.put("employeeNo", rs.getString("employee_no"));
				map1.put("employeeName", rs.getString("employeeName"));
				map1.put("startDate", rs.getString("start_date"));
				map1.put("endDate", rs.getString("end_date"));
				map1.put("leaveDay", rs.getString("leave_day"));
				map1.put("leaveReason", rs.getString("leave_reason"));
				map1.put("remark", rs.getString("remark"));
				map1.put("status", rs.getString("status"));
				map1.put("leaveType", rs.getString("leave_type"));
				list.add(map1);
			}
			pre2 = con.prepareStatement(sql2);
			rs = pre2.executeQuery();
			if(rs.next()){
				count = rs.getInt("num");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		map.put("rows", list);
		map.put("total", count);
		return map;
	}

	@Override
	public String delete(String id) {
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		String sql = "delete from hr_leavetab where leavetab_id='"+id+"'";
		String result = "failure";
		try{
			pre = con.prepareStatement(sql);
			int n = pre.executeUpdate();
			if(n==1){
				result = "success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String update(EmployeeLevection em) {
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		String sql = "update hr_leavetab set approver_id=?," +
				"employee_no=?,start_date=?,end_date=?,leave_day=?," +
				"leave_type=?,leave_reason=?,remark=?,status=? where leavetab_id=?";
	    String result = "failure";
	    try{
	    	pre = con.prepareStatement(sql);
	    	pre.setString(1,em.getApproverId());
	    	pre.setString(2,em.getEmployeeNo());
	    	pre.setString(3,em.getStartDate());
	    	pre.setString(4,em.getEndDate());
	    	pre.setString(5,em.getLeaveDay());
	    	pre.setString(6,em.getLeaveType());
	    	pre.setString(7,em.getLeaveReason());
	    	pre.setString(8,em.getRemark());
	    	pre.setString(9,em.getStatus());
	    	pre.setString(10,em.getLeavetabId());
	    	int n = pre.executeUpdate();
	    	if(n==1){
	    		result = "success";
	    	}
	    }catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public String add(EmployeeLevection em) {
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		String sql = "insert into hr_leavetab(leavetab_id,approver_id," +
				"employee_no,start_date,end_date,leave_day," +
				"leave_type,leave_reason,remark,status) values(?,?,?,?,?,?,?,?,?,?)";
	    String result = "failure";
	    try{
	    	pre = con.prepareStatement(sql);
	    	pre.setString(1,em.getLeavetabId());
	    	pre.setString(2,em.getApproverId());
	    	pre.setString(3,em.getEmployeeNo());
	    	pre.setString(4,em.getStartDate());
	    	pre.setString(5,em.getEndDate());
	    	pre.setString(6,em.getLeaveDay());
	    	pre.setString(7,em.getLeaveType());
	    	pre.setString(8,em.getLeaveReason());
	    	pre.setString(9,em.getRemark());
	    	pre.setString(10,em.getStatus());
	    	int n = pre.executeUpdate();
	    	if(n==1)
	    	  result = "success";
	    }catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean hasId(String id) {
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		String sql = "select leavetab_Id from hr_leavetab where leavetab_id='"+id+"'";
		boolean result = false;
		try{
			pre = con.prepareStatement(sql);
			ResultSet rs = pre.executeQuery();
			if(rs.next()){
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> getEmployee(String query) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Connection con = ConnectionManager.getInstance().getConnection("mydb1");
		PreparedStatement pre = null;
		ResultSet rs = null;
		String sql = "select employee_no,employee_name from hr_employee "+query;
		try{
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			while(rs.next()){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("employeeNo", rs.getString("employee_no"));
				map1.put("employeeName", rs.getString("employee_name"));
				list.add(map1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		map.put("employee", list);
		return map;
	}
}
