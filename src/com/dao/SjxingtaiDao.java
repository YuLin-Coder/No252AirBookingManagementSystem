package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjxingtaiDao extends SqlSessionDaoSupport{
	@Autowired
	private SjxingtaiMapper sjxingtaiMapper;

	public List getSjxingtaiList(Sjxingtai record,int page,int rows) {
		List<Sjxingtai> list = sjxingtaiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjxingtai getSjxingtaiById(int id){
		Sjxingtai sjxingtai = sjxingtaiMapper.selectByPrimaryKey(id);
		
		return sjxingtai;
	}

	public void update(Sjxingtai sjxingtai) {
		sjxingtaiMapper.updateByPrimaryKey(sjxingtai);

	}

	public void delete(Integer id) {
		sjxingtaiMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjxingtai sjxingtai) {
		sjxingtaiMapper.insert(sjxingtai);
		
	}
}
