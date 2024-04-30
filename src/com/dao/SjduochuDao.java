package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjduochuDao extends SqlSessionDaoSupport{
	@Autowired
	private SjduochuMapper sjduochuMapper;

	public List getSjduochuList(Sjduochu record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Sjduochu> list = sjduochuMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Sjduochu getSjduochuById(int id){
		Sjduochu sjduochu = sjduochuMapper.selectByPrimaryKey(id);
		
		return sjduochu;
	}

	public void update(Sjduochu sjduochu) {
		sjduochuMapper.updateByPrimaryKey(sjduochu);

	}

	public void delete(Integer id) {
		sjduochuMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjduochu sjduochu) {
		sjduochuMapper.insert(sjduochu);
		
	}
}
