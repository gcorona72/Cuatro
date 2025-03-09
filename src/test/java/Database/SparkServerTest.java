package Database;

import org.example.SparkServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import spark.Spark;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SparkServerTest {

    @BeforeAll
    public static void iniciarServidor() {
        Thread serverThread = new Thread(SparkServer::start);
        serverThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void detenerServidor() {
        Spark.stop();
    }

    @Test
    public void testServidorLevantado() {
        given().when().get("http://localhost:4567/api/guests")
                .then().statusCode(200);
    }

    @Test
    public void testCrearInvitado() {
        given().contentType("application/json")
                .body("{\"nombre\": \"Test\", \"acompanante\": true}")
                .when().post("http://localhost:4567/api/guests")
                .then().statusCode(200)
                .body("status", equalTo("ok"));
    }
}
