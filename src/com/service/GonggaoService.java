package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class GonggaoService {
	@Autowired
	private GonggaoDao gonggaoDao;

	public List queryGonggaos(Gonggao record,int page,int rows, String sdate, String edate) {
		// TODO Auto-generated method stub
		return gonggaoDao.getGonggaoList(record,page,rows,sdate,edate);
	}

	public Gonggao getGonggao(int parseInt) {
		// TODO Auto-generated method stub
		return gonggaoDao.getGonggaoById(parseInt);
	}

	public void modifyGonggao(Gonggao gonggao) {
		// TODO Auto-generated method stub
		gonggaoDao.update(gonggao);
	}

	public void deleteGonggao(Integer id) {
		// TODO Auto-generated method stub
		gonggaoDao.delete(id);

	}

	public void save(Gonggao gonggao) {
		// TODO Auto-generated method stub
		gonggaoDao.add(gonggao);

	}

}
