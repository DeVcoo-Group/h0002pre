package com.devcoo.agencyflight.core.ui.layout.report;

import java.io.Serializable;

import com.devcoo.agencyflight.core.store.Store;

public class JRBean implements Serializable{

	private static final long serialVersionUID = -2405739742859676622L;
	private Store store;

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
}
