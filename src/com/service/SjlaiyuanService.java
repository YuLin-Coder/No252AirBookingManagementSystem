package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjlaiyuanService {
	@Autowired
	private SjlaiyuanDao sjlaiyuanDao;

	public List querySjlaiyuans(Sjlaiyuan record,int page,int rows) {
		// TODO Auto-generated method stub
		return sjlaiyuanDao.getSjlaiyuanList(record,page,rows);
	}

	public Sjlaiyuan getSjlaiyuan(int parseInt) {
		// TODO Auto-generated method stub
		return sjlaiyuanDao.getSjlaiyuanById(parseInt);
	}

	public void modifySjlaiyuan(Sjlaiyuan sjlaiyuan) {
		// TODO Auto-generated method stub
		sjlaiyuanDao.update(sjlaiyuan);
	}

	public void deleteSjlaiyuan(Integer id) {
		// TODO Auto-generated method stub
		sjlaiyuanDao.delete(id);

	}

	public void save(Sjlaiyuan sjlaiyuan) {
		// TODO Auto-generated method stub
		sjlaiyuanDao.add(sjlaiyuan);

	}

}
