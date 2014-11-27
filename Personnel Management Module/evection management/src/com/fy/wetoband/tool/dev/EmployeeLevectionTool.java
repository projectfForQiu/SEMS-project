package com.fy.wetoband.tool.dev;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import net.sf.json.JSONObject;
import com.fy.wetoband.tool.manager.Tool;
import com.fy.wetoband.tool.parameter.Parameter;
import com.fy.xzg.dao.EmployeeLevectionDao;
import com.fy.xzg.dao.EmployeeLevectionDaoImpl;
import com.fy.xzg.entity.EmployeeLevection;

public class EmployeeLevectionTool extends Tool{

	@Override
	public Object procedure(Parameter para) {
		String action = (String)para.getParameter("action");
		EmployeeLevectionDao dao = new EmployeeLevectionDaoImpl();
		if(action.equals("list")){//查询出差记录
			Map<String, Object> result = new HashMap<String, Object>();
			String start = (String) para.getParameter("page");
			String limit = (String) para.getParameter("rows");
			String name = (String)para.getParameter("name");
			String value = (String)para.getParameter("value");
			String query = "";
			int start2 = 1;
			int limit2 = 15;
			if(start!=null && !start.equals("")){
				try{
					start2 = Integer.parseInt(start);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(limit!=null&& !limit.equals("")){
				try{
					limit2 = Integer.parseInt(limit);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(name!=null && !name.equals("") && value!=null){
				if(name.equals("leavetabId")){
					query = " where a.leavetab_id like '%"+value+"%'";
				}else if(name.equals("approverName")){
					query = " where b.employee_name like '%"+value+"%'";
				}else if(name.equals("employeeName")){
					query = " where c.employee_name like '%"+value+"%'";
				}
			}
			result = dao.getList(start2, limit2, query);
			return JSONObject.fromObject(result);
		}else if(action.equals("delete")){//根据id删除出差记录
			String id = (String)para.getParameter("id");
			if(id!=null && !id.equals("")){
				return dao.delete(id);
			}else{
				return "failure";
			}
			
		}else if(action.equals("add")){//添加出差记录
			 String approverId = (String)para.getParameter("approverId");
			 String employeeNo = (String)para.getParameter("employeeNo");
			 String startDate = (String)para.getParameter("startDate");
			 String endDate = (String)para.getParameter("endDate");
			 String leaveDay = (String)para.getParameter("leaveDay");
			 String leaveType = (String)para.getParameter("leaveType");
			 String leaveReason = (String)para.getParameter("leaveReason");
			 String remark = (String)para.getParameter("remark");
			 String status = (String)para.getParameter("status");
			 String leavetabId = null;
			 String result = "failure";
			 Random ran = new Random();
			 for(int i =0;i<20;i++){
					String id = ""+ran.nextInt(1000000000);
					if(!dao.hasId(id)){
						leavetabId = id;
						break;
					}
			}
			if(approverId!=null && approverId.equals("")){
				approverId = null;
			}
			if(employeeNo!=null && employeeNo.equals("")){
				employeeNo = null;
			}
			if(startDate!=null && startDate.equals("")){
				startDate = null;
			}
			if(endDate!=null && endDate.equals("")){
				endDate = null;
			}
			if(leaveDay!=null && leaveDay.equals("")){
				leaveDay = null;
			}
			if(leaveType!=null && leaveType.equals("")){
				leaveType = null;
			}
			if(leaveReason!=null && leaveReason.equals("")){
				leaveReason = null;
			}
			if(remark!=null && remark.equals("")){
				remark = null;
			}
			if(status!=null && status.equals("")){
				status = null;
			}
			EmployeeLevection em = new EmployeeLevection(leavetabId,approverId,employeeNo,startDate,
					endDate,leaveDay,leaveType,leaveReason,remark,status);
			if(leavetabId!=null){
				result = dao.add(em);
			}
			return result;
		}else if(action.equals("update")){//根据id更新出差记录
			 String leavetabId = (String)para.getParameter("leavetabId");
			 String approverId = (String)para.getParameter("approverId");
			 String employeeNo = (String)para.getParameter("employeeNo");
			 String startDate = (String)para.getParameter("startDate");
			 String endDate = (String)para.getParameter("endDate");
			 String leaveDay = (String)para.getParameter("leaveDay");
			 String leaveType = (String)para.getParameter("leaveType");
			 String leaveReason = (String)para.getParameter("leaveReason");
			 String remark = (String)para.getParameter("remark");
			 String status = (String)para.getParameter("status");
			 String result = "failure";
			 if(approverId!=null && approverId.equals("")){
					approverId = null;
				}
				if(employeeNo!=null && employeeNo.equals("")){
					employeeNo = null;
				}
				if(startDate!=null && startDate.equals("")){
					startDate = null;
				}
				if(endDate!=null && endDate.equals("")){
					endDate = null;
				}
				if(leaveDay!=null && leaveDay.equals("")){
					leaveDay = null;
				}
				if(leaveType!=null && leaveType.equals("")){
					leaveType = null;
				}
				if(leaveReason!=null && leaveReason.equals("")){
					leaveReason = null;
				}
				if(remark!=null && remark.equals("")){
					remark = null;
				}
				if(status!=null && status.equals("")){
					status = null;
				}
			 EmployeeLevection em = new EmployeeLevection(leavetabId,approverId,employeeNo,startDate,
					endDate,leaveDay,leaveType,leaveReason,remark,status);
			if(leavetabId!=null){
				result = dao.update(em);
			}
			return result;
		}else if(action.equals("getEmployee")){//取得审核人记录
			String text = (String)para.getParameter("query");
			String query = "";
			if(text!=null && !text.equals("")){
				query = " where employee_no like '%"+text+"%' or employee_name like '%"+text+"%'";
			}else{
				query = " limit 0,20";
			}
			Map<String,Object> result = dao.getEmployee(query);
			return JSONObject.fromObject(result);
		}
		return "";
	}
}
