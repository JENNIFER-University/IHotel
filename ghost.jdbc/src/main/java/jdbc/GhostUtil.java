package jdbc;

import java.util.Random;


/**
 * Created by khalid on 9/7/16.
 */
public class GhostUtil
{
  private static ThreadLocal<Long> local = new ThreadLocal<Long>();
  
  public static void setSleep(long tm) {
      System.out.println("Set " + tm);
      local.set(new Long(tm));
  }

  private static int min_next = -1;
  private static int max_next = -1;
  private static int min_time = -1;
  private static int max_time = -1;
  
  public static void setLock(int min_next, int max_next, int min_time, int max_time)
  {
    GhostUtil.min_next = min_next;
    GhostUtil.max_next = max_next;
    GhostUtil.min_time = min_time;
    GhostUtil.max_time = max_time;
  }
  
  public static void sleep()
  {
    try
    {
      Long tm = local.get();
      if ((tm != null) && (tm.longValue() > 0L)) {
        Thread.sleep(tm.longValue());
      }else{
    	  Thread.sleep(1500);
      }
    }
    catch (Exception localException) {}
    lock();
  }
  
  private static long next_lock = System.currentTimeMillis() + max_next * 1000;
  private static Object lock = new Object();
  
  public static void lock()
  {
    if ((min_next < 0) || (max_next < 0) || (min_time < 0) || (max_time < 0)) {
      return;
    }
    synchronized (lock)
    {
      long now = System.currentTimeMillis();
      if (now < next_lock) {
        return;
      }
      try
      {
        long tm = getRandom(min_time, max_time) * 1000;
        Thread.sleep(tm);
      }
      catch (Exception localException) {}
      now = System.currentTimeMillis();
      next_lock = now + getRandom(min_next, max_next) * 1000;
    }
  }
  
  private static Random rand = new Random();
  
  public static int getRandom(int min, int max)
  {
    max++;
    int max0 = max - min;
    int v = Math.abs(rand.nextInt()) % max0;
    return v + min;
  }

  
}
