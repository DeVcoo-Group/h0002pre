package com.devcoo.agencyflight.core.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.devcoo.agencyflight.core.report.ReportWriter;
import com.devcoo.agencyflight.core.util.Tools;

public class ReportCustomerList extends ReportWriter {
	private List<Customer> listCustomer = new ArrayList<Customer>();
	
	public ReportCustomerList(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	@Override
	public HashMap<String, Object> generateData() {
		//need 
		HashMap<String,Object> report = new HashMap<String, Object>();
		List<List<String>> rows =  new ArrayList<List<String>>();
		////
		for(Customer customer : listCustomer) {
			List<String> row = new ArrayList<String>();
			row.add(customer.getId()+"");
			row.add(customer.getCode());
			row.add(customer.getFirstName());
			row.add(customer.getLastName());
			row.add(Tools.getEnumToString(customer.getNationality(), Nationality.values()));
			rows.add(row);
		}
		//need
		report.put("listofcustomers", rows);
		return report;
	}

	@Override
	public String getTemplateName() {
		// TODO Auto-generated method stub
		return "CustomerListTemplate.odt";
	}

}
