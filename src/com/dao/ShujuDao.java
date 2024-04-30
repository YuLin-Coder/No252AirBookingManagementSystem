package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class ShujuDao extends SqlSessionDaoSupport{
	@Autowired
	private ShujuMapper shujuMapper;

	public List getShujuList(Shuju record,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<Shuju> list = shujuMapper.selectAll(record,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public Shuju getShujuById(int id){
		Shuju shuju = shujuMapper.selectByPrimaryKey(id);
		
		return shuju;
	}

	public void update(Shuju shuju) {
		shujuMapper.updateByPrimaryKey(shuju);

	}

	public void delete(Integer id) {
		shujuMapper.deleteByPrimaryKey(id);
	}

	public void add(Shuju shuju) {
		shujuMapper.insert(shuju);
		
	}
}
