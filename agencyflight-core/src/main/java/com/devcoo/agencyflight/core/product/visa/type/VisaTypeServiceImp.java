package com.devcoo.agencyflight.core.product.visa.type;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class VisaTypeServiceImp extends StdServiceImp<VisaTypeDao, VisaType> implements VisaTypeService {

}
