package test.sample;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.period.PeriodService;

public class SamplePeriod {
	
	public static List<Period> getSample() {
		
		List<Period> lst = new ArrayList<Period>();
		for (int i = 0; i < 10; i++) {
			Period period = new Period();
			period.setDay(i + "-" + (i + 1));
			lst.add(period);
		}
		return lst;
	}
	
	public static void createSample(PeriodService service) {
		for (Period period : getSample()) {
			service.save(period);
		}
	}

}
