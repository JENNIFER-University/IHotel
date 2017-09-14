package edu.jennifer.ihotel.problem;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Khalid Elshafie
 * @Created 9/14/17 12:47 PM.
 */
public class ProblemPool {

    private static ProblemPool instance;
    private List<Problem> problemList;

    public static ProblemPool getInstance() {
        if(instance == null) {
            synchronized (ProblemPool.class) {
                if ( instance == null) {
                    instance = new ProblemPool();
                }
            }
        }
        return instance;
    }

    private ProblemPool() {
        //Load the Problems json file
        StringBuffer buffer = new StringBuffer();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File jsonFile = new File(classLoader.getResource("problems.json").getFile());
            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));
            String data;
            while ( (data = reader.readLine()) != null) {
                buffer.append(data);
            }
            reader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        Gson gson = new Gson();
        Problem[] problems = gson.fromJson(buffer.toString(), Problem[].class);
        problemList = Arrays.asList(problems);

        //TODO: OOM
    }

    public List<Problem> getProblemList() {
        return problemList;
    }
}
