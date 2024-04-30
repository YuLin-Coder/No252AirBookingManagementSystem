package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class SjqitaDao extends SqlSessionDaoSupport{
	@Autowired
	private SjqitaMapper sjqitaMapper;

	public List getSjqitaList(Sjqita record,int page,int rows) {
		List<Sjqita> list = sjqitaMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Sjqita getSjqitaById(int id){
		Sjqita sjqita = sjqitaMapper.selectByPrimaryKey(id);
		
		return sjqita;
	}

	public void update(Sjqita sjqita) {
		sjqitaMapper.updateByPrimaryKey(sjqita);

	}

	public void delete(Integer id) {
		sjqitaMapper.deleteByPrimaryKey(id);
	}

	public void add(Sjqita sjqita) {
		sjqitaMapper.insert(sjqita);
		
	}
}
