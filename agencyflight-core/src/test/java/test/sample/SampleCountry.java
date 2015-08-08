package test.sample;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.country.CountryService;

public class SampleCountry {
	
	public static List<Country> getSample() {
		String[][] countries = {
				{"Cambodia", "KH", "KHM"},
				{"Thailand", "TH", "THA"},
				{"Lao", "LA", "LAO"},
				{"China", "CN", "CHN"},
				{"Viet Nam", "VN", "VNM"}
		};
		
		List<Country> lst = new ArrayList<Country>();
		for (int i = 0; i < countries.length; i++) {
			Country country = new Country();
			country.setName(countries[i][0]);
			country.setCode2(countries[i][1]);
			country.setCode3(countries[i][2]);
			lst.add(country);
		}
		return lst;
	}
	
	public static void createSample(CountryService service) {
		for (Country country : getSample()) {
			service.save(country);
		}
	}

}
