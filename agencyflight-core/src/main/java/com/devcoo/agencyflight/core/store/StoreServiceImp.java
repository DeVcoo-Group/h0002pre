package com.devcoo.agencyflight.core.store;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class StoreServiceImp extends StdServiceImp<StoreDao, Store> implements StoreService {
}
