package org.zycus.tinyurl.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.zycus.tinyurl.model.TinyUrlModel;
import org.zycus.tinyurl.repository.TinyUrlRepository;

@Repository
public class TinyUrlDaoImpl implements TinyUrlDao
{

   @Autowired
   private TinyUrlRepository tinyUrlRepository;

   private static final Logger LOGGER = LoggerFactory.getLogger(TinyUrlDaoImpl.class);

   @Override
   public void saveUrl(TinyUrlModel requestModel)
   {
      LOGGER.info("Saving Short Url for : {}", requestModel.getRequestUrl());
      tinyUrlRepository.save(requestModel);
   }

   @Override
   public List<TinyUrlModel> getUrl()
   {
      LOGGER.info("Retrieving all urls");
      return tinyUrlRepository.findAll();
   }
}