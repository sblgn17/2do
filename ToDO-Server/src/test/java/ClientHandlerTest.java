import data.server.ClientHandler;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ClientHandlerTest {

    @Test
    void testClientHandler() throws IOException, InterruptedException {
        // Given
        String request = "ADD_TASK " + LocalDate.now().toString() + " " + "testtask";
        BufferedReader in = new BufferedReader(new StringReader(request));
        StringWriter stringWriter = new StringWriter();
        PrintWriter out = new PrintWriter(stringWriter);

        // When
        ClientHandler handler = new ClientHandler(in, out);
        handler.start();

        // Then
        String response = stringWriter.toString();
        System.out.println("Response: " + response);
    }
}