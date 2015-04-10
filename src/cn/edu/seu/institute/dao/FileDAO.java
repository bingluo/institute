package cn.edu.seu.institute.dao;

import java.util.List;

import cn.edu.seu.institute.entity.File;

public interface FileDAO {
	List<File> getRecentFilesByBaseAndRange(int base, int range);

	int getFileCount();

	void insertFile(File file);

	void archiveFile(int fileId);
}
