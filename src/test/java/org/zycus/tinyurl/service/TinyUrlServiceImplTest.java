package org.zycus.tinyurl.service;

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
import org.zycus.tinyurl.dao.TinyUrlDao;
import org.zycus.tinyurl.model.TinyUrlModel;


@RunWith(MockitoJUnitRunner.class)
public class TinyUrlServiceImplTest
{

   @Mock
   private TinyUrlDao tinyUrlDao;

   @InjectMocks
   private TinyUrlServiceImpl tinyUrlServiceImpl;
   
   TinyUrlModel tinyUrlModel = null;
   TinyUrlModel responseModel = null;
   List<TinyUrlModel> tinyUrlModelList = null;

   @Before
   public void setUp() throws Exception
   {
      MockitoAnnotations.initMocks(this);
      
      tinyUrlModel = new TinyUrlModel("123", "www.google.com", "myTinyUrl");
      responseModel = new TinyUrlModel("218856", "www.facebook.com", "myfBUrl");
      tinyUrlModelList = new ArrayList<TinyUrlModel>();
      tinyUrlModelList.add(responseModel);
   }

   @Test
   public void testShortenURL() throws Exception
   {
      Mockito.doNothing().when(tinyUrlDao).saveUrl(Matchers.anyObject());
      Assert.assertNotNull(tinyUrlServiceImpl.shortenURL("test", "www.google.com"));
   }

   @Test
   public void testGetLongURLFromID() throws Exception
   {
      Mockito.when(tinyUrlDao.getUrl()).thenReturn(tinyUrlModelList);
      Assert.assertNotNull(tinyUrlServiceImpl.getLongURLFromID("456"));
   }
}
