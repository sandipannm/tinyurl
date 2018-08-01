package org.zycus.tinyurl.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.zycus.tinyurl.converter.IDConverter;
import org.zycus.tinyurl.dao.TinyUrlDao;
import org.zycus.tinyurl.model.TinyUrlModel;


@Service
public class TinyUrlServiceImpl implements TinyUrlService
{

   @Autowired
   private TinyUrlDao tinyUrlDao;

   private static final Logger LOGGER = LoggerFactory.getLogger(TinyUrlServiceImpl.class);

   @Override
   public String shortenURL(String localURL, String longUrl)
   {
      boolean isShortUrlPresent = false;
      String shortenedURL = null;
      LOGGER.info("Shortening {}", longUrl);
      //Check if already exist
      List<TinyUrlModel> savedModels = getSavedUrls();
      if (!CollectionUtils.isEmpty(savedModels))
      {
         for (TinyUrlModel savedModel : savedModels)
         {
            if (savedModel.getRequestUrl().equals(longUrl))
            {
               isShortUrlPresent = true;
               shortenedURL = savedModel.getShortUrl();
            }
         }
      }
      //Create New Short URL
      if (!isShortUrlPresent)
      {
         final Random rnd = new Random();
         Long id = Long.valueOf(rnd.nextInt(Integer.MAX_VALUE));
         String uniqueID = IDConverter.INSTANCE.createUniqueID(id);
         String baseString = formatLocalURLFromShortener(localURL);
         shortenedURL = baseString + uniqueID;
         TinyUrlModel requestModel = new TinyUrlModel(String.valueOf(id), longUrl, shortenedURL);
         tinyUrlDao.saveUrl(requestModel);
      }
      return shortenedURL;
   }

   @Override
   public String getLongURLFromID(String uniqueID) throws Exception
   {
      String longUrl = null;
      Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
      List<TinyUrlModel> savedModels = getSavedUrls();
      if (!CollectionUtils.isEmpty(savedModels))
      {
         for (TinyUrlModel savedModel : savedModels)
         {
            if (savedModel.getUrlIdentifier().equals(String.valueOf(dictionaryKey)))
            {
               longUrl = savedModel.getRequestUrl();
            }
         }
      }
      if (StringUtils.isEmpty(longUrl))
      {
         throw new Exception("This is not a valid Short URL.");
      }
      LOGGER.info("Converting shortened URL back to {}", longUrl);
      return longUrl;
   }

   private String formatLocalURLFromShortener(String localURL)
   {
      String[] addressComponents = localURL.split("/");
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < addressComponents.length - 1; ++i)
      {
         sb.append(addressComponents[i]);
      }
      sb.append('/');
      return sb.toString();
   }

   private List<TinyUrlModel> getSavedUrls()
   {
      return tinyUrlDao.getUrl();
   }
}
