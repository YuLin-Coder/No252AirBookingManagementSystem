package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjjianchuService {
	@Autowired
	private SjjianchuDao sjjianchuDao;

	public List querySjjianchus(Sjjianchu record,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return sjjianchuDao.getSjjianchuList(record,page,rows,sdate,edate,sdate1,edate1);
	}

	public Sjjianchu getSjjianchu(int parseInt) {
		// TODO Auto-generated method stub
		return sjjianchuDao.getSjjianchuById(parseInt);
	}

	public void modifySjjianchu(Sjjianchu sjjianchu) {
		// TODO Auto-generated method stub
		sjjianchuDao.update(sjjianchu);
	}

	public void deleteSjjianchu(Integer id) {
		// TODO Auto-generated method stub
		sjjianchuDao.delete(id);

	}

	public void save(Sjjianchu sjjianchu) {
		// TODO Auto-generated method stub
		sjjianchuDao.add(sjjianchu);

	}

}
