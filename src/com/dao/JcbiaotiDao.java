package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class JcbiaotiDao extends SqlSessionDaoSupport{
	@Autowired
	private JcbiaotiMapper jcbiaotiMapper;

	public List getJcbiaotiList(Jcbiaoti record,int page,int rows) {
		List<Jcbiaoti> list = jcbiaotiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Jcbiaoti getJcbiaotiById(int id){
		Jcbiaoti jcbiaoti = jcbiaotiMapper.selectByPrimaryKey(id);
		
		return jcbiaoti;
	}

	public void update(Jcbiaoti jcbiaoti) {
		jcbiaotiMapper.updateByPrimaryKey(jcbiaoti);

	}

	public void delete(Integer id) {
		jcbiaotiMapper.deleteByPrimaryKey(id);
	}

	public void add(Jcbiaoti jcbiaoti) {
		jcbiaotiMapper.insert(jcbiaoti);
		
	}
}
