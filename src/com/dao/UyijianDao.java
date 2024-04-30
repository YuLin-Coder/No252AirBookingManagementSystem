package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class UyijianDao extends SqlSessionDaoSupport{
	@Autowired
	private UyijianMapper uyijianMapper;

	public List getUyijianList(Uyijian record,int page,int rows,String sdate, String edate) {
		List<Uyijian> list = uyijianMapper.selectAll(record,page,rows,sdate,edate);
		return list;
	}
	
	public Uyijian getUyijianById(int id){
		Uyijian uyijian = uyijianMapper.selectByPrimaryKey(id);
		
		return uyijian;
	}

	public void update(Uyijian uyijian) {
		uyijianMapper.updateByPrimaryKey(uyijian);

	}

	public void delete(Integer id) {
		uyijianMapper.deleteByPrimaryKey(id);
	}

	public void add(Uyijian uyijian) {
		uyijianMapper.insert(uyijian);
		
	}
}
