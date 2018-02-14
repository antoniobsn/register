package br.com.register.controller.v1;

import br.com.register.controller.request.UserRequest;
import br.com.register.enums.Office;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by asampaio on 07/02/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserControllerTest {

    private final Long FIRST_COMPANY_ID = 1l;
    private final Long FIRST_USER_ID = 1l;

    private final String BASE_PATH = "http://localhost:8181/api/v1/users";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldCreate() throws Exception {

        UserRequest userRequest = new UserRequest(
                "72805764196",
                "UsuarioIntegrationTest1",
                "usuario_integration_test_1@gmail.com",
                Office.FINAL_USER,
                FIRST_COMPANY_ID);

        ResponseEntity<UserRequest> responseEntity = restTemplate.postForEntity(BASE_PATH, userRequest, UserRequest.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("72805764196", responseEntity.getBody().getCpf());
        assertEquals("UsuarioIntegrationTest1", responseEntity.getBody().getName());
        assertEquals("usuario_integration_test_1@gmail.com", responseEntity.getBody().getEmail());
        assertEquals("FINAL_USER", responseEntity.getBody().getOffice().getDescription().toUpperCase());
    }


    @Test
    @Sql(scripts = "/testSqlFiles/cleanTables.sql")
    public void shouldFindAll() throws Exception {

        String response = restTemplate.getForObject(BASE_PATH, String.class);

        List<UserRequest> users = objectMapper
                .readValue(response, objectMapper.getTypeFactory().constructCollectionType(List.class, UserRequest.class));

        assertEquals(HttpStatus.OK, HttpStatus.OK);
    }


    @Test
    public void shouldFindById() throws Exception {

        ResponseEntity<UserRequest> responseEntity = restTemplate.getForEntity(BASE_PATH+"/"+FIRST_USER_ID.toString(), UserRequest.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("1", responseEntity.getBody().getCompanyId().toString());
        assertEquals("52848767847", responseEntity.getBody().getCpf());
        assertEquals("Usuario1", responseEntity.getBody().getName());
        assertEquals("usuario_1@emil.com.br", responseEntity.getBody().getEmail());
        assertEquals("FINAL_USER", responseEntity.getBody().getOffice().getDescription().toUpperCase());

    }

//    @Test
//    public void update() throws Exception {
//        System.out.print("update");
//    }

//    @Test
//    @Sql("/testSqlFiles/cleanTables.sql")
//    public void delete() throws Exception {
//        ResponseEntity<UserRequest> responseEntity = restTemplate.getForEntity(BASE_PATH+"/2", UserRequest.class);
//
//        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
//        System.out.print("delete");
//    }

}