package com.taskmanager.service;

import java.util.List;

import com.taskmanager.domain.Task;
import com.taskmanager.domain.TaskStatus;
import com.taskmanager.dto.TaskRequest;
import com.taskmanager.dto.TaskResponse;
import com.taskmanager.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponse create(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setStatus(TaskStatus.OPEN);
        return TaskResponse.from(taskRepository.save(task));
    }

    public List<TaskResponse> findAll() {
        return taskRepository.findAll(Sort.by(Sort.Direction.DESC,"createdAt")).stream().map(TaskResponse::from).toList();
    }

    public Page<TaskResponse> findAllByPage(Pageable pageable) {
        return taskRepository.findAll(pageable).map(TaskResponse::from);
    }

    public TaskResponse findById(Long id) {
        return TaskResponse.from(getById(id));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task task = getById(id);
        task.setTitle(request.getTitle());
        return TaskResponse.from(taskRepository.save(task));
    }

    public void deleteById(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        taskRepository.deleteById(id);
    }

    public TaskResponse complete(Long id) {
        Task task = getById(id);
        task.setStatus(TaskStatus.DONE);
        return TaskResponse.from(taskRepository.save(task));
    }

    public List<TaskResponse> findByStatus(TaskStatus status) {
        return taskRepository.findByStatusOrderByCreatedAtDesc(status)
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    private Task getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
