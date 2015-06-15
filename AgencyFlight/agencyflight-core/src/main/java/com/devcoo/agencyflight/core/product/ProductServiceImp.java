package com.devcoo.agencyflight.core.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class ProductServiceImp extends StdServiceImp<ProductDao, Product> implements ProductService {

}
