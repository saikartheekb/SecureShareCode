package platform.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.model.CodeDto;
import platform.model.Codes;
import platform.model.CodesService;

import java.util.List;
import java.util.Map;

@RestController
public class CodeRestController {

    @Autowired
    CodesService codesService;


    @GetMapping("/api/code/{id}")
    public CodeDto findCode(@PathVariable String id) {
        return codesService.findCode(id);
    }


    @GetMapping("/api/code/latest")
    public List<CodeDto> findLast() {
        return codesService.findLast();
    }


    @PostMapping("/api/code/new")
    public Map<String, String> postCode(@RequestBody Codes codes1) {
        System.out.println(codes1);
        return codesService.postCode(codes1);
    }


}