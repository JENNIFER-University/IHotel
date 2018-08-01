package edu.jennifer.hotel.dao;

import edu.jennifer.common.AppUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Random;

/**
 * @author Khalid
 * @Created 7/26/18 10:56 AM.
 */
public abstract class BaseDao {

    private final int SLOW_SQL_VALUE  = 100; //Every N Query
    private static long sqlCount = 0;


    public JdbcTemplate jdbcTemplate;

    public BaseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public float mySqlDelay() {
        if (sqlCount++ > SLOW_SQL_VALUE) {
            sqlCount = 0;
            return (getRandom(30, 50) / 10 );

        }
        return (getRandom(1, 2) / 1000);
    }

    public static float getRandom(float low,float high){
        high++;
        float max = high - low;
        float value = Math.abs(new Random().nextFloat()) % max;
        return value + low;
    }
}
