package cn.edu.seu.institute.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.institute.dao.AdminDAO;
import cn.edu.seu.institute.entity.Admin;
import cn.edu.seu.institute.filter.SecurityContextHolder;

@Service
public class AdminService {
	@Autowired
	AdminDAO adminDAOImpl;

	public Admin login(String name, String pswd) {
		Admin admin = adminDAOImpl.getAdminByNameAndPswd(name, pswd);
		SecurityContextHolder.getSecurityContext().setAdmin(admin);
		return admin;
	}

	public void logout() {
		SecurityContextHolder.getSecurityContext().setAdmin(null);
	}

	public Admin getAdminById(int id) {
		return adminDAOImpl.getAdminById(id);
	}

	public Admin currentAdmin() {
		return SecurityContextHolder.getSecurityContext().getAdmin();
	}

	public void changePswd(String pswd) {
		Admin admin = SecurityContextHolder.getSecurityContext().getAdmin();
		admin.setPswd(pswd);
		adminDAOImpl.updateAdmin(admin);
	}
}
