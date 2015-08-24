package com.devcoo.agencyflight.core.ui.field.selelct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.devcoo.agencyflight.core.std.StdField;

public class ComboBox<T extends StdField> extends com.vaadin.ui.ComboBox {

	private static final long serialVersionUID = -1442555558647789066L;
	
	private Map<Integer, T> values;
	
	public ComboBox(List<T> values) {
		this("", values);
	}
	
	public ComboBox(String caption, List<T> values) {
		super(caption);
		this.values = new HashMap<Integer, T>();
		build(values);
	}
	
	public void setValues(List<T> values) {
		clear();
		build(values);
	}
	
	private void build(List<T> values) {
		if (values != null) {
			for (T t : values) {
				addItem(t.getId());
				setItemCaption(t.getId(), t.getDisplayName());
				this.values.put(t.getId(), t);
			}
		}
	}
	
	public void clear() {
		removeAllItems();
		values.clear();
	}
	
	public T getEntity() {
		if (getValue() != null) {
			return values.get(getValue());
		}
		return null;
	}
	
	public void setEntity(T entity) {
		if (entity == null) {
			setValue(null);
		} else {
			setValue(entity.getId());
		}
	}

}
