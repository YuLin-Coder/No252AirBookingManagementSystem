package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class RizhiService {
	@Autowired
	private RizhiDao rizhiDao;

	public List queryRizhis(Rizhi record,int page,int rows) {
		// TODO Auto-generated method stub
		return rizhiDao.getRizhiList(record,page,rows);
	}

	public Rizhi getRizhi(int parseInt) {
		// TODO Auto-generated method stub
		return rizhiDao.getRizhiById(parseInt);
	}

	public void modifyRizhi(Rizhi rizhi) {
		// TODO Auto-generated method stub
		rizhiDao.update(rizhi);
	}

	public void deleteRizhi(Integer id) {
		// TODO Auto-generated method stub
		rizhiDao.delete(id);

	}

	public void save(Rizhi rizhi) {
		// TODO Auto-generated method stub
		rizhiDao.add(rizhi);

	}

}
