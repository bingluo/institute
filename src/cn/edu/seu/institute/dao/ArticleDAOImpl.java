package cn.edu.seu.institute.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.edu.seu.institute.entity.Article;

import com.ibatis.sqlmap.client.SqlMapClient;

@Component
public class ArticleDAOImpl extends SqlMapClientDaoSupport implements
		ArticleDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Article> getCatArticlesByBaseAndRange(int catId, int base,
			int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("catId", catId);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"ARTICLE.selectRecentArticlesByBaseAndRange", map);
	}

	@Override
	public int getArticleCountByCategory(int id) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectArticleCountByCategory", id);
	}

	@Override
	public Article getArticleByArticleId(int id) {
		return (Article) getSqlMapClientTemplate().queryForObject(
				"ARTICLE.selectArticleByArticleId", id);
	}

	@Override
	public void insertArticle(Article article) {
		getSqlMapClientTemplate().insert("ARTICLE.insertArticle", article);
	}

	@Override
	public void updateArticle(Article article) {
		getSqlMapClientTemplate().update("ARTICLE.updateArticle", article);
	}

	@Override
	public void archiveArticle(int id) {
		getSqlMapClientTemplate().update("ARTICLE.archiveArticle", id);
	}

}
