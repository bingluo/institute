package cn.edu.seu.institute.dao;

import cn.edu.seu.institute.entity.Admin;

public interface AdminDAO {
	Admin getAdminById(int id);

	Admin getAdminByNameAndPswd(String name, String pswd);

	void insertAdmin(Admin admin);

	void updateAdmin(Admin admin);
}
