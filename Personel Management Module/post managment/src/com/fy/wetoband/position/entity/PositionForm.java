package com.fy.wetoband.position.entity;

public class PositionForm {
	int ID;
	String employee_no;
	String begDate;
	String endDate;
	int leaveDays;
	int leaveType;
	String leaveReason;
	String memo;
	String status;
	String approverNo;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getEmployee_no() {
		return employee_no;
	}
	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}
	public String getBegDate() {
		return begDate;
	}
	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(int leaveDays) {
		this.leaveDays = leaveDays;
	}
	public int getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(int leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApproverNo() {
		return approverNo;
	}
	public void setApproverNo(String approverNo) {
		this.approverNo = approverNo;
	}
	
	public PositionForm(int ID,String employee_no,String begDate,String endDate,int leaveDays,int leaveType,String leaveReason,String memo,String status,String approverNo){
		this.ID = ID;
		this.employee_no = employee_no;
		this.begDate = begDate;
		this.endDate = endDate;
		this.leaveDays = leaveDays;
		this.leaveType = leaveType;
		this.leaveReason = leaveReason;
		this.memo = memo;
		this.status = status;
		this.approverNo = approverNo;
	}
	
}
