package edu.jennifer.ihotel.action.api;

import edu.jennifer.ihotel.util.Common;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileLock;

/**
 * Created by khalid on 07/04/2017.
 */
public class FileManager {
    
    private final String LOCK_FILE_NAME 	= "start_service_queue.tmp";
    public static final String UNLOCK_FILE_NAME 	= "end_service_queue.tmp";

    private FileOutputStream writer;
    private FileLock fileLock;

    private final byte[] fakeData = null;

    private static FileManager instance;

    public static FileManager getInstance(){
        if(instance == null)
            instance = new FileManager();
        return instance;
    }

    public void writeToFile(){
        try{
            File lockFile = new File(System.getProperty("java.io.tmpdir"),LOCK_FILE_NAME);
            if(!lockFile.exists()){
                lockFile.createNewFile();
            }
            writer = new FileOutputStream(lockFile);
            fileLock = writer.getChannel().lock();
            writer.write(fakeData);
            waitForRelease();
        }catch(Exception ex){}
    }

    public void readTheFile(){
        try{
            while(fileLock.isValid()){
                Thread.sleep(Common.getRandom(100, 1000));
            }
        }catch(Exception ex){}
    }

    private void waitForRelease(){
        try{
            File release = new File(System.getProperty("java.io.tmpdir"),UNLOCK_FILE_NAME);
            while(!release.exists()){
                Thread.sleep(2000);
            }

            fileLock.release();
            writer.close();
            Thread.sleep(2000);
            release.delete();
            new File(System.getProperty("java.io.tmpdir"),LOCK_FILE_NAME).delete();
        }catch(Exception ex){}
    }
}
