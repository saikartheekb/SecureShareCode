package platform.view;

//import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.model.Codes;

@RestController //@RequestMapping
public class CodeController {
    String exampleCode = "public static void main(String[] args) {\n    SpringApplication.run(CodeSharingPlatform.class, args);\n}";

    @GetMapping("/api/code")
    public Codes returnCode(){
        return getCode();
    }

    @GetMapping(value ="/codes", produces = MediaType.TEXT_HTML_VALUE)
    public String returnCodeHtml(){
        return getCode().toString();
    }

    @GetMapping("/code")
    public ResponseEntity<String> usingResponseEntityBuilderandHttpHeaders(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Baeldung-Example-Header",
                "Value-ResponseEntityBuilderWithHttpHeaders");
        String message = String.format("""
                <html>
                <head>
                    <title>Code</title>
                </head>
                <body>
                    <pre>
                %s
                </pre>
                </body>
                </html>""", getCode());
        return ResponseEntity.ok().headers(responseHeaders).body(message)
;    }
//    @Bean
    private Codes getCode() {
        return new Codes(exampleCode);
    }
}
