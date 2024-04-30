package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class BuyuanService {
	@Autowired
	private BuyuanDao buyuanDao;

	public List queryBuyuans(Buyuan record,int page,int rows) {
		// TODO Auto-generated method stub
		return buyuanDao.getBuyuanList(record,page,rows);
	}

	public Buyuan getBuyuan(int parseInt) {
		// TODO Auto-generated method stub
		return buyuanDao.getBuyuanById(parseInt);
	}

	public void modifyBuyuan(Buyuan buyuan) {
		// TODO Auto-generated method stub
		buyuanDao.update(buyuan);
	}

	public void deleteBuyuan(Integer id) {
		// TODO Auto-generated method stub
		buyuanDao.delete(id);

	}

	public void save(Buyuan buyuan) {
		// TODO Auto-generated method stub
		buyuanDao.add(buyuan);

	}

}
