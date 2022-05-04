package ie.licenta.artassistant.controllers;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ApiOperation("Museum API")
@RequestMapping("/api")
@CrossOrigin("*")
@Tag(name = "Museum requests")
@AllArgsConstructor
public class UserController {
}
