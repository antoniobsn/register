package br.com.register.controller.v1;

import br.com.register.controller.request.UserRequest;
import br.com.register.enums.Office;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by asampaio on 07/02/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Transactional
public class UserControllerTest {

    private final Long FIRST_COMPANY_ID = 1l;
    private final Long FIRST_USER_ID = 1l;

    private final String BASE_PATH = "http://localhost:8181/api/v1/users";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql(scripts = "classpath:sql/companies/insert-first_company.sql")
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
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-multiple_users.sql"})
    public void shouldFindAll() throws Exception {

        String response = restTemplate.getForObject(BASE_PATH, String.class);

        List<UserRequest> users = objectMapper
                .readValue(response,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, UserRequest.class));

        assertEquals(HttpStatus.OK, HttpStatus.OK);
        assertEquals(2, users.size());

        assertEquals("1", users.get(0).getCompanyId().toString());
        assertEquals("57778715007", users.get(0).getCpf());
        assertEquals("Usuario2", users.get(0).getName());
        assertEquals("usuario_2@emil.com.br", users.get(0).getEmail());
        assertEquals("FINAL_USER", users.get(0).getOffice().getDescription().toUpperCase());

        assertEquals("1", users.get(1).getCompanyId().toString());
        assertEquals("78479876000", users.get(1).getCpf());
        assertEquals("Usuario3", users.get(1).getName());
        assertEquals("usuario_3@emil.com.br", users.get(1).getEmail());
        assertEquals("FINAL_USER", users.get(1).getOffice().getDescription().toUpperCase());
    }


    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void shouldFindById() throws Exception {

        ResponseEntity<UserRequest> responseEntity = restTemplate.getForEntity(BASE_PATH + "/" + FIRST_USER_ID.toString(), UserRequest.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("1", responseEntity.getBody().getCompanyId().toString());
        assertEquals("87705417014", responseEntity.getBody().getCpf());
        assertEquals("Usuario1", responseEntity.getBody().getName());
        assertEquals("usuario_1@emil.com.br", responseEntity.getBody().getEmail());
        assertEquals("FINAL_USER", responseEntity.getBody().getOffice().getDescription().toUpperCase());

    }

    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void update() throws Exception {

        UserRequest userRequest = new UserRequest(
                "25506893051",
                "UsuarioIntegrationTest1Alterado",
                "usuario_integration_test_1_alterado@gmail.com",
                Office.FINAL_USER,
                FIRST_COMPANY_ID);
        HttpEntity<UserRequest> entity = new HttpEntity<>(userRequest);

        ResponseEntity<UserRequest> responseEntity = restTemplate.exchange(BASE_PATH + "/" + FIRST_USER_ID.toString(), HttpMethod.PUT, entity, UserRequest.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("25506893051", responseEntity.getBody().getCpf());
        assertEquals("UsuarioIntegrationTest1Alterado", responseEntity.getBody().getName());
        assertEquals("usuario_integration_test_1_alterado@gmail.com", responseEntity.getBody().getEmail());
        assertEquals("FINAL_USER", responseEntity.getBody().getOffice().getDescription().toUpperCase());
    }


    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void delete() throws Exception {
        ResponseEntity<UserRequest> responseEntity = restTemplate.exchange(BASE_PATH + "/" + FIRST_USER_ID.toString(), HttpMethod.DELETE, null,UserRequest.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("1", responseEntity.getBody().getCompanyId().toString());
        assertEquals("87705417014", responseEntity.getBody().getCpf());
        assertEquals("Usuario1", responseEntity.getBody().getName());
        assertEquals("usuario_1@emil.com.br", responseEntity.getBody().getEmail());
        assertEquals("FINAL_USER", responseEntity.getBody().getOffice().getDescription().toUpperCase());

    }

}