package com.devcoo.agencyflight.core.country;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class CountryServiceImp extends StdServiceImp<CountryDao, Country> implements CountryService {

}
