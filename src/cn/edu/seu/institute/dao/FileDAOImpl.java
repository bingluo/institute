package cn.edu.seu.institute.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.edu.seu.institute.entity.File;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class FileDAOImpl extends SqlMapClientDaoSupport implements FileDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<File> getRecentFilesByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"FILE.selectRecentFilesByBaseAndRange", map);
	}

	@Override
	public int getFileCount() {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"FILE.selectFileCount");
	}

	@Override
	public void insertFile(File file) {
		getSqlMapClientTemplate().insert("FILE.insertFile", file);
	}

	@Override
	public void archiveFile(int fileId) {
		getSqlMapClientTemplate().update("FILE.archiveFile", fileId);
	}
}
