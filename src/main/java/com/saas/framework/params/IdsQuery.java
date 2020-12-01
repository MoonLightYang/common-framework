package com.saas.framework.params;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IdsQuery extends IdVersion {

	private List<Integer> items;

	public IdsQuery(Integer id, List<Integer> items, Integer enterpriseId) {
		this.id = id;
		this.items = items;
		this.enterpriseId = enterpriseId;
	}

	public IdsQuery(List<Integer> items, Integer enterpriseId) {
		this.items = items;
		this.enterpriseId = enterpriseId;
	}

}
