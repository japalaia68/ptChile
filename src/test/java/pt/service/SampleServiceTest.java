package pt.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import pt.model.SampleCsv;
import pt.repository.SampleRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.properties")
public class SampleServiceTest {
	@MockBean
	SampleRepository sampleRepository;

	@Autowired
	SampleService sampleService;

	@Test
	public void testCantidadResultados() throws Exception {
		assertEquals(10,sampleService.listSample().size());
	}
	@Test
	public void testComparaResultado() throws Exception {
		List<SampleCsv> result= sampleService.listSample();
		SampleCsv sampleCsv=new SampleCsv(1001, "Autauga", "Alabama", "5/8/20",3550000L, 3571446L,-0.6, 2790000.0,-21.88,"4/12/20",1542857.14, 80.83);
		Assert.assertTrue(result.get(0).equals(sampleCsv));
	}
}
