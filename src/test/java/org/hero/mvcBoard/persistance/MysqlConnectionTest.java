package org.hero.mvcBoard.persistance;

import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

@Log4j
public class MysqlConnectionTest {

    private static final String Driver = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost/HeroMVC?useSSL=false&serverTimezone=Asia/Seoul";
    private static final String USER = "root";
    private static final String PASSWORD = "920124";

    static {
            try {
                Class.forName(Driver);
                log.info("Driver Available");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void connectionTest()throws Exception{
        log.info("Database Connection Start....");
       try(Connection con = DriverManager.getConnection(URL,USER,PASSWORD)){
           log.info(con);
       }catch (Exception e){
           fail(e.getMessage());
       }

    }
}
