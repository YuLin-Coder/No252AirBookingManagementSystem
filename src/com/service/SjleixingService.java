package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjleixingService {
	@Autowired
	private SjleixingDao sjleixingDao;

	public List querySjleixings(Sjleixing record,int page,int rows) {
		// TODO Auto-generated method stub
		return sjleixingDao.getSjleixingList(record,page,rows);
	}

	public Sjleixing getSjleixing(int parseInt) {
		// TODO Auto-generated method stub
		return sjleixingDao.getSjleixingById(parseInt);
	}

	public void modifySjleixing(Sjleixing sjleixing) {
		// TODO Auto-generated method stub
		sjleixingDao.update(sjleixing);
	}

	public void deleteSjleixing(Integer id) {
		// TODO Auto-generated method stub
		sjleixingDao.delete(id);

	}

	public void save(Sjleixing sjleixing) {
		// TODO Auto-generated method stub
		sjleixingDao.add(sjleixing);

	}

}
