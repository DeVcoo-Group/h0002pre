package com.devcoo.agencyflight.core.product.visa.period;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class PeriodServiceImp extends StdServiceImp<PeriodDao, Period> implements PeriodService {

}
