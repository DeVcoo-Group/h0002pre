package com.devcoo.agencyflight.core.supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class SupplierServiceImp extends StdServiceImp<SupplierDao, Supplier> implements SupplierService {

}
