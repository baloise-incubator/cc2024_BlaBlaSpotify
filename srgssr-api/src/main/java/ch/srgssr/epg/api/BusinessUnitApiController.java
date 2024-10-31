package ch.srgssr.epg.api;

import org.springframework.format.annotation.DateTimeFormat;
import ch.srgssr.epg.api.Error;
import java.time.LocalDate;
import ch.srgssr.epg.api.Program;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-10-31T10:50:32.787731300+01:00[Europe/Zurich]")
@Controller
@RequestMapping("${openapi.sRGSSREPGV3.base-path:/epg/v3}")
public class BusinessUnitApiController implements BusinessUnitApi {

    private final NativeWebRequest request;

    @Autowired
    public BusinessUnitApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
