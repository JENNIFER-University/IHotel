package edu.jennifer.ihotel.startup;

import edu.jennifer.ihotel.model.User;
import edu.jennifer.ihotel.util.Common;
import edu.jennifer.ihotel.util.ConnectionUtil;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 3:21 PM.
 */
public class UserMaker {

    private List<User> users = new ArrayList<>();

    private  final String password = Common.plainToMD5("admin");

    public UserMaker() {
        User user = new User();
        user.setId(1 + "");
        user.setRealName("Khalid Saeed");
        user.setUsername("khalid");
        user.setPassword(password);
        users.add(user);

        user = new User();
        user.setId(2 + "");
        user.setRealName("Admin User");
        user.setUsername("admin");
        user.setPassword(password);
        users.add(user);

        for(int i = 3; i <= 13; i++) {
            user = new User();
            user.setId(i + "");
            user.setRealName("User " + i);
            user.setUsername("user"+1);
            user.setPassword(password);
            users.add(user);
        }
    }


    public void createUsers() {
        cleanUpOldUsers();

        generateNewsUsers();
    }

    private void generateNewsUsers() {
        try{
            String query = "INSERT INTO users (id, username, password, realname) values (?,?,?,?)";
            PreparedStatement pst = ConnectionUtil.getInstance().getDataSource().getConnection().prepareStatement(query);

            for(User user: users ) {
                pst.setString(1 , user.getId());
                pst.setString(2 , user.getUsername());
                pst.setString(3 , user.getPassword() );
                pst.setString(4 , user.getRealName());
                pst.addBatch();
            }

            pst.executeBatch();

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void cleanUpOldUsers() {

        try{
            Statement stm = ConnectionUtil.getInstance().getDataSource().getConnection().createStatement();
            String query = "DELETE FROM users";
            stm.executeUpdate(query);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
