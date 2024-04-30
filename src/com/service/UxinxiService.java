package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class UxinxiService {
	@Autowired
	private UxinxiDao uxinxiDao;

	public List queryUxinxis(Uxinxi record,int page,int rows, String sdate, String edate) {
		// TODO Auto-generated method stub
		return uxinxiDao.getUxinxiList(record,page,rows,sdate,edate);
	}

	public Uxinxi getUxinxi(int parseInt) {
		// TODO Auto-generated method stub
		return uxinxiDao.getUxinxiById(parseInt);
	}

	public void modifyUxinxi(Uxinxi uxinxi) {
		// TODO Auto-generated method stub
		uxinxiDao.update(uxinxi);
	}

	public void deleteUxinxi(Integer id) {
		// TODO Auto-generated method stub
		uxinxiDao.delete(id);

	}

	public void save(Uxinxi uxinxi) {
		// TODO Auto-generated method stub
		uxinxiDao.add(uxinxi);

	}

}
