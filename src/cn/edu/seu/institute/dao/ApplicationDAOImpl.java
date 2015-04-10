package cn.edu.seu.institute.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.edu.seu.institute.entity.Application;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class ApplicationDAOImpl extends SqlMapClientDaoSupport implements
		ApplicationDAO {
	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Application> ApplicationByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"APPLICATION.selectApplicationByBaseAndRange", map);
	}

	@Override
	public int getApplicationCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"APPLICATION.selectApplicationCount");
	}

	@Override
	public List<Application> getApplications() {
		return getSqlMapClientTemplate().queryForList(
				"APPLICATION.selectApplications");
	}

	@Override
	public Application getApplicationById(int id) {
		return (Application) getSqlMapClientTemplate().queryForObject(
				"APPLICATION.application", id);
	}

	@Override
	public Application getApplicationByName(String name) {
		return (Application) getSqlMapClientTemplate().queryForObject(
				"APPLICATION.selectApplicationByName", name);
	}

	@Override
	public void insertApplication(Application application) {
		getSqlMapClientTemplate().insert("APPLICATION.insertApplication",
				application);
	}

	@Override
	public void updateApplication(Application application) {
		getSqlMapClientTemplate().update("APPLICATION.updateApplication",
				application);
	}
}
