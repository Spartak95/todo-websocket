package com.apress.todo.event;

import com.apress.todo.config.ToDoProperties;
import com.apress.todo.domain.ToDo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RepositoryEventHandler(ToDo.class)
@RequiredArgsConstructor
public class ToDoEventHandler {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ToDoProperties toDoProperties;

    @HandleAfterCreate
    public void handleToDoSave(ToDo toDo) {
        simpMessagingTemplate.convertAndSend(this.toDoProperties.getBroker() + "/new", toDo);
        log.info(">> Sending Message to WS: ws://todo/new - " + toDo);
    }
}
