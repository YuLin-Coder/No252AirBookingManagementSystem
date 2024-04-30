package com.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.mapper.*;
import com.model.*;
@Repository
public class UserDao extends SqlSessionDaoSupport{
	@Autowired
	private UserMapper userMapper;

	public List getUserList(User record,String username,int page,int rows,String sdate, String edate,String sdate1, String edate1) {
		List<User> list = userMapper.selectAll(record,username,page,rows,sdate,edate,sdate1,edate1);
		return list;
	}
	
	public User getUserById(int id){
		User user = userMapper.selectByPrimaryKey(id);
		
		return user;
	}

	public void update(User user) {
		userMapper.updateByPrimaryKey(user);

	}

	public void delete(Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	public void add(User user) {
		userMapper.insert(user);
		
	}
}
