package cn.edu.seu.institute.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.edu.seu.institute.entity.Admin;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class AdminDAOImpl extends SqlMapClientDaoSupport implements AdminDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public Admin getAdminByNameAndPswd(String name, String pswd) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPswd(pswd);
		return (Admin) getSqlMapClientTemplate().queryForObject(
				"ADMIN.selectAdminByNameAndPswd", admin);
	}

	@Override
	public void insertAdmin(Admin admin) {
		getSqlMapClientTemplate().insert("ADMIN.insertAdmin", admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		getSqlMapClientTemplate().update("ADMIN.updateAdmin", admin);
	}

	@Override
	public Admin getAdminById(int id) {
		return (Admin) getSqlMapClientTemplate().queryForObject(
				"ADMIN.selectAdminById", id);
	}
}
