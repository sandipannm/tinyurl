package org.zycus.tinyurl.service;


public interface TinyUrlService {

	public String shortenURL(String localURL, String longUrl);
	public String getLongURLFromID(String uniqueID) throws Exception;
}
