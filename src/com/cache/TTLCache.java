package com.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class TTLCache
{
	
	 static {
		    /**
		     * Scan our LRU cache every 30 minutes, remove expired keys.
		     */
		    new Thread(new Runnable() {
		      @Override
		      public void run() {
		        try {
		          while (true) {
		            for (String key : cache.keySet()) {
		              if (cache.get(key).isExpired())
		                cache.remove(key);
		            }
		            Thread.sleep(TimeUnit.MINUTES.toMillis(30));
		          }
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    }).start();
		  }
	 
	private static ConcurrentHashMap<String, Entry> cache = new ConcurrentHashMap<>();

	static class Entry
	{
		private final Object value;
		private final Long expiration;
		private Long lastUseTime;

		public Entry(Object value, Long expiration)
		{
			this.value = value;
			this.expiration = expiration;
			this.lastUseTime = System.currentTimeMillis();
		}

		public boolean isExpired()
		{
			return (lastUseTime + expiration) < System.currentTimeMillis();
		}

		public Object getValue()
		{
			return this.value;
		}

		public void updateUseTime()
		{
			this.lastUseTime = System.currentTimeMillis();
		}
	}

	/**
	 * Put a new object into cache, and speicify its expiration time. Everytime
	 * we get the object from cache, its expiration time would be updated.
	 */
	public static void put(String key, Object value, Long expiration)
	{
		cache.put(key, new Entry(value, expiration));
	}

	/**
	 * Get instance from cache, no need to be thread-safe.
	 */
	public static Object get(String key)
	{
		Entry entry = cache.get(key);
		if (!entry.isExpired())
		{
			entry.updateUseTime();
			return entry.getValue();
		}
		else
		{
			cache.remove(key);
			return null;
		}
	}
}
