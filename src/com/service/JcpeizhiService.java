package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class JcpeizhiService {
	@Autowired
	private JcpeizhiDao jcpeizhiDao;

	public List queryJcpeizhis(Jcpeizhi record,int page,int rows) {
		// TODO Auto-generated method stub
		return jcpeizhiDao.getJcpeizhiList(record,page,rows);
	}

	public Jcpeizhi getJcpeizhi(int parseInt) {
		// TODO Auto-generated method stub
		return jcpeizhiDao.getJcpeizhiById(parseInt);
	}

	public void modifyJcpeizhi(Jcpeizhi jcpeizhi) {
		// TODO Auto-generated method stub
		jcpeizhiDao.update(jcpeizhi);
	}

	public void deleteJcpeizhi(Integer id) {
		// TODO Auto-generated method stub
		jcpeizhiDao.delete(id);

	}

	public void save(Jcpeizhi jcpeizhi) {
		// TODO Auto-generated method stub
		jcpeizhiDao.add(jcpeizhi);

	}

}
