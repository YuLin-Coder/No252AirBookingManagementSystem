package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class BuyuanDao extends SqlSessionDaoSupport{
	@Autowired
	private BuyuanMapper buyuanMapper;

	public List getBuyuanList(Buyuan record,int page,int rows) {
		List<Buyuan> list = buyuanMapper.selectAll(record,page,rows);
		return list;
	}
	
	public Buyuan getBuyuanById(int id){
		Buyuan buyuan = buyuanMapper.selectByPrimaryKey(id);
		
		return buyuan;
	}

	public void update(Buyuan buyuan) {
		buyuanMapper.updateByPrimaryKey(buyuan);

	}

	public void delete(Integer id) {
		buyuanMapper.deleteByPrimaryKey(id);
	}

	public void add(Buyuan buyuan) {
		buyuanMapper.insert(buyuan);
		
	}
}
