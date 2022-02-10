package pt.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import pt.dto.core.RestResponse;
import pt.dto.request.FormDataReqDto;
import pt.exception.CustomException;
import pt.exception.CustomServiceException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pt.model.Sample;
import pt.repository.SampleRepository;
import pt.service.SampleService;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.HEAD})
@Validated
public class SampleController {
        private static final Logger LOGGER = LoggerFactory.getLogger(SampleController.class);

    @Autowired
    @Qualifier("sampleService")
    private SampleService sampleService;

    @ApiOperation(value = "SAMPLESEARCH", nickname = "SAMPLESEARCH" ,
            notes = "Este servicio busca en una db en memoria H2, filtrando por todos los campos, pudiendo ordenarlos y limitar la cantidad de registros por pagina." +
                    "El contenido del csv es cargado al iniciar el sistema")
    @GetMapping(value = "/sampleSearch", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Valid stock item found"),
            @ApiResponse(code = 404, response = PagedModel.class, message = "Stock item not found")}
    )
//    @PreAuthorize("hasPermission('','SAMPLE')")
    public ResponseEntity<PagedModel<Sample>> sampleSearch(Pageable pageable,@Valid FormDataReqDto request,
                                                               @ApiIgnore PagedResourcesAssembler assembler ) throws CustomException {

            Page<Sample> result = this.sampleService.buscarSample(pageable,request  );
            PagedModel pagedResources = assembler.toModel(result);
            return new ResponseEntity(new RestResponse<>(HttpStatus.OK, pagedResources), HttpStatus.OK);


    }
    @ApiOperation(value = "SAMPLELIST", nickname = "SAMPLELIST",produces = MediaType.APPLICATION_JSON_VALUE,
            notes = "Servicio que toma el csv, y lo lista")
    @GetMapping(value = "/sampleList", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasPermission('','SAMPLE')")
    public ResponseEntity<RestResponse> sampleList() throws Exception {
        return new ResponseEntity(new RestResponse(HttpStatus.OK, this.sampleService.listSample()), HttpStatus.OK);
    }

    @ApiOperation(value = "health", nickname = "health")
    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasPermission('','EJEPLO_VALIDACION_PERMISOS_USUARIO')")
    public ResponseEntity<RestResponse> health() throws JsonProcessingException, CustomServiceException {
        LOGGER.info("Executing health method.");
        return new ResponseEntity(true, HttpStatus.OK);
    }



}

