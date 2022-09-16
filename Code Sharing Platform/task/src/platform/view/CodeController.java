package platform.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.model.CodeDto;
import platform.model.Codes;
import platform.model.CodesService;

@Controller
public class CodeController {

    @Autowired
    CodesService codesService;

    @GetMapping("/code/{id}")
    public String findCode(@PathVariable String id, Model model) {
        CodeDto codes = codesService.findCode(id);
        Codes sameCode = codesService.searchCode(id);
        if(codes.getTime() ==0 && !sameCode.isTimeRestricted())codes.setTime(null);
        if(codes.getViews()==0 && !sameCode.isViewRestricted())codes.setViews(null);
        model.addAttribute("code", codes.getCode());
        model.addAttribute("date", codes.getDate());
        model.addAttribute("views_restriction", codes.getViews());
        model.addAttribute("time_restriction", codes.getTime());
        return "code";
    }

    @GetMapping("/code/latest")
    public String findLast(Model model) {
        model.addAttribute("codeList", codesService.findLast());
        return "codeList";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String getNewCode() {
        return "createnew";
    }



}
