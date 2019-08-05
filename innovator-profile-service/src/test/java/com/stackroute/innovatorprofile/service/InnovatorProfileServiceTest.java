package com.stackroute.innovatorprofile.service;

import com.stackroute.innovatorprofile.domain.InnovatorProfile;
import com.stackroute.innovatorprofile.exception.EmailIdAlreadyExistsException;
import com.stackroute.innovatorprofile.repository.InnovatorProfileRespository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class InnovatorProfileServiceTest {

    private InnovatorProfile innovatorProfile;

    //Create a mock for UserRepository
    @Mock
    private InnovatorProfileRespository innovatorProfileRespository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    private InnovatorProfileServiceImpl innovatorProfileServiceimpl;
    List<InnovatorProfile> list = null;


    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        innovatorProfile = new InnovatorProfile();
        innovatorProfile.setEmailId("laxman567@gmail.com");
        innovatorProfile.setPassword("laxman*99940");
        innovatorProfile.setName("Laxman");
        innovatorProfile.setDomain("IT");
        list = new ArrayList();
        list.add(innovatorProfile);

    }
    @Test
    public void saveInnovatorProfile() throws EmailIdAlreadyExistsException {
        when(innovatorProfileRespository.save((InnovatorProfile) any())).thenReturn(innovatorProfile);
        InnovatorProfile savedinnovatorprofile = innovatorProfileServiceimpl.saveInnovatorProfile(innovatorProfile);
        Assert.assertEquals(innovatorProfile,savedinnovatorprofile);
        //verify here verifies that userRepository save method is only called once
        verify(innovatorProfileRespository,times(1)).save(innovatorProfile);
    }



    @Test(expected = EmailIdAlreadyExistsException.class)
    public void saveInnovatorProfileFailure() throws EmailIdAlreadyExistsException {
        when(innovatorProfileRespository.save((InnovatorProfile) any())).thenReturn(null);
        InnovatorProfile savedInnovatorProfile = innovatorProfileServiceimpl.saveInnovatorProfile(innovatorProfile);
        System.out.println("savedres"+savedInnovatorProfile);
        Assert.assertEquals(innovatorProfile,savedInnovatorProfile);
    }




    @Test
    public void getAllInnovatorProfile() throws Exception {
        innovatorProfileRespository.save(innovatorProfile);
        //stubbing the mock to return specific data
        when(innovatorProfileRespository.findAll()).thenReturn(list);
        List<InnovatorProfile> userlist = innovatorProfileServiceimpl.getInnovatorProfile();

        Assert.assertEquals(list,userlist);
    }

}