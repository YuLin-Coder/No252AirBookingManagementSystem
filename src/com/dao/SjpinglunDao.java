package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjpinglunDao extends SqlSessionDaoSupport{
	@Autowired
	private SjpinglunMapper sjpinglunMapper;

	public List getSjpinglunList(Sjpinglun record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Sjpinglun> list = sjpinglunMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Sjpinglun getSjpinglunById(int id){
		Sjpinglun sjpinglun = sjpinglunMapper.selectByPrimaryKey(id);
		
		return sjpinglun;
	}

	public void update(Sjpinglun sjpinglun) {
		sjpinglunMapper.updateByPrimaryKey(sjpinglun);

	}

	public void delete(Integer id) {
		sjpinglunMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjpinglun sjpinglun) {
		sjpinglunMapper.insert(sjpinglun);
		
	}
}
