import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.*;
import org.junit.Test;
import lombok.extern.log4j.Log4j;

@Log4j
public class OracleConnectionTest {

    private static final String DRIVER ="oracle.jdbc.driver.OracleDriver";
    private static final String URL ="jdbc:oracle:thin:@localhost:1521:orcl";
    private static final String USER ="c##book_ex";
    private static final String PW ="book_ex";
@Test
    public void testConnect() throws Exception{

        Class.forName(DRIVER);

        try(Connection con = DriverManager.getConnection(URL, USER, PW)){

            System.out.println(con);
            System.out.println("success");
        }catch(Exception e) {
            e.printStackTrace();
            log.info("예외 발생");
        }
    }

}