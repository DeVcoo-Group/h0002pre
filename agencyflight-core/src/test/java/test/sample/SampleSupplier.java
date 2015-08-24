package test.sample;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.supplier.Supplier;
import com.devcoo.agencyflight.core.supplier.SupplierService;

public class SampleSupplier {

	public static List<Supplier> getSample() {
		List<Supplier> lst = new ArrayList<Supplier>();
		for (int i = 0; i < 10; i++) {
			Supplier supplier = new Supplier();
			supplier.setName("Supplier" + i);
			supplier.setPhone("01234567" + i);
			supplier.setEmail(supplier.getName() + "@sample.com");
			lst.add(supplier);
		}
		return lst;
	}
	
	public static void createSample(SupplierService service) {
		for (Supplier supplier : getSample()) {
			service.save(supplier);
		}
	}
}
