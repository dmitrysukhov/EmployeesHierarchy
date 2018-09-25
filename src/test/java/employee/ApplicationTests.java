package employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import employee.repositories.EmployeePersonRepository;
import employee.repositories.PositionRepository;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private EmployeePersonRepository personRepository;
	
	@Autowired
	private PositionRepository positionRepository;

	@Before
	public void deleteAllBeforeTests() throws Exception {
		personRepository.deleteAll();
		positionRepository.deleteAll();
	}

	@Test
	public void shouldCreateEntity() throws Exception {
		mockMvc.perform(post("/employee").content(
				"{\"firstName\": \"Luke\", "
				+ "\"lastName\":\"Skywalker\", "
				+ "\"contactNumber\":\"+12345678291\", "
				+ "\"email\":\"lukeskywalker@starwars.com\"}")).andExpect(
						status().isCreated()).andExpect(
								header().string("Location", containsString("employee/")));
	}

	@Test
	public void shouldRetrieveEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
				"{\"firstName\": \"Luke\", "
				+ "\"lastName\":\"Skywalker\", "
				+ "\"contactNumber\":\"+12345678291\", "
				+ "\"email\":\"lukeskywalker@starwars.com\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.firstName").value("Luke")).andExpect(
						jsonPath("$.lastName").value("Skywalker")).andExpect(
								jsonPath("$.contactNumber").value("+12345678291")).andExpect(
										jsonPath("$.email").value("lukeskywalker@starwars.com"));
	}

	@Test
	public void shouldQueryEntity() throws Exception {
		mockMvc.perform(post("/employee").content(
				"{\"firstName\": \"Luke\", "
				+ "\"lastName\":\"Skywalker\", "
				+ "\"contactNumber\":\"+12345678291\", "
				+ "\"email\":\"lukeskywalker@starwars.com\"}")).andExpect(
						status().isCreated());

		mockMvc.perform(
				get("/employee/search/findByLastNameLike?name={name}", "walker")).andExpect(
						status().isOk()).andExpect(
								jsonPath("$._embedded.employee[0].firstName").value(
										"Luke"));
	}

	@Test
	public void shouldUpdateEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
				"{\"firstName\": \"Luke\", "
				+ "\"lastName\":\"Skywalker\", "
				+ "\"contactNumber\":\"+12345678291\", "
				+ "\"email\":\"lukeskywalker@starwars.com\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");

		mockMvc.perform(put(location).content(
				"{\"firstName\": \"Darth\", \"lastName\":\"Vader\"}")).andExpect(
						status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isOk()).andExpect(
				jsonPath("$.firstName").value("Darth")).andExpect(
						jsonPath("$.lastName").value("Vader"));
	}

	@Test
	public void shouldDeleteEntity() throws Exception {
		MvcResult mvcResult = mockMvc.perform(post("/employee").content(
				"{\"firstName\": \"Luke\", "
				+ "\"lastName\":\"Skywalker\", "
				+ "\"contactNumber\":\"+12345678291\", "
				+ "\"email\":\"lukeskywalker@starwars.com\"}")).andExpect(
						status().isCreated()).andReturn();

		String location = mvcResult.getResponse().getHeader("Location");
		mockMvc.perform(delete(location)).andExpect(status().isNoContent());

		mockMvc.perform(get(location)).andExpect(status().isNotFound());
	}
}