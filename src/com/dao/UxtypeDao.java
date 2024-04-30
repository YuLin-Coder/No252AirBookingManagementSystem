package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class UxtypeDao extends SqlSessionDaoSupport{
	@Autowired
	private UxtypeMapper uxtypeMapper;

	public List getUxtypeList(Uxtype record,int page,int rows) {
		List<Uxtype> list = uxtypeMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Uxtype getUxtypeById(int id){
		Uxtype uxtype = uxtypeMapper.selectByPrimaryKey(id);
		
		return uxtype;
	}

	public void update(Uxtype uxtype) {
		uxtypeMapper.updateByPrimaryKey(uxtype);

	}

	public void delete(Integer id) {
		uxtypeMapper.deleteByPrimaryKey(id);
	}

	public void add(Uxtype uxtype) {
		uxtypeMapper.insert(uxtype);
		
	}
}
