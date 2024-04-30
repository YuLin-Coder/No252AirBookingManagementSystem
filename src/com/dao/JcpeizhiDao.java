package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class JcpeizhiDao extends SqlSessionDaoSupport{
	@Autowired
	private JcpeizhiMapper jcpeizhiMapper;

	public List getJcpeizhiList(Jcpeizhi record,int page,int rows) {
		List<Jcpeizhi> list = jcpeizhiMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Jcpeizhi getJcpeizhiById(int id){
		Jcpeizhi jcpeizhi = jcpeizhiMapper.selectByPrimaryKey(id);
		
		return jcpeizhi;
	}

	public void update(Jcpeizhi jcpeizhi) {
		jcpeizhiMapper.updateByPrimaryKey(jcpeizhi);

	}

	public void delete(Integer id) {
		jcpeizhiMapper.deleteByPrimaryKey(id);
	}

	public void add(Jcpeizhi jcpeizhi) {
		jcpeizhiMapper.insert(jcpeizhi);
		
	}
}
