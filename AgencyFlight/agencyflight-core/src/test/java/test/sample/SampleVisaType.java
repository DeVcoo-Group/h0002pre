package test.sample;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.product.visa.type.VisaTypeService;

public class SampleVisaType {
	
	public static List<VisaType> getSample() {
		String[] visaType = {"Travel", "Business", "Resident"};
		
		List<VisaType> lst = new ArrayList<VisaType>();
		for (String string : visaType) {
			VisaType type = new VisaType();
			type.setCode(string);
			type.setDescription(string);
			lst.add(type);
		}
		return lst;
	}
	
	public static void createSample(VisaTypeService service) {
		for (VisaType type : getSample()) {
			service.save(type);
		}
	}

}
