package util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/GetAnswer")
public class GetAnswer {
	private static Set<Session> userSessions = Collections.newSetFromMap(new ConcurrentHashMap<Session, Boolean>());

	@OnOpen
	public void onOpen(Session curSession) {
		userSessions.add(curSession);
	}

	@OnClose
	public void onClose(Session curSession) {
		userSessions.remove(curSession);
	}

	@OnMessage
	public void onMessage(String message, Session userSession) {
		String crtans = db.Database.getCorrectAnswer(message);
		String ans;

		switch (crtans) {
		case "1":
			ans = "A";
			break;
		case "2":
			ans = "B";
			break;
		case "3":
			ans = "C";
			break;
		case "4":
			ans = "D";
			break;
		default:
			ans = crtans;
			break;
		}

		for (Session ses : userSessions) {
			ses.getAsyncRemote().sendText(ans);
		}
	}
}