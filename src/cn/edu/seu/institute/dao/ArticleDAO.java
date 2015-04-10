package cn.edu.seu.institute.dao;

import java.util.List;

import cn.edu.seu.institute.entity.Article;

public interface ArticleDAO {
	List<Article> getCatArticlesByBaseAndRange(int catId, int base, int range);

	int getArticleCountByCategory(int id);

	Article getArticleByArticleId(int id);

	void insertArticle(Article article);

	void updateArticle(Article article);

	void archiveArticle(int id);
}
