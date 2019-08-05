package com.stackroute.service;

import com.stackroute.domain.Idea;
import com.stackroute.exceptions.IdeaAlreadyExistsException;
import com.stackroute.repository.IdeaHubRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdeaHubServiceTest {

    @Autowired
    private IdeaHubRepository ideaHubRepository;
    private Idea idea;

    @Before
    public void setUp() throws Exception {
        idea=new Idea();
        idea.setIdeaId("1");
        idea.setTitle("My idea");
        idea.setDescription("this is my idea's description");
        idea.setDomain("IT");
        idea.setSubDomain("FSD");
        idea.setBudget(1000);

//        idea2=new Idea();
//        idea=new Idea();
//        idea.setIdeaId("1");
//        idea.setTitle("My idea");
//        idea.setDescription("this is my idea's description");
//        idea.setDomain("IT");
//        idea.setSubDomain("FSD");
//        idea.setBudget(1000);
    }

    @After
    public void tearDown() {
        }

    @Test
    public void addIdea() {
        ideaHubRepository.save(idea);
        Idea fetchIdea= ideaHubRepository.findById(idea.getIdeaId()).get();
        Assert.assertEquals("1", fetchIdea.getIdeaId());
    }

    @Test
    public void deleteIdeaTest() throws Exception {
        idea= new Idea();
        ideaHubRepository.save(idea);
        ideaHubRepository.delete(idea);
//        Assert.assertNull(idea);
    }

    @Test
    public void updateIdea() throws Exception {
        ideaHubRepository.save(idea);
    }

   @Test
    public void displayIdeasTest() throws Exception {
       ideaHubRepository.save(idea);
       List<Idea> ideas= ideaHubRepository.findAll();
       Assert.assertEquals("1", idea.getIdeaId());
   }


    @Test
    public void findIdeaById() throws Exception {
        ideaHubRepository.save(idea);
      ideaHubRepository.findById(idea.getIdeaId());
       Assert.assertEquals("1",idea.getIdeaId() );
    }
}