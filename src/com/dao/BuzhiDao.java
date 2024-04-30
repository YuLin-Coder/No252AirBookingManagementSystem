package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class BuzhiDao extends SqlSessionDaoSupport{
	@Autowired
	private BuzhiMapper buzhiMapper;

	public List getBuzhiList(Buzhi record,int page,int rows) {
		List<Buzhi> list = buzhiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Buzhi getBuzhiById(int id){
		Buzhi buzhi = buzhiMapper.selectByPrimaryKey(id);
		
		return buzhi;
	}

	public void update(Buzhi buzhi) {
		buzhiMapper.updateByPrimaryKey(buzhi);

	}

	public void delete(Integer id) {
		buzhiMapper.deleteByPrimaryKey(id);
	}

	public void add(Buzhi buzhi) {
		buzhiMapper.insert(buzhi);
		
	}
}
