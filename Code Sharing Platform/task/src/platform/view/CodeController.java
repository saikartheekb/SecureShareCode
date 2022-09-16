package platform.view;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.model.Codes;
import platform.model.EmptyJsonBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@RestController
public class CodeController {
    private Codes codes;

    private Codes sampleCode = new Codes(LocalDateTime.parse("2017-12-01T21:30"), "public static void main");
    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);



    @GetMapping("/api/code")
    public Map<String, String> returnCode(){
        return Map.of("code", getCode().toString(),
                "date", getCode().getDate().format(formatter));
    }

    @GetMapping("/code")
    public ResponseEntity<String> codeToHtml(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Code",
                "Value-ResponseEntityBuilderWithHttpHeaders");
        String message = String.format("""
                <html>
                <head>
                    <title>Code</title>
                </head>
                <body>
                    <span id="load_date"> %s </span>
                    <pre id="code_snippet">
                    %s
                    </pre>
                </body>
                </html>""",
                getCode().getDate().format(formatter),
                getCode().toString());

        return ResponseEntity.ok().
                headers(responseHeaders).
                body(message);
    }

    @GetMapping("/code/new")
    public ResponseEntity<String> codeFormHtml(){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Create code",
                "Value-ResponseEntityBuilderWithHttpHeaders");
        String message = String.format("""
                <!DOCTYPE html>
                <html>
                <head>
                    <title>Create</title>
                </head>
                <body>
                    <textarea id="code_snippet">  </textarea>
                    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
                    <script>
                    function send() {
                        let object = {
                            "code": document.getElementById("code_snippet").value
                        };
                       \s
                        let json = JSON.stringify(object);
                       \s
                        let xhr = new XMLHttpRequest();
                        xhr.open("POST", '/api/code/new', false)
                        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                        xhr.send(json);
                       \s
                        if (xhr.status == 200) {
                          alert("Success!");
                        }
                    }
                    </script>
                </body>
                </html>""");
        return ResponseEntity.ok().
                headers(responseHeaders).
                body(message);
    }

    @PostMapping("/api/code/new")
    public EmptyJsonBody postCode(@RequestBody Codes sampleCode){
        codes = sampleCode;
        codes.setDate(LocalDateTime.now());
        return new EmptyJsonBody();
    }
    @Bean
    private Codes getCode() {
        if (codes == null) return sampleCode;
        return codes;
    }
}