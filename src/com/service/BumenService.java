package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class BumenService {
	@Autowired
	private BumenDao bumenDao;

	public List queryBumens(Bumen record,int page,int rows) {
		// TODO Auto-generated method stub
		return bumenDao.getBumenList(record,page,rows);
	}

	public Bumen getBumen(int parseInt) {
		// TODO Auto-generated method stub
		return bumenDao.getBumenById(parseInt);
	}

	public void modifyBumen(Bumen bumen) {
		// TODO Auto-generated method stub
		bumenDao.update(bumen);
	}

	public void deleteBumen(Integer id) {
		// TODO Auto-generated method stub
		bumenDao.delete(id);

	}

	public void save(Bumen bumen) {
		// TODO Auto-generated method stub
		bumenDao.add(bumen);

	}

}
