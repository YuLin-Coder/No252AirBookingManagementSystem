package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class YonghuService {
	@Autowired
	private YonghuDao yonghuDao;

	public List queryYonghus(Yonghu record,String yonghuName,int page,int rows, String sdate, String edate, String sdate1, String edate1) {
		// TODO Auto-generated method stub
		return yonghuDao.getYonghuList(record,yonghuName,page,rows,sdate,edate,sdate1,edate1);
	}

	public Yonghu getYonghu(int parseInt) {
		// TODO Auto-generated method stub
		return yonghuDao.getYonghuById(parseInt);
	}

	public void modifyYonghu(Yonghu yonghu) {
		// TODO Auto-generated method stub
		yonghuDao.update(yonghu);
	}

	public void deleteYonghu(Integer id) {
		// TODO Auto-generated method stub
		yonghuDao.delete(id);

	}

	public void save(Yonghu yonghu) {
		// TODO Auto-generated method stub
		yonghuDao.add(yonghu);

	}

}
