package org.cs.rmw.service;

import org.cs.core.service.IBaseService;
import org.cs.rmw.model.RmwRepertory;
import org.cs.util.Pager;

import java.util.List;

public interface IRmwRepertoryService extends IBaseService<RmwRepertory> {

	public Pager getRmwRepertorys(Pager page);

	public boolean delRmwRepertory(String id);
	
	public boolean addRmwRepertory(RmwRepertory rmwRepertory);
	
	public boolean updateRmwRepertory(RmwRepertory rmwRepertory);

	List<RmwRepertory> getAllRmwRepertory();
}
