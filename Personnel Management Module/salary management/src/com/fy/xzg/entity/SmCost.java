package com.fy.xzg.entity;

import java.sql.Timestamp;

public class SmCost {
	/**工资表编号*/
	private String salary_id;
	/**员工编号*/
	private String employee_no;
	/**基础工资*/
	private float basic_salary;
	/**工作表现工资*/
	private float performance_salary;
	/**考勤工资*/
	private float attend_salary;
	/**年度工资*/
	private float year_sarary;
	/**其他工资*/
	private float other_sarary;
	/**备注*/
	private String remark;
	public String getSalary_id() {
		return salary_id;
	}




	public void setSalary_id(String salary_id) {
		this.salary_id = salary_id;
	}




	public String getEmployee_no() {
		return employee_no;
	}




	public void setEmployee_no(String employee_no) {
		this.employee_no = employee_no;
	}




	public float getBasic_salary() {
		return basic_salary;
	}




	public void setBasic_salary(float basic_salary) {
		this.basic_salary = basic_salary;
	}




	public float getPerformance_salary() {
		return performance_salary;
	}




	public void setPerformance_salary(float performance_salary) {
		this.performance_salary = performance_salary;
	}




	public float getAttend_salary() {
		return attend_salary;
	}




	public void setAttend_salary(float attend_salary) {
		this.attend_salary = attend_salary;
	}




	public float getYear_sarary() {
		return year_sarary;
	}




	public void setYear_sarary(float year_sarary) {
		this.year_sarary = year_sarary;
	}




	public float getOther_sarary() {
		return other_sarary;
	}




	public void setOther_sarary(float other_sarary) {
		this.other_sarary = other_sarary;
	}




	public String getRemark() {
		return remark;
	}




	public void setRemark(String remark) {
		this.remark = remark;
	}
	public SmCost(String salary_id,String employee_no,float basic_salary,float performance_salary,float attend_salary,float year_sarary,float other_sarary,String remark){
		this.salary_id = salary_id;
		this.employee_no = employee_no;
		this.basic_salary = basic_salary;
		this.performance_salary = performance_salary;
		this.attend_salary = attend_salary;
		this.year_sarary = year_sarary;
		this.other_sarary= other_sarary;
		this.remark = remark;
	}
//	public SmCost(String employee_no,double basic_salary,Timestamp sDate,String remark,double performanceSalary,double attendSalary,double yearSarary,double otherSarary){
//		this.employee_no = employee_no;
//		this.basic_salary = basic_salary;
//		this.sDate = sDate;
//		this.remark = remark;
//		this.performanceSalary = performanceSalary;
//		this.attendSalary = attendSalary;
//		this.yearSalary = yearSarary;
//		this.otherSarary = otherSarary;
//	}
	}
