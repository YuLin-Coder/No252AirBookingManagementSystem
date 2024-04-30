package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class RoleDao extends SqlSessionDaoSupport{
	@Autowired
	private RoleMapper roleMapper;

	public List getRoleList(Role record,int page,int rows) {
		List<Role> list = roleMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Role getRoleById(int id){
		Role role = roleMapper.selectByPrimaryKey(id);
		
		return role;
	}

	public void update(Role role) {
		roleMapper.updateByPrimaryKey(role);

	}

	public void delete(Integer id) {
		roleMapper.deleteByPrimaryKey(id);
	}

	public void add(Role role) {
		roleMapper.insert(role);
		
	}
}
