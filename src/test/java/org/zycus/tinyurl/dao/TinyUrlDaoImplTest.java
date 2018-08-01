package org.zycus.tinyurl.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.zycus.tinyurl.model.TinyUrlModel;
import org.zycus.tinyurl.repository.TinyUrlRepository;


@RunWith(MockitoJUnitRunner.class)
public class TinyUrlDaoImplTest
{

   @InjectMocks
   private TinyUrlDaoImpl tinyUrlDaoImpl;

   @Mock
   private TinyUrlRepository tinyUrlRepository;

   TinyUrlModel tinyUrlModel = null;
   TinyUrlModel responseModel = null;
   List<TinyUrlModel> tinyUrlModelList = null;

   @Before
   public void setUp() throws Exception
   {
      MockitoAnnotations.initMocks(this);
      tinyUrlModel = new TinyUrlModel("123", "www.google.com", "myTinyUrl");
      responseModel = new TinyUrlModel("456", "www.facebook.com", "myfBUrl");
      tinyUrlModelList = new ArrayList<TinyUrlModel>();
      tinyUrlModelList.add(responseModel);
   }

   @Test
   public void testSaveUrl() throws Exception
   {
      Mockito.when(tinyUrlRepository.save(Matchers.anyObject())).thenReturn(null);
      tinyUrlDaoImpl.saveUrl(tinyUrlModel);
   }

   @Test
   public void testGetUrl() throws Exception
   {
      Mockito.when(tinyUrlRepository.findAll()).thenReturn(tinyUrlModelList);
      Assert.assertNotNull(tinyUrlDaoImpl.getUrl());
   }

   @Test
   public void testGetUrlForNull() throws Exception
   {
      Mockito.when(tinyUrlRepository.findAll()).thenReturn(null);
      Assert.assertNull(tinyUrlDaoImpl.getUrl());
   }
}
