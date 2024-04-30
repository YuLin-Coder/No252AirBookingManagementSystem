package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class AdminDao extends SqlSessionDaoSupport{
	@Autowired
	private AdminMapper adminMapper;

	public List getAdminList(Admin record,int page,int rows) {
		List<Admin> list = adminMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Admin getAdminById(int id){
		Admin admin = adminMapper.selectByPrimaryKey(id);
		
		return admin;
	}

	public void update(Admin admin) {
		adminMapper.updateByPrimaryKey(admin);

	}

	public void delete(Integer id) {
		adminMapper.deleteByPrimaryKey(id);
	}

	public void add(Admin admin) {
		adminMapper.insert(admin);
		
	}
}
