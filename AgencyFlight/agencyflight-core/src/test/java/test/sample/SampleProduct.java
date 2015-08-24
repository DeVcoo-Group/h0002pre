package test.sample;

import java.util.ArrayList;
import java.util.List;

import com.devcoo.agencyflight.core.country.Country;
import com.devcoo.agencyflight.core.product.Product;
import com.devcoo.agencyflight.core.product.ProductService;
import com.devcoo.agencyflight.core.product.ProductType;
import com.devcoo.agencyflight.core.product.visa.Visa;
import com.devcoo.agencyflight.core.product.visa.period.Period;
import com.devcoo.agencyflight.core.product.visa.type.VisaType;
import com.devcoo.agencyflight.core.supplier.Supplier;

public class SampleProduct {
	
	public static List<Product> getSample() {
		List<Product> lst = new ArrayList<Product>();
		for (int i = 0; i < 10; i++) {
			Product product = new Product();
			product.setCode("Product Code" + i + 1);
			product.setName("Product Name" + i + 1);
			product.setPrice(Math.random() * 100);
			Supplier s = new Supplier();
			s.setId(1);
			product.setSupplier(s);
			product.setProductType(ProductType.PASSPORT_VISA.getId());
			
			Visa v = new Visa();
			Period p = new Period();
			p.setId(1);
			v.setPeriod(p);
			VisaType vt = new VisaType();
			vt.setId(1);
			v.setVisaType(vt);
			Country c = new Country();
			c.setId(1);
			v.setNationality(c);
			v.setFindFee(Math.random() * 10);
			v.setDelete(false);
			product.setVisa(v);
			lst.add(product);
		}
		return lst;
	}
	
	public static void createSample(ProductService service) {
		for (Product product : getSample()) {
			service.save(product);
		}
	}

}
