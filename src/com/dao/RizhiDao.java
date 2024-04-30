package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class RizhiDao extends SqlSessionDaoSupport{
	@Autowired
	private RizhiMapper rizhiMapper;

	public List getRizhiList(Rizhi record,int page,int rows) {
		List<Rizhi> list = rizhiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Rizhi getRizhiById(int id){
		Rizhi rizhi = rizhiMapper.selectByPrimaryKey(id);
		
		return rizhi;
	}

	public void update(Rizhi rizhi) {
		rizhiMapper.updateByPrimaryKey(rizhi);

	}

	public void delete(Integer id) {
		rizhiMapper.deleteByPrimaryKey(id);
	}

	public void add(Rizhi rizhi) {
		rizhiMapper.insert(rizhi);
		
	}
}
