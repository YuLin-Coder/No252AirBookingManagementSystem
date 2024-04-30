package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class GgpinglunService {
	@Autowired
	private GgpinglunDao ggpinglunDao;

	public List queryGgpingluns(Ggpinglun record,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return ggpinglunDao.getGgpinglunList(record,page,rows,sdate,edate,sdate1,edate1);
	}

	public Ggpinglun getGgpinglun(int parseInt) {
		// TODO Auto-generated method stub
		return ggpinglunDao.getGgpinglunById(parseInt);
	}

	public void modifyGgpinglun(Ggpinglun ggpinglun) {
		// TODO Auto-generated method stub
		ggpinglunDao.update(ggpinglun);
	}

	public void deleteGgpinglun(Integer id) {
		// TODO Auto-generated method stub
		ggpinglunDao.delete(id);

	}

	public void save(Ggpinglun ggpinglun) {
		// TODO Auto-generated method stub
		ggpinglunDao.add(ggpinglun);

	}

}
