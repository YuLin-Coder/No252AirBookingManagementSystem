package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjduochuService {
	@Autowired
	private SjduochuDao sjduochuDao;

	public List querySjduochus(Sjduochu record,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return sjduochuDao.getSjduochuList(record,page,rows,sdate,edate,sdate1,edate1);
	}

	public Sjduochu getSjduochu(int parseInt) {
		// TODO Auto-generated method stub
		return sjduochuDao.getSjduochuById(parseInt);
	}

	public void modifySjduochu(Sjduochu sjduochu) {
		// TODO Auto-generated method stub
		sjduochuDao.update(sjduochu);
	}

	public void deleteSjduochu(Integer id) {
		// TODO Auto-generated method stub
		sjduochuDao.delete(id);

	}

	public void save(Sjduochu sjduochu) {
		// TODO Auto-generated method stub
		sjduochuDao.add(sjduochu);

	}

}
