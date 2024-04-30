package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class GgtypeService {
	@Autowired
	private GgtypeDao ggtypeDao;

	public List queryGgtypes(Ggtype record,int page,int rows) {
		// TODO Auto-generated method stub
		return ggtypeDao.getGgtypeList(record,page,rows);
	}

	public Ggtype getGgtype(int parseInt) {
		// TODO Auto-generated method stub
		return ggtypeDao.getGgtypeById(parseInt);
	}

	public void modifyGgtype(Ggtype ggtype) {
		// TODO Auto-generated method stub
		ggtypeDao.update(ggtype);
	}

	public void deleteGgtype(Integer id) {
		// TODO Auto-generated method stub
		ggtypeDao.delete(id);

	}

	public void save(Ggtype ggtype) {
		// TODO Auto-generated method stub
		ggtypeDao.add(ggtype);

	}

}
