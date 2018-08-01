package edu.jennifer.hotel.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Random;

/**
 * @author Khalid
 * @Created 7/26/18 10:56 AM.
 */
public abstract class BaseDao {

    public JdbcTemplate jdbcTemplate;

    public BaseDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public float mySqlDelay() {
        return getRandom(1, 2);
    }

    public static float getRandom(float low,float high){
        high++;
        float max = high - low;
        float value = Math.abs(new Random().nextFloat()) % max;
        return value + low;
    }
}
