package cn.et.food.service;

import java.util.List;

import cn.et.food.entity.Emp;
import cn.et.food.entity.TreeNode;
import cn.et.food.utils.EPageTools;


public interface DeptService {

	public abstract List<TreeNode> queryTreeNode(Integer id);
	//public abstract List<Emp> queryEmp(Integer id);
	/**
	 * 查询方法
	 */
	public abstract EPageTools queryEmp(Integer id,String ename,Integer page,Integer rows);
	/**
	 * 修改员工信息
	 */
	public abstract void updateEmp(Emp emp);
	/**
	 * 添加员工信息
	 */
	public abstract void saveEmp(Emp emp);
	
	/**
	 * 通过id删除员工
	 */
	public abstract void deleteEmp(Integer id);
	
}