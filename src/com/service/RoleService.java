package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.*;
import com.model.*;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public List queryRoles(Role record,int page,int rows) {
		// TODO Auto-generated method stub
		return roleDao.getRoleList(record,page,rows);
	}

	public Role getRole(int parseInt) {
		// TODO Auto-generated method stub
		return roleDao.getRoleById(parseInt);
	}

	public void modifyRole(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	public void deleteRole(Integer id) {
		// TODO Auto-generated method stub
		roleDao.delete(id);

	}

	public void save(Role role) {
		// TODO Auto-generated method stub
		roleDao.add(role);

	}

}
