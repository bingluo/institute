package cn.edu.seu.institute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.institute.dao.FileDAO;
import cn.edu.seu.institute.entity.File;

@Service
public class FileService {
	@Autowired
	private FileDAO fileDAOImpl;

	public List<File> getFilesByPnAndSize(int pn, int pageSize) {
		return fileDAOImpl.getRecentFilesByBaseAndRange((pn - 1) * pageSize,
				pageSize);
	}

	public List<File> getRecentFile() {
		return fileDAOImpl.getRecentFilesByBaseAndRange(0, 5);
	}

	public int getFileCount() {
		return fileDAOImpl.getFileCount();
	}

	public void uploadFile(File file) {
		fileDAOImpl.insertFile(file);
	}

	public void deleteFile(int id) {
		fileDAOImpl.archiveFile(id);
	}
}
