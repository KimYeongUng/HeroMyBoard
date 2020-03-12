package org.hero.mvcBoard.persistance;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;


/**
 * Test Connection
 * Mysql JDBC Driver
 * Hikari CP Connection Pool
 * Mybatis
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class DataSourceTest {

    @Setter(onMethod_ ={@Autowired} )
    private DataSource dataSource;

    @Setter(onMethod_ = {@Autowired})
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testConnection(){
        try(Connection conn = dataSource.getConnection()){
            log.info(conn);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testMybatis(){
        try(SqlSession session = sqlSessionFactory.openSession(); Connection con = session.getConnection();){
            log.info(session);
            log.info(con);
        }catch (Exception e){
            fail(e.getMessage());
        }
    }


}