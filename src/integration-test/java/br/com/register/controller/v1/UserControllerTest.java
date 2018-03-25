package br.com.register.controller.v1;

import br.com.register.controller.request.UserRequest;
import br.com.register.enums.Office;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Sql(scripts = "classpath:sql/companies/insert-first_company.sql")
    public void shouldCreate() throws Exception {

        UserRequest userRequest = new UserRequest(
                "72805764196",
                "UsuarioIntegrationTest1",
                "usuario_integration_test_1@gmail.com",
                Office.FINAL_USER,
                FIRST_COMPANY_ID);

        mockMvc.perform(post(BASE_PATH)
                .contentType("application/json;charset=UTF8")
                .content(asJsonString(userRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("cpf").value("72805764196"))
                .andExpect(jsonPath("name").value("UsuarioIntegrationTest1"))
                .andExpect(jsonPath("email").value("usuario_integration_test_1@gmail.com"));
    }

    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void shouldDelete() throws Exception {
        mockMvc.perform(delete(BASE_PATH+"/{id}", FIRST_USER_ID.intValue())
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value("87705417014"))
                .andExpect(jsonPath("name").value("Usuario1"))
                .andExpect(jsonPath("email").value("usuario_1@emil.com.br"))
                .andExpect(jsonPath("office").value(Office.FINAL_USER.getDescription().toUpperCase()));
    }

    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void shouldUpdate() throws Exception {

        UserRequest userRequest = new UserRequest(
                "72805764196",
                "UsuarioIntegrationTest1",
                "usuario_integration_test_1@gmail.com",
                Office.FINAL_USER,
                FIRST_COMPANY_ID);

        mockMvc.perform(
                put(BASE_PATH+"/{id}", FIRST_USER_ID.intValue())
                        .contentType("application/json;charset=UTF-8")
                        .content(asJsonString(userRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("cpf").value("25506893051"))
                .andExpect(jsonPath("name").value("UsuarioIntegrationTest1Alterado"))
                .andExpect(jsonPath("email").value("usuario_integration_test_1_alterado@gmail.com"))
                .andExpect(jsonPath("office").value(Office.FINAL_USER.getDescription().toUpperCase()));
    }

    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-first_user.sql"})
    public void findById() throws Exception {
        mockMvc.perform(get(BASE_PATH+"/{1}", FIRST_USER_ID.intValue()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("cpf", is("87705417014")))
                .andExpect(jsonPath("name", is("Usuario1")))
                .andExpect(jsonPath("email", is("usuario_1@emil.com.br")))
                .andExpect(jsonPath("office" , is(Office.FINAL_USER.toString())));
    }



    @Test
    @Sql(scripts = {"classpath:sql/companies/insert-first_company.sql", "classpath:sql/users/insert-multiple_users.sql"})
    public void findAll() throws Exception {
        mockMvc.perform(get(BASE_PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[2].company_id", is(FIRST_COMPANY_ID.intValue())))
                .andExpect(jsonPath("$[2].cpf", is("57778715007")))
                .andExpect(jsonPath("$[2].name", is("Usuario2")))
                .andExpect(jsonPath("$[2].email", is("usuario_2@emil.com.br")))
                .andExpect(jsonPath("$[2].office" , is(Office.FINAL_USER.toString())))
                .andExpect(jsonPath("$[3].company_id", is(FIRST_COMPANY_ID.intValue())))
                .andExpect(jsonPath("$[3].cpf", is("78479876000")))
                .andExpect(jsonPath("$[3].name", is("Usuario3")))
                .andExpect(jsonPath("$[3].email", is("usuario_3@emil.com.br")))
                .andExpect(jsonPath("$[3].office" , is(Office.FINAL_USER.toString())));
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}