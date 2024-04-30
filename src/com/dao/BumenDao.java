package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class BumenDao extends SqlSessionDaoSupport{
	@Autowired
	private BumenMapper bumenMapper;

	public List getBumenList(Bumen record,int page,int rows) {
		List<Bumen> list = bumenMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Bumen getBumenById(int id){
		Bumen bumen = bumenMapper.selectByPrimaryKey(id);
		
		return bumen;
	}

	public void update(Bumen bumen) {
		bumenMapper.updateByPrimaryKey(bumen);

	}

	public void delete(Integer id) {
		bumenMapper.deleteByPrimaryKey(id);
	}

	public void add(Bumen bumen) {
		bumenMapper.insert(bumen);
		
	}
}
