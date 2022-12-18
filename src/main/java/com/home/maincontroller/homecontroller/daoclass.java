package com.home.maincontroller.homecontroller;


import java.util.List;

import org.springframework.stereotype.Service;       

@Service
public interface daoclass {

	public void add(model m);
	public List<model> show();
	public void delete(int id);
	public void update(model m);
	public List<model> pdf(int id);
	public model edit(int id);
}
