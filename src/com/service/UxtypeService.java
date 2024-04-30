package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class UxtypeService {
	@Autowired
	private UxtypeDao uxtypeDao;

	public List queryUxtypes(Uxtype record,int page,int rows) {
		// TODO Auto-generated method stub
		return uxtypeDao.getUxtypeList(record,page,rows);
	}

	public Uxtype getUxtype(int parseInt) {
		// TODO Auto-generated method stub
		return uxtypeDao.getUxtypeById(parseInt);
	}

	public void modifyUxtype(Uxtype uxtype) {
		// TODO Auto-generated method stub
		uxtypeDao.update(uxtype);
	}

	public void deleteUxtype(Integer id) {
		// TODO Auto-generated method stub
		uxtypeDao.delete(id);

	}

	public void save(Uxtype uxtype) {
		// TODO Auto-generated method stub
		uxtypeDao.add(uxtype);

	}

}
