package pt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.dto.core.RestResponse;
import pt.dto.request.FormDataReqDto;
import pt.dto.responce.EchoRspDto;
import pt.exception.CustomException;
import pt.exception.CustomServiceException;
import pt.model.Sample;
import pt.service.SampleService;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
@Validated
public class HomeController {
        private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);



    @ApiOperation(value = "echo", nickname = "health")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasPermission('','EJEPLO_VALIDACION_PERMISOS_USUARIO')")
    public ResponseEntity<EchoRspDto> echo() throws IOException, CustomServiceException, XmlPullParserException {
        LOGGER.info("Executing health method.");
        EchoRspDto echoRspDto=new EchoRspDto();
        echoRspDto.setTimestamp(new Date().getTime());


        InetAddress inetAddress = InetAddress.getLocalHost();
        echoRspDto.setHostname(inetAddress.getHostName());
        echoRspDto.setVisitorIp(String.valueOf(inetAddress));

        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(new FileReader("pom.xml"));
        echoRspDto.setEngine(System.getProperty("java.version"));

        return new ResponseEntity(echoRspDto, HttpStatus.OK);
    }



}

