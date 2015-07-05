package com.devcoo.agencyflight.core.product.visa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class VisaServiceImp extends StdServiceImp<VisaDao, Visa> implements VisaService {

}
