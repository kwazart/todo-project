package com.polozov.todoproject.repository;

import com.polozov.todoproject.domain.ToDoTask;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface ToDoTaskRepository extends ReactiveMongoRepository<ToDoTask, String> {

    Flux<ToDoTask> findAll();

    Mono<ToDoTask> findById(String id);

    Mono<Void> save(Mono<ToDoTask> toDoTask);

    Flux<ToDoTask> findAllByTextRegex(String text);

    Flux<ToDoTask> findAllByPriority(int priority);

//    Flux<ToDoTask> findAllByCreatedDate(Instant createdDate);
//    Flux<ToDoTask> findAllByEndDate(Instant endDate);
//    Flux<ToDoTask> findAllByStartDate(Instant startDate);
}
