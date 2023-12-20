package com.example.SSE;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class NewsController {
//    public Map<String, SseEmitter> emitters= new CopyOnWriteArrayList<>();
public Map<String, SseEmitter> emitters= new HashMap<>();
//    method for client subscription
    @CrossOrigin
    @RequestMapping(value = "/subscribe", consumes = MediaType.ALL_VALUE)
    public SseEmitter subscribe( @RequestParam String userID){
        SseEmitter sseEmitter=new SseEmitter(Long.MAX_VALUE);
        sendInitEvent(sseEmitter);
        emitters.put(userID,sseEmitter);

        sseEmitter.onCompletion(() -> emitters.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emitters.remove(sseEmitter));
        sseEmitter.onError((e) -> emitters.remove(sseEmitter));

        return sseEmitter;
    }

    @PostMapping(value = "/dispatchToEvent")
    //method for dispatching event for all clients
    public void dispatchEventToClients(@RequestParam String title, @RequestParam String text, @RequestParam String userID){
        String eventFormatted= new JSONObject()
                .put("title",title)
                .put("text",text).toString();

        SseEmitter sseEmitter=emitters.get(userID);
            if (sseEmitter!=null){
                try{
                    sseEmitter.send(SseEmitter.event().name("latestNews").data(eventFormatted));

                }catch (IOException e){
                    emitters.remove(sseEmitter);
                }
            }

        }
    private void sendInitEvent (SseEmitter sseEmitter){
        try{
            sseEmitter.send(SseEmitter.event().name("INIT"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    }

