package com.devcoo.agencyflight.core.customer;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devcoo.agencyflight.core.std.StdServiceImp;

@Service
@Transactional
public class CustomerServiceImp extends StdServiceImp<CustomerDao, Customer> implements CustomerService {

}
