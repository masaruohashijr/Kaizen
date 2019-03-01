package com.logus.kaizen.model.util;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
/**
 *
 * @author Roberto Araújo
 * @since 1 de mar de 2019
 * @version 1.0.0
 *
 */
public class YokaiListener {

	@PostPersist
	public void onInsert(Object object) {
		System.out.println("INSERT" + "-" + object.toString());
	}

	@PostUpdate
	public void onUpdate(Object object){
		System.out.println("UPDATE" + "-" + object.toString());
	}

	@PostRemove
	public void onDelete(Object object) {
		System.out.println("DELETE" + "-" + object.toString());
	}

	@PostLoad
	public void onLoad(Object object) {
		System.out.println("LOAD" + '-' + object.toString());
	}
}
