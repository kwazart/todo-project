package com.polozov.todoproject.dataLoader;

import com.polozov.todoproject.domain.ToDoTask;
import com.polozov.todoproject.repository.ToDoTaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.Disposable;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDataLoader implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(TaskDataLoader.class);

    private final ToDoTaskRepository repository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // инициализация с тестовыми данными
        if (0L == repository.count().block()) {
            List<ToDoTask> tasks = List.of(
                    new ToDoTask("text - 1", 1),
                    new ToDoTask("text - 2", 2),
                    new ToDoTask("text - 3", 1)
            );

            repository.saveAll(tasks).subscribe();

            log.info("Repository contains now {} entries.", repository.count().block());
        } else {
            log.info("Repository contains {} old entries.", repository.count().block());
        }
    }
}
