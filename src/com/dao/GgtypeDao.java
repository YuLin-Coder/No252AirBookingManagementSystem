package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class GgtypeDao extends SqlSessionDaoSupport{
	@Autowired
	private GgtypeMapper ggtypeMapper;

	public List getGgtypeList(Ggtype record,int page,int rows) {
		List<Ggtype> list = ggtypeMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Ggtype getGgtypeById(int id){
		Ggtype ggtype = ggtypeMapper.selectByPrimaryKey(id);
		
		return ggtype;
	}

	public void update(Ggtype ggtype) {
		ggtypeMapper.updateByPrimaryKey(ggtype);

	}

	public void delete(Integer id) {
		ggtypeMapper.deleteByPrimaryKey(id);
	}

	public void add(Ggtype ggtype) {
		ggtypeMapper.insert(ggtype);
		
	}
}
