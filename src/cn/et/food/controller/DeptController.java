package cn.et.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.et.food.entity.Emp;
import cn.et.food.entity.Result;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
import cn.et.food.utils.EPageTools;


@Controller
public class DeptController {
	@Autowired
	DeptService service;
	/**
	 * 查询的方法
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryDept")
	public List<TreeNode> queryDept(Integer id){
		if(id==null){
			id=0;
		}
		return service.queryTreeNode(id);
	}
	
//	@Autowired
//	DeptService ds;
//	@ResponseBody
//	@RequestMapping("/queryEmp1")
//	public List<Emp> queryEmp(Integer id){
//		System.out.println(id+"=====================");
//		return ds.queryEmp(id);
//	}
	/**
	 * 查询的方法
	 */
	@ResponseBody
	@RequestMapping("/queryEmp")
	public EPageTools queryEmp(Integer id,String ename,Integer page,Integer rows){
		
		return service.queryEmp(id,ename, page, rows);
	}
	
	/**
	 * 修改员工信息
	 */
	@ResponseBody
	@RequestMapping(value="/updateEmp/{id}",method=RequestMethod.PUT)
	public Result updateEmp(@PathVariable Integer id,Emp emp){
	
		emp.setId(id);
		Result rt=new Result();
		rt.setCode(1);
		try {
			service.updateEmp(emp);
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;
	}
	/**
	 *添加员工信息
	 */
	@ResponseBody
	@RequestMapping(value="/saveEmp",method=RequestMethod.POST)
	public Result saveEmp(Emp emp){
		Result rt=new Result();
		rt.setCode(1);
		try {
			
			service.saveEmp(emp);
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;
	}
	
	/**
	 * 删除学生
	 */
	@ResponseBody
	@RequestMapping(value="/deleteEmp/{id}",method=RequestMethod.DELETE)
	public Result deleteEmp(@PathVariable String id){
		//删除多行
		String[] str=id.split(",");
		Result rt = new Result();
		rt.setCode(1);
		try {
			for(String s:str){
				service.deleteEmp(Integer.parseInt(s));
			}
		} catch (Exception e) {
			rt.setCode(0);
			rt.setMessage(e);
		}
		return rt;
	}
	
}
