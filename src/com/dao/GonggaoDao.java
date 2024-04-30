package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class GonggaoDao extends SqlSessionDaoSupport{
	@Autowired
	private GonggaoMapper gonggaoMapper;

	public List getGonggaoList(Gonggao record,int page,int rows,String sdate, String edate) {
		List<Gonggao> list = gonggaoMapper.selectAll(record,page,rows,sdate,edate);
		return list;
	}
	
	public Gonggao getGonggaoById(int id){
		Gonggao gonggao = gonggaoMapper.selectByPrimaryKey(id);
		
		return gonggao;
	}

	public void update(Gonggao gonggao) {
		gonggaoMapper.updateByPrimaryKey(gonggao);

	}

	public void delete(Integer id) {
		gonggaoMapper.deleteByPrimaryKey(id);
	}

	public void add(Gonggao gonggao) {
		gonggaoMapper.insert(gonggao);
		
	}
}
