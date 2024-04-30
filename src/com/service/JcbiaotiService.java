package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class JcbiaotiService {
	@Autowired
	private JcbiaotiDao jcbiaotiDao;

	public List queryJcbiaotis(Jcbiaoti record,int page,int rows) {
		// TODO Auto-generated method stub
		return jcbiaotiDao.getJcbiaotiList(record,page,rows);
	}

	public Jcbiaoti getJcbiaoti(int parseInt) {
		// TODO Auto-generated method stub
		return jcbiaotiDao.getJcbiaotiById(parseInt);
	}

	public void modifyJcbiaoti(Jcbiaoti jcbiaoti) {
		// TODO Auto-generated method stub
		jcbiaotiDao.update(jcbiaoti);
	}

	public void deleteJcbiaoti(Integer id) {
		// TODO Auto-generated method stub
		jcbiaotiDao.delete(id);

	}

	public void save(Jcbiaoti jcbiaoti) {
		// TODO Auto-generated method stub
		jcbiaotiDao.add(jcbiaoti);

	}

}
