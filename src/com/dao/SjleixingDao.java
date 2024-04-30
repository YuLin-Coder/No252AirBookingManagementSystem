package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjleixingDao extends SqlSessionDaoSupport{
	@Autowired
	private SjleixingMapper sjleixingMapper;

	public List getSjleixingList(Sjleixing record,int page,int rows) {
		List<Sjleixing> list = sjleixingMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjleixing getSjleixingById(int id){
		Sjleixing sjleixing = sjleixingMapper.selectByPrimaryKey(id);
		
		return sjleixing;
	}

	public void update(Sjleixing sjleixing) {
		sjleixingMapper.updateByPrimaryKey(sjleixing);

	}

	public void delete(Integer id) {
		sjleixingMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjleixing sjleixing) {
		sjleixingMapper.insert(sjleixing);
		
	}
}
