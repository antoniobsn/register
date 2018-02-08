package br.com.register.controller.v1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.comparesEqualTo;

import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by asampaio on 07/02/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    @LocalServerPort
    int port;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

    @Test
    public void findById() throws Exception {
        given()
                .port(port)
                .param("id", 1l)
                .when()
                .get("api/v1/users")
                .then()
                .statusCode(200)
                .body("cpf", comparesEqualTo("02663512439")) //02663512439
                .body("name", equalTo("USUARIO"))
                .body("email", equalTo("usuario@emil.com.br"))
                .body("office", equalTo("FINAL_USER"))
                .body("company_id", equalTo("1"));
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

}