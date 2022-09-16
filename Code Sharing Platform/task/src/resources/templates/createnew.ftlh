<!DOCTYPE html>

<html>
<head>
    <title>Create</title>
       <script>
        function send() {

            let object = {
                "code": document.getElementById("code_snippet").value,
                 "time": document.getElementById("time_restriction").value,
                  "views": document.getElementById("views_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false)
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status == 200) {
              alert("Success!");
            }
        }
        </script>
</head>
<body>
<form>
    <p><textarea id="code_snippet" placeholder="write your code here"></textarea>
    <p><input id="time_restriction" placeholder="time_restriction" type="text"/>
     <p><input id="views_restriction" placeholder="view restriction" type="text"/>

    <button id="send_snippet" type="submit" onclick="send()">Submit</button>

</form>

</body>
</html>