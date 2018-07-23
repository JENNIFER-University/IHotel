package edu.jennifer.stress.factory;

import edu.jennifer.stress.model.User;
import edu.jennifer.stress.util.AppUtil;

import java.util.Random;

/**
 * @author Khalid
 * @Created 7/23/18 9:30 AM.
 */
public class UserFactory {

    private static final String[] NAMES_SEED   = {"khlaid","Sami","Elma","Lucy","Messi","David","Chris","Anderson","Jessica","Chappel","Mike"};
    private static final String[] EMAIL_SEED   = {"jennifersoft.com","example.com","ihotel.edu","email.com"};
    private static final String[] USERNAME_SEED = {"admin","sami","elma", "david", "sally", "julia", "tom12", "user9", "user10", "user11", "user12", "user13"};


    private static final Random RANDOM = new Random();

    public static User createUser() {
        User user = new User();

        user.setFirstName(NAMES_SEED[RANDOM.nextInt(NAMES_SEED.length)]);
        user.setLastName(NAMES_SEED[RANDOM.nextInt(NAMES_SEED.length)]);
        user.setEmail(String.format("%s@%s", user.getFirstName(), EMAIL_SEED[RANDOM.nextInt(EMAIL_SEED.length)]));
        user.setUsername(USERNAME_SEED[RANDOM.nextInt(USERNAME_SEED.length)]);
        user.setLocation(String.format("%d.%d.%d.%d", AppUtil.getRandom(0, 255), AppUtil.getRandom(0, 255),AppUtil.getRandom(0, 255),AppUtil.getRandom(0, 255)));
        return user;
    }
}
