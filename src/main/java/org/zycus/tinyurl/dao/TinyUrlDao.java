package org.zycus.tinyurl.dao;


import java.util.List;

import org.zycus.tinyurl.model.TinyUrlModel;


public interface TinyUrlDao
{

   public void saveUrl(TinyUrlModel requestModel);

   public List<TinyUrlModel> getUrl();
}
