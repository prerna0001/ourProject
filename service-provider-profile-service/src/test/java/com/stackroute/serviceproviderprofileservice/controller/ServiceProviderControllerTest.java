package com.stackroute.serviceproviderprofileservice.controller;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.repository.ServiceProviderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ServiceProviderControllerTest {

  @Autowired
  ServiceProviderRepository serviceProviderRepository;
  private ServiceProvider serviceProvider;

  @Before
  public void setUp()
  {

    serviceProvider= new ServiceProvider();
    serviceProvider.setEmailId("gargi");
    serviceProvider.setPassword("root123");

    serviceProvider.setEmailId("ann@gmail");
    serviceProvider.setAbout("hii");

    serviceProvider.getDomain();
    serviceProvider.getSubDomain();

  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void saveServiceProvider() throws Exception {
    serviceProviderRepository.save(serviceProvider);

    ServiceProvider testUser = serviceProviderRepository.findById(serviceProvider.getEmailId()).get();
    System.out.println(testUser.getEmailId());
  }

  @Test
  public void getserviceProviders() throws Exception {
  }
}
