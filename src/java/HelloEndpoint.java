
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Herc
 */
@ServerEndpoint("/hello")
public class HelloEndpoint {

    @Inject
    private SessionRegistry sessionRegistry;

    @OnMessage
    public String onMessage(String message, Session sender) throws IOException {
       final ObjectMapper mapper = new ObjectMapper();
        final JsonNode messagejson = mapper.readTree(message);
        String name = messagejson.get("name").asText();
        String text = messagejson.get("text").asText();
        String date = messagejson.get("date").asText();
         Set<Session>sessions=sessionRegistry.getAll();
        for (Session s : sessions) {
            s.getBasicRemote().sendText("xristis : me onoma " + name + " eipe " + text + " stis " + date);
        }
        return null;

    }

    @OnOpen
    public void open(Session session, EndpointConfig conf) throws IOException {
        Map<String, String> params = session.getPathParameters();
        sessionRegistry.add(session);
        session.getBasicRemote().sendText("People in chat" + sessionRegistry);
    }

    @OnClose
    public void close(Session session) {
        sessionRegistry.remove(session);
    }
}
