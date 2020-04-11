package cn.dbdj1201.jpa;

import cn.dbdj1201.jpa.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tyz1201
 * @datetime 2020-04-11 14:22
 **/
public class JdbcDemo1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///db_jpa?useSSL=false&useUnicode=true&characterEncoding" +
                        "=UTF8&serverTimezone=GMT&allowPublicKeyRetrieval=true"
                , "root", "root");

        PreparedStatement pstm = connection.prepareStatement("select * from tb_user");
        ResultSet resultSet = pstm.executeQuery();
//        User user = new User();
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setAge(resultSet.getString(3));
            System.out.println("temp user -> " + user);
            users.add(user);
        }

        System.out.println("users: " + users);
        users.forEach(System.out::println);

    }
}
