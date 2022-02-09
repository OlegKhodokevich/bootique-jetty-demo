<!DOCTYPE html>
<html>
   <meta charset = "utf-8" />
   <title>WebSocket Test</title>

   <script language = "javascript" type = "text/javascript">
      var wsUri = "ws://172.30.224.1:9999/myapp/fw";
      var output;

      function init() {
         output = document.getElementById("output");
         testWebSocket();
      }

      function testWebSocket() {
         websocket = new WebSocket(wsUri);

         websocket.onopen = function(evt) {
            writeToScreen("Сonnection established. It is waiting for welcome message...");
         };

		websocket.onmessage = function(event) {
			writeToScreen(event.data)
		};

		websocket.onerror = function(error) {
			writeToScreen("Ошибка " + error.message);
		};
      }

      function writeToScreen(message) {
        var pre = document.createElement("p");
        pre.style.wordWrap = "break-word";
        pre.innerHTML = message; output.appendChild(pre);
		}

      window.addEventListener("load", init, false);

   </script>

   <h2>WebSocket Test</h2>
   <div id = "output"></div>

</html>