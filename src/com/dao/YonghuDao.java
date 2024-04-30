package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class YonghuDao extends SqlSessionDaoSupport{
	@Autowired
	private YonghuMapper yonghuMapper;

	public List getYonghuList(Yonghu record,String yonghuname,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Yonghu> list = yonghuMapper.selectAll(record,yonghuname,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Yonghu getYonghuById(int id){
		Yonghu yonghu = yonghuMapper.selectByPrimaryKey(id);
		
		return yonghu;
	}

	public void update(Yonghu yonghu) {
		yonghuMapper.updateByPrimaryKey(yonghu);

	}

	public void delete(Integer id) {
		yonghuMapper.deleteByPrimaryKey(id);
	}

	public void add(Yonghu yonghu) {
		yonghuMapper.insert(yonghu);
		
	}
}
