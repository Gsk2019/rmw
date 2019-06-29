package org.cs.mgr.sys.service.impl;

import org.cs.core.model.Dic;
import org.cs.core.service.impl.BaseService;
import org.cs.mgr.sys.service.IDicMgrService;
import org.cs.util.Pager;
import org.springframework.stereotype.Service;

@Service("dicMgrService")
public class DicMgrService extends BaseService<Dic> implements IDicMgrService{

	@Override
	public Pager getDicList(Pager page) {
		// TODO Auto-generated method stub
		return this.findPageByHql("from Dic", page, null);
	}

}
