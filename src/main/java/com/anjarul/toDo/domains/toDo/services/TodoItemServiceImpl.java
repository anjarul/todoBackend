package com.anjarul.toDo.domains.toDo.services;


import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import com.anjarul.toDo.domains.toDo.repositories.TodoItemRepository;
import com.anjarul.toDo.exceptions.NotFoundException;
import com.anjarul.toDo.helperUtilities.PageAttr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class TodoItemServiceImpl implements TodoItemService {

    private final TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemServiceImpl(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    @Override
    public Page<TodoItem> search(String query, int page, int size) {
        if (query != null) query = query.toLowerCase();
        return this.todoItemRepository.search(query, PageAttr.getPageRequest(page, size));
    }

    @Override
    public TodoItem save(TodoItem entity) {
        Objects.requireNonNull(entity);
        return todoItemRepository.save(entity);
    }

    @Override
    public Optional<TodoItem> find(Long id) {
        return this.todoItemRepository.find(id);
    }

    @Override
    public void delete(Long id, Boolean softDelete) throws NotFoundException {
        if (softDelete) {
            TodoItem item = this.todoItemRepository.find(id).orElseThrow(NotFoundException::new);
            item.setDeleted(true);
            this.todoItemRepository.save(item);
            return;
        }
        this.todoItemRepository.deleteById(id);
    }

    @Override
    public TodoItem getTodoById(Long id) {
        return this.todoItemRepository.getTodoById(id);
    }
}
