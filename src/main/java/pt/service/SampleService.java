package pt.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pt.dto.request.FormDataReqDto;
import pt.exception.CustomServiceException;
import pt.model.Sample;
import pt.model.SampleCsv;
import pt.repository.SampleRepository;

import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)

@Service("sampleService")
public class SampleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    @Qualifier("sampleRepository")
    private SampleRepository sampleRepository;


    public Page<Sample> buscarSample(Pageable pageable, FormDataReqDto request) throws CustomServiceException {
        Page<Sample> documentos;
        try {

            return sampleRepository.findAllByRequest(request, pageable);

        } catch (Exception e) {
            throw new CustomServiceException("Error en buscarSample" );
        }
    }

    public List<SampleCsv> listSample() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:04_SAMPLE_vmt_county.csv");
        logger.info(resource.getURI().getPath());

        Reader reader = new InputStreamReader(resource.getInputStream());

      //  new BufferedReader(new InputStreamReader(inputStream));
      //  Reader reader = Files.newBufferedReader( Paths.get(resource.getURI()));
        CsvToBean<SampleCsv> csvToBean = new CsvToBeanBuilder<SampleCsv>(reader)
                .withType(SampleCsv.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<SampleCsv> lista = csvToBean.parse();

        return lista;

    }
}
