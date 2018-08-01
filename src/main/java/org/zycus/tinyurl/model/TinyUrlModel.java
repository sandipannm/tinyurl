package org.zycus.tinyurl.model;

import org.springframework.data.annotation.Id;


public class TinyUrlModel
{

   public TinyUrlModel(String urlIdentifier, String requestUrl, String shortUrl)
   {
      this.urlIdentifier = urlIdentifier;
      this.requestUrl = requestUrl;
      this.shortUrl = shortUrl;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getRequestUrl()
   {
      return requestUrl;
   }

   public void setRequestUrl(String requestUrl)
   {
      this.requestUrl = requestUrl;
   }

   public String getShortUrl()
   {
      return shortUrl;
   }

   public void setShortUrl(String shortUrl)
   {
      this.shortUrl = shortUrl;
   }

   public String getUrlIdentifier()
   {
      return urlIdentifier;
   }

   public void setUrlIdentifier(String urlIdentifier)
   {
      this.urlIdentifier = urlIdentifier;
   }

   @Id
   Long id;
   String urlIdentifier;
   String requestUrl;
   String shortUrl;
}