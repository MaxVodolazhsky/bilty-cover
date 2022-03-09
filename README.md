Bilty-cover (Для укорачивания и разворачивания коротких ссылок (аналог https://bitly.com/))
Способ запуска:
1. Прописать команду git clone https://github.com/MaxVodolazhsky/bilty-cover.git
2. В application.properties в переменной server.port прописать значение порта, которое будет занимать данное app.
3. Для укорачевание ссылок необходимо послать POST запрост на ендпоинт localhost:{application.properties/server.port}/boxing тело которого будет содержать следующий json:
    {
        "unboxingLink" : "https://www.google.com"
    }
    В ответ должен прислать укороченную ссылку - формата http://bilty-cover/{\\d+}
4. Для разворачивания ссылок необходимо послать POST запрост на ендпоинт localhost:{application.properties/server.port}/unboxing тело которого будет содержать json:
    {
        "boxingLink" : "http://bilty-cover/{\\d+}"
    }
    В ответ должен прислать развернутую ссылку.
