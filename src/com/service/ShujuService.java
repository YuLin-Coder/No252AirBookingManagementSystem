package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class ShujuService {
	@Autowired
	private ShujuDao shujuDao;

	public List queryShujus(Shuju record,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return shujuDao.getShujuList(record,page,rows,sdate,edate,sdate1,edate1);
	}

	public Shuju getShuju(int parseInt) {
		// TODO Auto-generated method stub
		return shujuDao.getShujuById(parseInt);
	}

	public void modifyShuju(Shuju shuju) {
		// TODO Auto-generated method stub
		shujuDao.update(shuju);
	}

	public void deleteShuju(Integer id) {
		// TODO Auto-generated method stub
		shujuDao.delete(id);

	}

	public void save(Shuju shuju) {
		// TODO Auto-generated method stub
		shujuDao.add(shuju);

	}

}
