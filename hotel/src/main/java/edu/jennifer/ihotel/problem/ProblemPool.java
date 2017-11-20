package edu.jennifer.ihotel.problem;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Khalid Elshafie
 * @Created 9/14/17 12:47 PM.
 */
public class ProblemPool {

    public static final int HIGH_CPU 			= 1;
    public static final int SQL_EXCEPTION 		= 2;
    public static final int EX_CALL_EXCEPTION 	= 3;
    public static final int SLOW_LOGIN			= 4;
    public static final int SERVICE_QUEUE	 	= 5;
    public static final int DEAD_LOCK 			= 6;


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

    private Problem get(int id) {
        for(Problem problem : problemList) {
            if (problem.getId() == id){
                return  problem;
            }
        }
        return null;
    }

    /**
     * Check if problem is enabled and last executed is less than interval
     * @param id Problem ID
     * @return true or false
     */
    public boolean makeProblem(int id){
        Problem problem = get(id);
        return problem.isEnabled();
    }

    /**
     * Toggle a problem (Enable/Disable)
     * @param id Problem ID
     */
    public void toggleProblem(int id) {
        for(Problem problem: problemList) {
            if (problem.getId() == id) {
                problem.setEnabled(!problem.isEnabled());
                break;
            }
        }
    }

    /**
     * Get Problem List (For Config)
     * @return
     */
    public List<Problem> getProblemList() {
        return problemList;
    }
}
