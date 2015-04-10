package cn.edu.seu.institute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.institute.dao.ArticleDAO;
import cn.edu.seu.institute.entity.Article;

@Service
public class ArticleService {
	@Autowired
	private ArticleDAO articleDAOImpl;

	public List<Article> getCatArticlesByPnAndSize(int catId, int pn,
			int pageSize) {
		return articleDAOImpl.getCatArticlesByBaseAndRange(catId, (pn - 1)
				* pageSize, pageSize);
	}

	public List<Article> getRecentAnnounce() {
		return articleDAOImpl.getCatArticlesByBaseAndRange(0, 0, 5);
	}

	public int getArticleCountByCategory(int id) {
		return (int) articleDAOImpl.getArticleCountByCategory(id);
	}

	public Article getArticleByArticleId(int id) {
		return articleDAOImpl.getArticleByArticleId(id);
	}

	public void insertArticle(Article article) {
		articleDAOImpl.insertArticle(article);
	}

	public void updateArticle(Article article) {
		articleDAOImpl.updateArticle(article);
	}

	public void archiveArticle(int id) {
		articleDAOImpl.archiveArticle(id);
	}
}
