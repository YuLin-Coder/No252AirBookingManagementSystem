package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class SjqitaService {
	@Autowired
	private SjqitaDao sjqitaDao;

	public List querySjqitas(Sjqita record,int page,int rows) {
		// TODO Auto-generated method stub
		return sjqitaDao.getSjqitaList(record,page,rows);
	}

	public Sjqita getSjqita(int parseInt) {
		// TODO Auto-generated method stub
		return sjqitaDao.getSjqitaById(parseInt);
	}

	public void modifySjqita(Sjqita sjqita) {
		// TODO Auto-generated method stub
		sjqitaDao.update(sjqita);
	}

	public void deleteSjqita(Integer id) {
		// TODO Auto-generated method stub
		sjqitaDao.delete(id);

	}

	public void save(Sjqita sjqita) {
		// TODO Auto-generated method stub
		sjqitaDao.add(sjqita);

	}

}
