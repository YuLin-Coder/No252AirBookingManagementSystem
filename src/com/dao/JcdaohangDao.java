package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class JcdaohangDao extends SqlSessionDaoSupport{
	@Autowired
	private JcdaohangMapper jcdaohangMapper;

	public List getJcdaohangList(Jcdaohang record,int page,int rows) {
		List<Jcdaohang> list = jcdaohangMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Jcdaohang getJcdaohangById(int id){
		Jcdaohang jcdaohang = jcdaohangMapper.selectByPrimaryKey(id);
		
		return jcdaohang;
	}

	public void update(Jcdaohang jcdaohang) {
		jcdaohangMapper.updateByPrimaryKey(jcdaohang);

	}

	public void delete(Integer id) {
		jcdaohangMapper.deleteByPrimaryKey(id);
	}

	public void add(Jcdaohang jcdaohang) {
		jcdaohangMapper.insert(jcdaohang);
		
	}
}
