package cn.edu.seu.institute.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.seu.institute.dao.ApplicationDAO;
import cn.edu.seu.institute.entity.Application;

@Service
public class ApplicationService {

	@Autowired
	ApplicationDAO applicationDAOImpl;

	public List<Application> getApplicationsByPnAndPageSize(int pn, int pageSize) {
		return applicationDAOImpl.ApplicationByBaseAndRange(
				(pn - 1) * pageSize, pageSize);
	}

	public int getApplicationCount() {
		return applicationDAOImpl.getApplicationCount();
	}

	public List<Application> getApplications() {
		return applicationDAOImpl.getApplications();
	}

	public Application getApplicationById(int id) {
		return applicationDAOImpl.getApplicationById(id);
	}

	public Application getApplicationByName(String name) {
		return applicationDAOImpl.getApplicationByName(name);
	}

	public void insertApplication(Application application) {
		applicationDAOImpl.insertApplication(application);
	}

	public void updateApplication(Application application) {
		applicationDAOImpl.updateApplication(application);
	}
}
