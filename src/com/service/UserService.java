package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public List queryUsers(User record,String userName,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return userDao.getUserList(record,userName,page,rows,sdate,edate,sdate1,edate1);
	}

	public User getUser(int parseInt) {
		// TODO Auto-generated method stub
		return userDao.getUserById(parseInt);
	}

	public void modifyUser(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDao.delete(id);

	}

	public void save(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);

	}

}
