package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjlaiyuanDao extends SqlSessionDaoSupport{
	@Autowired
	private SjlaiyuanMapper sjlaiyuanMapper;

	public List getSjlaiyuanList(Sjlaiyuan record,int page,int rows) {
		List<Sjlaiyuan> list = sjlaiyuanMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjlaiyuan getSjlaiyuanById(int id){
		Sjlaiyuan sjlaiyuan = sjlaiyuanMapper.selectByPrimaryKey(id);
		
		return sjlaiyuan;
	}

	public void update(Sjlaiyuan sjlaiyuan) {
		sjlaiyuanMapper.updateByPrimaryKey(sjlaiyuan);

	}

	public void delete(Integer id) {
		sjlaiyuanMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjlaiyuan sjlaiyuan) {
		sjlaiyuanMapper.insert(sjlaiyuan);
		
	}
}
