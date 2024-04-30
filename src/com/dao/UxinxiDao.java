package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class UxinxiDao extends SqlSessionDaoSupport{
	@Autowired
	private UxinxiMapper uxinxiMapper;

	public List getUxinxiList(Uxinxi record,int page,int rows,String sdate, String edate) {
		List<Uxinxi> list = uxinxiMapper.selectAll(record,page,rows,sdate,edate);
		return list;
	}
	
	public Uxinxi getUxinxiById(int id){
		Uxinxi uxinxi = uxinxiMapper.selectByPrimaryKey(id);
		
		return uxinxi;
	}

	public void update(Uxinxi uxinxi) {
		uxinxiMapper.updateByPrimaryKey(uxinxi);

	}

	public void delete(Integer id) {
		uxinxiMapper.deleteByPrimaryKey(id);
	}

	public void add(Uxinxi uxinxi) {
		uxinxiMapper.insert(uxinxi);
		
	}
}
