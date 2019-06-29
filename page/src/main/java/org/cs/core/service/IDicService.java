package org.cs.core.service;

import java.util.List;

import org.cs.core.model.Dic;

public interface IDicService extends IBaseService<Dic>{

	List<Dic> getByType(String type);

	Dic getByTypeAndCode(String type, String code);

}
