package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.dao.EmpMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.entity.TreeNode;
import cn.et.food.entity.EmpExample.Criteria;
import cn.et.food.service.DeptService;
import cn.et.food.utils.EPageTools;




@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	DeptMapper dm;
	@Autowired
	EmpMapper em;
	/* 
	 * 查询部门表
	 */
	public List<TreeNode> queryTreeNode(Integer pid){
		//发起sql语句查询总记录数
		DeptExample de = new DeptExample();
		de.createCriteria().andPidEqualTo(pid);
		List<Dept> dept =dm.selectByExample(de);
		List<TreeNode> deptList = new ArrayList<TreeNode>();
		//用for循环将部门转换成对象
		for(Dept d:dept){
			TreeNode tn = new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			//判断当前节点下是否还有存在子节点
			if(queryTreeNode(d.getId()).size()==0){
				tn.setState("open");
			}
			deptList.add(tn);
		}
		return deptList;
		
	}
	/* 
	 * 查询员工表
	 */
//	public List<Emp> queryEmp(Integer id){
//		EmpExample ee = new EmpExample();
//		if(id!=null)
//			ee.createCriteria().andDeptidEqualTo(id);
//		System.out.println("--------------------------------"+ee+"==========="+id);
//		return em.selectByExample(ee);
//	}
	
	public EPageTools queryEmp(Integer id,String ename,Integer page,Integer rows){
		if(ename==null){
			ename="";
		}
		
		//发起sql语句查询总记录数
		EmpExample se=new EmpExample();
		Criteria criteria = se.createCriteria();
		criteria.andEnameLike("%"+ename+"%");
		if(id!=null){
			criteria.andDeptidEqualTo(id);
		}
		int totalCount=(int)em.countByExample(se);
		EPageTools pt=new EPageTools(page, totalCount, rows);
		RowBounds rb=new RowBounds( pt.getStartIndex()-1, rows);
		List<Emp> list=em.selectByExampleWithRowbounds(se, rb);
		pt.setRows(list);
		return pt;
		
	}
	
	/**
	 * 获取总行数
	 */
	public int queryEmpCount(EmpExample se){
		int totalCount=(int)em.countByExample(se);
		return totalCount;
	}
	
	
	/**
	 * 修改员工信息
	 */
	public void updateEmp(Emp emp) {
		em.updateByPrimaryKey(emp);
		
	}
	/**
	 * 添加员工信息
	 */
	public void saveEmp(Emp emp) {
		em.insert(emp);
		
	}
	/**
	 * 通过id删除员工
	 */
	public void deleteEmp(Integer id) {
		em.deleteByPrimaryKey(id);
		
	}

}
