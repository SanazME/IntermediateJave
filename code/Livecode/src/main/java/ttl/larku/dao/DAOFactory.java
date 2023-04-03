package ttl.larku.dao;

import java.util.ResourceBundle;

/**
 * @author whynot
 */
public class DAOFactory {

    public static StudentDAO studentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larku");
        String profile = bundle.getString("larku.profile");

        switch(profile) {
            case "inmem":
                return new InMemoryStudentDAO();
            case "mysql":
                return new MysqlStudentDAO();
            default:
                throw new RuntimeException("Unknown profile: " + profile);
        }

//        return new InMemoryStudentDAO();
        //return new MysqlStudentDAO();
    }
}
