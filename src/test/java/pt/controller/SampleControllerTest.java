package pt.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.opencsv.bean.CsvBindByName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.HttpStatus;
import pt.model.SampleCsv;
import pt.service.SampleService;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.properties")
@AutoConfigureMockMvc

public class SampleControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(SampleControllerTest.class);

    @Autowired
    private MockMvc mockMvc;


//    @MockBean
//    SampleService sampleService;

    @Test
    public void sampleListTest() throws Exception {
        String response = mockMvc.perform(get ( "/public/sampleList"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.body[0].countyFips", is(1001)))
                .andExpect(jsonPath("$.body[0].countyName", is("Autauga")))
                .andExpect(jsonPath("$.body[0].stateName", is("Alabama")))
                .andExpect(jsonPath("$.body[0].date", is("5/8/20")))
                .andExpect(jsonPath("$.body[0].countyVmt", is(3550000)))
                .andExpect(jsonPath("$.body[0].baselineJanVmt", is(3571446)))
                .andExpect(jsonPath("$.body[0].percentChangeFromJan", is(-0.6)))
                .andExpect(jsonPath("$.body[0].mean7CountyVmt", is(2790000.0)))
                .andExpect(jsonPath("$.body[0].mean7PercentChangeFromJan", is(-21.88)))
                .andExpect(jsonPath("$.body[0].dateAtLow", is("4/12/20")))
                .andExpect(jsonPath("$.body[0].mean7CountyVmtAtLow", is(1542857.14)))
                .andExpect(jsonPath("$.body[0].percentChangeFromLow", is( 80.83)))
                .andReturn().getResponse().getContentAsString();
        logger.info("response: " + response);
    }
}
