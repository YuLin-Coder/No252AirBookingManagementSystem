package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class GgpinglunDao extends SqlSessionDaoSupport{
	@Autowired
	private GgpinglunMapper ggpinglunMapper;

	public List getGgpinglunList(Ggpinglun record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Ggpinglun> list = ggpinglunMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Ggpinglun getGgpinglunById(int id){
		Ggpinglun ggpinglun = ggpinglunMapper.selectByPrimaryKey(id);
		
		return ggpinglun;
	}

	public void update(Ggpinglun ggpinglun) {
		ggpinglunMapper.updateByPrimaryKey(ggpinglun);

	}

	public void delete(Integer id) {
		ggpinglunMapper.deleteByPrimaryKey(id);
	}

	public void add(Ggpinglun ggpinglun) {
		ggpinglunMapper.insert(ggpinglun);
		
	}
}
