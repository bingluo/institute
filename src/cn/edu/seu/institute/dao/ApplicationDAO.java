package cn.edu.seu.institute.dao;

import java.util.List;

import cn.edu.seu.institute.entity.Application;

public interface ApplicationDAO {
	List<Application> ApplicationByBaseAndRange(int base, int range);

	int getApplicationCount();

	List<Application> getApplications();

	Application getApplicationById(int id);

	Application getApplicationByName(String name);

	void insertApplication(Application application);

	void updateApplication(Application application);
}
