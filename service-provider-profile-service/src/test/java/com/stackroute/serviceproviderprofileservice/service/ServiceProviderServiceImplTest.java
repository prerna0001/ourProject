package com.stackroute.serviceproviderprofileservice.service;

import com.stackroute.serviceproviderprofileservice.domain.ServiceProvider;
import com.stackroute.serviceproviderprofileservice.repository.ServiceProviderRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotEmpty;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceProviderServiceImplTest {

  @Mock
  private ServiceProviderRepository serviceProviderRepository;

  //Inject the mocks as dependencies into UserServiceImpl
  @InjectMocks
  private ServiceProviderServiceImpl serviceProviderServiceimpl;
  private ServiceProvider serviceProvider;

  List<ServiceProvider> list = null;


  @Before
  public void setUp() throws Exception {

    MockitoAnnotations.initMocks(this);
     serviceProvider= new ServiceProvider();

    serviceProvider.setPassword("root123");
    serviceProvider.setEmailId("ann@gmail");
    serviceProvider.setAbout("hii");

    list = new ArrayList();
    list.add(serviceProvider);

  }
  @Test
  public void saveServiceProvider() throws  Exception{
    when(serviceProviderRepository.save((ServiceProvider) any())).thenReturn(serviceProvider);
    ServiceProvider savedserviceProvider = serviceProviderServiceimpl.saveServiceProvider(serviceProvider);
    Assert.assertEquals(serviceProvider,savedserviceProvider);
    //verify here verifies that userRepository save method is only called once
    verify(serviceProviderRepository,times(1)).save(serviceProvider);
  }




  @Test
  public void getAllInnovatorProfile() throws Exception {
    serviceProviderRepository.save(serviceProvider);
    //stubbing the mock to return specific data
    when(serviceProviderRepository.findAll()).thenReturn(list);
    List<ServiceProvider> userlist = serviceProviderServiceimpl.getServiceProvider();

    Assert.assertEquals(list,userlist);
  }

}
