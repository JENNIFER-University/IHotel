package edu.jennifer.ihotel.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.channels.FileLock;
import java.util.Date;


public class LoadUtil {

	private final long DEFAULT_OOM_INTERVAL 		= 1 * 1000 * 60 * 60;	
	private final long DEFAULT_SQ_INTERVAL 			= 1 * 1000 * 60 * 30;
	private final long DEFAULT_SQ_DURATION 			= 1 * 1000 * 60;	
	private final long DEFAULT_DL_INRERVAL 			= 1 * 1000 * 60 * 20;
	private final long DEFAULT_TL_DURATION 			= 1 * 1000 * 60;
	private final long DEFAULT_CPU_LOAD_INTERVAL	= 1 * 1000 * 60;
	private final long DEFAULT_CPU_LOAD_DURATION	= 60000;
	
	
	private FileOutputStream sqWriter;
	private FileLock sqLock;
	
	private static LoadUtil instance;

	
	private final static String ONE_KB_DATA = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatad"
			+"atadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadata";
	
	public static LoadUtil getInstance() {
		if(instance == null){
			instance = new LoadUtil();
		}
		return instance;
	}
	

	public void makeOOM() throws Throwable{
		if(!lastOOMInterval())
			return;
		long start = System.currentTimeMillis();
		int value = 20;
		try{
			for(int i = 1; i < 20; i++){
				int loop = 2;
				int[] memoryFill = new int[value];
				do{
					memoryFill[loop] = 0;
					loop--;
				}while(loop > 0);
				
				value = value * 5;
				Thread.sleep(1000);
			}
		}catch (Throwable t) {
			if(t instanceof OutOfMemoryError){
				long end = System.currentTimeMillis() - start;
				System.out.println("Success, OOM in " + end + "ms" );
				throw t;
			}
		}
	}
	
	private boolean lastOOMInterval(){
		try{
			File oomTimer = new File(System.getProperty("java.io.tmpdir"), "oom_signal.txt");
			if(oomTimer.exists()){
				long lastModify = System.currentTimeMillis() - oomTimer.lastModified();
				if(lastModify > DEFAULT_OOM_INTERVAL){
					FileWriter f = new FileWriter(oomTimer);
					f.write(".");
					f.close();
					return true;
				}
				return false;
			}else{
				oomTimer.createNewFile();
				return false; //No OOM;
			}
		}catch(Exception ex){
			return false;
		}
	}
		
	public void makeServiceQueue(){
		try{
			File sqTimer = new File(System.getProperty("java.io.tmpdir"), "sq.txt");
			if(sqTimer.exists()){
				long lastModify = System.currentTimeMillis() - sqTimer.lastModified();
				if(lastModify > DEFAULT_SQ_INTERVAL){
					FileWriter f = new FileWriter(sqTimer);
					f.write(".");
					f.close();
					writeTotheFile();
				}
				
			}else{
				sqTimer.createNewFile();
				return;
			}
		}catch(Exception ex){}
	}
	
	private void writeTotheFile(){
		try{
			sqWriter = new FileOutputStream("ihotel.tmp");
			sqLock   = sqWriter.getChannel().lock();
			long now = System.currentTimeMillis();
			long start = now;
			long end = start + DEFAULT_SQ_DURATION;
			while(now < end){
				Thread.sleep(Common.getRandom(100, 1000));
				now = System.currentTimeMillis();
			}
			
			end = System.currentTimeMillis() - start;
			sqLock.release();
			sqWriter.close();
			System.out.println("Locked the file for " + end + " ms");
		}catch(Exception ex){}
	}
		
	public void readThefile(){
		try{
			while(sqLock.isValid()){
				Thread.sleep(Common.getRandom(100, 1000));
			}
		}catch(Exception ex){}
	}
		
	public void makeThreadLock(){
		if(!lastThreadLock())
			return;
		Object lock = new Object();
		Thread t1 = new Thread(new LockThread(lock), "T1");
		Thread t2 = new Thread(new LockThread(lock), "T2");
		t1.start();
		t2.start();
		
		try{Thread.sleep(10000);}catch(InterruptedException ex){}
		synchronized (lock) {
			lock.notifyAll();
		}
	}
	
	private boolean lastThreadLock(){
		try{
			File oomTimer = new File(System.getProperty("java.io.tmpdir"), "lock_signal.txt");
			if(oomTimer.exists()){
				long lastModify = System.currentTimeMillis() - oomTimer.lastModified();
				if(lastModify > DEFAULT_DL_INRERVAL){
					FileWriter f = new FileWriter(oomTimer);
					f.write(".");
					f.close();
					return true;
				}
				return false;
			}else{
				oomTimer.createNewFile();
				return false; 
			}
		}catch(Exception ex){
			return false;
		}
	}
	
	private class LockThread implements Runnable{
		private Object lock;
		public LockThread(Object lock){
			this.lock = lock;
		}

		public void run() {
			try{
				synchronized (lock) {
					System.out.println(getThreadNameAndTime() + " lock  wa keda");
					Thread.sleep(1000);
					
					System.out.println(getThreadNameAndTime() + " waiting .... ");
					lock.wait();
					
					Thread.sleep(DEFAULT_TL_DURATION);
					System.out.println(getThreadNameAndTime() + " awake");
					
				}
			}catch(InterruptedException ex){}
		}
	}
	
	private String getThreadNameAndTime(){
		return new Date().toString() + ": " + Thread.currentThread().getName();
	}
	
	
	public void makeHighCPU(){
		if(!lastCPUInterval())
			return;
		try{
			int threads = 3;
			while(threads != 0){
				new Thread(new Runnable() {
					public void run() {
						System.out.println(getThreadNameAndTime() + " Started");
						double val = 10;
						for(;;)
							Math.atan(Math.sqrt(Math.pow(val, 10)));
					}
				}).start();
				threads --;
			}
		}catch (Exception ex) {
		}
	}
	
	public void testCPU2(){
		try{
			int threads = 2;
			while(threads != 0){
				new Thread(new Runnable(){
					public void run(){
						System.out.println(getThreadNameAndTime() + " : Started to stress CPU");
						long now = System.currentTimeMillis();
						long start = now;
						long end = start + DEFAULT_CPU_LOAD_DURATION;
						String data = ONE_KB_DATA;
						while(now < end){
							now = System.currentTimeMillis();
							data += ONE_KB_DATA;
							Math.atan(Math.sqrt(Math.pow(100, 10)));
						}
						
						end = System.currentTimeMillis() - start;
						System.out.println(getThreadNameAndTime() + " : Stressed CPU for " + end + " ms");
					}					
				}).start();;
				threads--;
			}
			
		}catch(Exception ex){}
		
		
	}
	
	private boolean lastCPUInterval(){
		try{
			File cpuTimer = new File(System.getProperty("java.io.tmpdir"), "cput_signal.txt");
			if(cpuTimer.exists()){
				long lastModify = System.currentTimeMillis() - cpuTimer.lastModified();
				if(lastModify > DEFAULT_CPU_LOAD_INTERVAL){
					FileWriter f = new FileWriter(cpuTimer);
					f.write(".");
					f.close();
					return true;
				}
				return false;
			}else{
				cpuTimer.createNewFile();
				return false;
			}
		}catch(Exception ex){
			return false;
		}
	}

}
