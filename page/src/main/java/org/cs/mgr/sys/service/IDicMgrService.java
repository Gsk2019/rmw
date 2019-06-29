package org.cs.mgr.sys.service;

import org.cs.core.model.Dic;
import org.cs.core.service.IBaseService;
import org.cs.util.Pager;

public interface IDicMgrService extends IBaseService<Dic>{

	public Pager getDicList(Pager page);
	
}
