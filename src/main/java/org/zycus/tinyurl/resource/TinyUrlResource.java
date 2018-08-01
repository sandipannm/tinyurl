package org.zycus.tinyurl.resource;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.zycus.tinyurl.model.ShortenRequest;
import org.zycus.tinyurl.service.TinyUrlService;
import org.zycus.tinyurl.validator.URLValidator;


@RestController
public class TinyUrlResource
{

   private static final Logger LOGGER = LoggerFactory.getLogger(TinyUrlResource.class);

   @Autowired
   private TinyUrlService tinyUrlService;

   @RequestMapping(value = "/shortener", method = RequestMethod.POST, consumes =
   { "application/json" })
   public String shortenUrl(@RequestBody @Valid final ShortenRequest shortenRequest, HttpServletRequest request) throws Exception
   {
      LOGGER.info("Received url to shorten: " + shortenRequest.getUrl());
      String longUrl = shortenRequest.getUrl();
      if (URLValidator.INSTANCE.validateURL(longUrl))
      {
         String localURL = request.getRequestURL().toString();
         String shortenedUrl = tinyUrlService.shortenURL(localURL, shortenRequest.getUrl());
         LOGGER.info("Shortened url to: " + shortenedUrl);
         return shortenedUrl;
      }
      throw new Exception("Please enter a valid URL");
   }

   @RequestMapping(value = "/{id}", method = RequestMethod.GET)
   public RedirectView redirectUrl(@PathVariable String id) throws IOException, URISyntaxException, Exception
   {
      LOGGER.debug("Received shortened url to redirect: " + id);
      String redirectUrlString = tinyUrlService.getLongURLFromID(id);
      RedirectView redirectView = new RedirectView();
      redirectView.setUrl("http://" + redirectUrlString);
      return redirectView;
   }
}
