package com.polozov.todoproject.config;

import com.polozov.todoproject.handlers.ToDoTaskHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ToDoRestRouter {

    @Bean
    public RouterFunction<ServerResponse> routeToDoTask(ToDoTaskHandler handler) {
        return RouterFunctions
                .route(GET("/func/task/{id}"), handler::getTaskById)
                .andRoute(POST("/func/task/create"), handler::saveTask)
                .andRoute(GET("/func/task"), handler::getAllTasks)
                .andRoute(PUT("/func/task/{id}"), handler::update)
                .andRoute(DELETE("/func/task/{id}"), handler::delete)
                .andRoute(GET("/func/task/byPriority/{priority}"), handler::getByPriority);

    }
}
