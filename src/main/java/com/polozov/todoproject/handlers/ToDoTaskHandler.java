package com.polozov.todoproject.handlers;

import com.polozov.todoproject.domain.ToDoTask;
import com.polozov.todoproject.repository.ToDoTaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ToDoTaskHandler {

    private final ToDoTaskRepository repository;

    public Mono<ServerResponse> getAllTasks(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(repository.findAll(), ToDoTask.class);
    }

    public Mono<ServerResponse> getTaskById(ServerRequest request) {
        return repository.findById(request.pathVariable("id"))
                .flatMap(task -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(task)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> saveTask(ServerRequest request) {
        Mono<ToDoTask> newTaskMono = request.bodyToMono(ToDoTask.class)
                .flatMap(repository::save);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newTaskMono, ToDoTask.class);
    }

    public Mono<ServerResponse> update(ServerRequest request) {
        Mono<ToDoTask> newTaskMono = request.bodyToMono(ToDoTask.class)
                .map(t -> {
                    t.setId(request.pathVariable("id"));
                    return t;
                })
                .flatMap(repository::save);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newTaskMono, ToDoTask.class);

    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        return ServerResponse.noContent().build(repository.deleteById(request.pathVariable("id")));
    }

    public Mono<ServerResponse> getByPriority(ServerRequest request) {
        int priority = Integer.parseInt(request.pathVariable("priority"));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<ToDoTask> taskFlux = repository.findAllByPriority(priority);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(taskFlux, ToDoTask.class)
                .switchIfEmpty(notFound);
    }
}
