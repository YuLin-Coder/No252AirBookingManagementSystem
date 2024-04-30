package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjjianchuDao extends SqlSessionDaoSupport{
	@Autowired
	private SjjianchuMapper sjjianchuMapper;

	public List getSjjianchuList(Sjjianchu record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Sjjianchu> list = sjjianchuMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Sjjianchu getSjjianchuById(int id){
		Sjjianchu sjjianchu = sjjianchuMapper.selectByPrimaryKey(id);
		
		return sjjianchu;
	}

	public void update(Sjjianchu sjjianchu) {
		sjjianchuMapper.updateByPrimaryKey(sjjianchu);

	}

	public void delete(Integer id) {
		sjjianchuMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjjianchu sjjianchu) {
		sjjianchuMapper.insert(sjjianchu);
		
	}
}
