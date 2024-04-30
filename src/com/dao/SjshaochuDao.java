package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjshaochuDao extends SqlSessionDaoSupport{
	@Autowired
	private SjshaochuMapper sjshaochuMapper;

	public List getSjshaochuList(Sjshaochu record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Sjshaochu> list = sjshaochuMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Sjshaochu getSjshaochuById(int id){
		Sjshaochu sjshaochu = sjshaochuMapper.selectByPrimaryKey(id);
		
		return sjshaochu;
	}

	public void update(Sjshaochu sjshaochu) {
		sjshaochuMapper.updateByPrimaryKey(sjshaochu);

	}

	public void delete(Integer id) {
		sjshaochuMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjshaochu sjshaochu) {
		sjshaochuMapper.insert(sjshaochu);
		
	}
}
