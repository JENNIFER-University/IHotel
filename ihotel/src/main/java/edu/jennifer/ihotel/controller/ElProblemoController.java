package edu.jennifer.ihotel.controller;

import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.jennifer.ihotel.dao.ReservationDAO;
import edu.jennifer.ihotel.model.Reservation;
import edu.jennifer.ihotel.util.ElproblemoHelper;


@Controller
@RequestMapping(value="/elproblemo")
public class ElProblemoController {
		
	@Autowired
	ReservationDAO reservationService;

	public static final String ONE_KB_DATA = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatad"
			+"atadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadata"
			+"datadatadatadatadatadatadata";
	
	/**
	 * Helper function used by ElProblemo Application to check connection health between the app and iHotel
	 * @return Json Response saying I am alive
	 */
	@RequestMapping(value="/server_status.do")
	public @ResponseBody String checkServerStatus(){
		JSONObject object = new JSONObject();
		try {
			object.put("status", "alive");
		} catch (JSONException e) {
		}
		return object.toString();
	}
	
	
	/**
	 * Fake Download File and cause high CPU  
	 * @param request duration how long to stress the cpu in MS
	 */
	@RequestMapping(value="/download.do",method=RequestMethod.GET)
	public @ResponseBody String downloadFile(HttpServletRequest request)
	{
		final long duration = Long.parseLong(request.getParameter("duration"));		
		try{
			int threads = 2;
			while(threads != 0){
				new Thread(new Runnable(){
					
					public void run(){
						System.out.println(getThreadNameAndTime() + " : Started to stress CPU");
						long now = System.currentTimeMillis();
						long start = now;
						long end = start + duration;
						String data = ONE_KB_DATA;
						while(now < end){
							now = System.currentTimeMillis();
							data += ONE_KB_DATA;
							Math.atan(Math.sqrt(Math.pow(100, 10)));
						}
						
						end = System.currentTimeMillis() - start;
						System.out.println(getThreadNameAndTime() + " : Stressed CPU for " + end + " ms Data Size: " + data.length());
					}					
				}).start();;
				threads--;
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "ok";
		
	}

	
	/**
	 * Fake Reading File into ArrayList Buffer and causes OOM (Java Heap Space)  
	 */
	@RequestMapping(value="/read.do",method=RequestMethod.GET)
	public @ResponseBody String readFile(HttpServletRequest request)throws OutOfMemoryError{
		long start = System.currentTimeMillis();
		byte[] data = new byte[2^20];
		ArrayList<byte[]> buffer = new ArrayList<byte[]>();
		try{
			while(true){
				buffer.add(data);
			}
		}catch(OutOfMemoryError t){
			long end = System.currentTimeMillis() - start;
			System.out.println(t.getMessage() + " in " + end + " ms");
			throw t;
		}
	}
	
	/**
	 * Insert a record to the database with a primary key that already exists, 
	 * and causes SQLException (Duplicate Key)  
	 * Problem: JENNIFER does not show this, not sure if its because Spring Wrap the exception or no
	 */
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	public @ResponseBody String updateDB(HttpServletRequest request)throws SQLException{
		long start = System.currentTimeMillis();
		Reservation rservation = reservationService.getReservation();
		try{
			reservationService.insert(rservation);
		}catch(SQLException sql){
			long end = System.currentTimeMillis() - start;
			System.out.println(sql.getMessage() + " in " + end + " ms");
			throw sql;
		}
		return "ok";
	}
	
	
	/**
	 * Controller Active Service (Lock or unlock the file)
	 */
	@RequestMapping(value="/qu.do",method=RequestMethod.GET)
	public @ResponseBody String activeServiceController(HttpServletRequest req){
		String cmd = req.getParameter("cmd");
		if(cmd == null || cmd.length() == 0)
			cmd = "";
		if(cmd.equals("1")){//Lock the file
			ElproblemoHelper.getInstance().writeToFile();
		}else if(cmd.equals("2")){//Release
			try{
				new File(System.getProperty("java.io.tmpdir"),ElproblemoHelper.UNLOCK_FILE_NAME).createNewFile();
			}catch(IOException ex){
				System.out.println("OOOOPS, failed to create release signal, please create it manually using command [touch end_service_queue.tmp] in tomcat tmp dir");
			}
		}
		
		return "ok";
	}
	
	
	/**
	 * Read the file if it is not locked or wait for lock to be relaesed
	 */
	@RequestMapping(value="/file.do",method=RequestMethod.GET)
	public @ResponseBody String readFileIfCan(){
		ElproblemoHelper.getInstance().readTheFile();
		return "ok";
	}
	
	/**
	 * Simulate DeadLock
	 * @throws InterruptedException
	 */
	@RequestMapping(value="/sync.do",method=RequestMethod.GET)
	public @ResponseBody String deadLock(HttpServletRequest req)throws InterruptedException{
		String threadName;
        try{
            threadName = req.getParameter("name");
        }catch(NullPointerException ex){
            threadName = "DeadLockThread";
        }
        boolean shouldWait;
        try{
            shouldWait = Boolean.valueOf(req.getParameter("wait"));
        }catch(Exception ex){
            shouldWait = false;
        }
        
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = new Object();
        
        
        Thread synchronizeThread1 = new Thread(new SynchronizeThread(obj1, obj2), threadName+"1");
        Thread synchronizeThread2 = new Thread(new SynchronizeThread(obj2, obj3), threadName+"2");
        Thread synchronizeThread3 = new Thread(new SynchronizeThread(obj3, obj1), threadName+"3");
 
        synchronizeThread1.start();
        Thread.sleep(3000);
        synchronizeThread2.start();
        Thread.sleep(3000);
        synchronizeThread3.start();
        
        if(shouldWait){
        	try{
        		while(synchronizeThread1.getState() == State.BLOCKED)
        			Thread.sleep(3000);
        	}catch(InterruptedException ex){}
        }
        
		return "ok";
	}
	
	
	/**
	 * Helper function to get the current thread name and current time as string
	 * @return Time and Thread name Format DATE : THREAD_NAME
	 */
	private String getThreadNameAndTime(){
		return new Date().toString() + ": " + Thread.currentThread().getName();
	}
	
	
}


class SynchronizeThread implements Runnable {
    private Object obj1;
    private Object obj2;
 
    public SynchronizeThread(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
 
    
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (obj1) {
            System.out.println(name + " acquired lock on Object1: " + obj1);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
 
            synchronized (obj2) {
                System.out.println(name + " acquired lock on Object2: " + obj2);
            }
            System.out.println(name + " released lock on Object2: " + obj2);
        }
        System.out.println(name + " released lock on Object1: " + obj1);
        System.out.println(name + " Finished Crunchify Deadlock Test.");
    }
}
