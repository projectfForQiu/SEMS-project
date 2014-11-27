package com.fy.xzg.dao;

import java.util.Map;

import com.fy.xzg.entity.EmployeeLevection;
/**
 * 数据表hr_leavetab的dao接口类，定义对该表的各种操作
 * @author Administrator
 *
 */
public interface EmployeeLevectionDao {
	/**
	 * 根据查询条件添加分页查询员工出差记录
	 * @param page 查询第几页
	 * @param size 每页的记录数
	 * @param query 查询条件
	 * @return 员工出差记录
	 */
	Map<String, Object> getList(int page, int size, String query);

	/**
	 * 根据id删除出差记录
	 * @param id 出差记录id
	 * @return success：成功，failure：失败
	 */
	String delete(String id);

	/**
	 * 更新员工出差记录
	 * @param em 记录信息
	 * @return success：成功，failure：失败
	 */
	String update(EmployeeLevection em);

	/**
	 * 添加员工出差记录
	 * @param em 出差记录信息
	 * @return success：成功，failure：失败
	 */
	String add(EmployeeLevection em);

	/**
	 * 查询出差记录id是否已存在
	 * @param id 要查询的id
	 * @return true：已存在，false：不存在
	 */
	boolean hasId(String id);

	/**
	 * 根据查询条件查询审核人信息
	 * @param query 查询条件
	 * @return 审核人信息
	 */
	Map<String, Object> getEmployee(String query);
}
