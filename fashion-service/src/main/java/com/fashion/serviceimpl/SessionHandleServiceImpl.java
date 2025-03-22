package com.fashion.serviceimpl;

import com.fashion.repo.SessionRepo;
import com.fashion.request.SessionData;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SessionHandleServiceImpl {

    @Autowired
    SessionRepo sessionRepo;

    public String storeSession(HttpSession session) {
        log.info("Session Value: " + session);
        if(session==null){

            return "Session is null";
        }else {
            SessionData sessionData = new SessionData();
            Map<String, String> sessionRsp = getSession(session);
            sessionData.setSessionId(sessionRsp.get("sessionId"));
            sessionData.setSessionMsg(sessionRsp.get("message"));
            sessionRepo.save(sessionData);
            return sessionData.getSessionMsg();
        }
    }
    public Map<String, String> getSession(HttpSession session) {

        Map<String, String> response = new HashMap<>();

        if (session.isNew()) {
            response.put("message", "New session created!");
        } else {
            response.put("message", "Returning user session!");
        }

        response.put("sessionId", session.getId());

        return response;
    }
}
