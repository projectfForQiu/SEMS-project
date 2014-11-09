package com.fy.xzg.entity;
/**
 * 数据表hr_leavetab对应的类
 * @author yuezhengling
 *
 */
public class EmployeeLevection {
	private String leavetabId;
	private String approverId;
	private String employeeNo;
	private String startDate;
	private String endDate;
	private String leaveDay;
	private String leaveType;
	private String leaveReason;
	private String remark;
	private String status;
	public EmployeeLevection(){}
	public EmployeeLevection(String leavetabId,String approverId,String employeeNo,String startDate,
			String endDate,String leaveDay,String leaveType,String leaveReason,String remark,String status){
		this.leavetabId = leavetabId;
		this.approverId = approverId;
		this.employeeNo = employeeNo;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveDay = leaveDay;
		this.leaveType = leaveType;
		this.leaveReason = leaveReason;
		this.remark = remark;
		this.status = status;
		
	}
	public String getLeavetabId() {
		return leavetabId;
	}
	public void setLeavetabId(String leavetabId) {
		this.leavetabId = leavetabId;
	}
	public String getApproverId() {
		return approverId;
	}
	public void setApproverId(String approverId) {
		this.approverId = approverId;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getLeaveDay() {
		return leaveDay;
	}
	public void setLeaveDay(String leaveDay) {
		this.leaveDay = leaveDay;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
