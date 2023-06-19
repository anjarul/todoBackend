package com.anjarul.toDo.domains.toDo.models.mappers;

import com.anjarul.toDo.constants.ErrorMessages;
import com.anjarul.toDo.domains.base.models.mappers.BaseMapper;
import com.anjarul.toDo.domains.toDo.models.dtos.TodoItemDto;
import com.anjarul.toDo.domains.toDo.models.entities.TodoItem;
import com.anjarul.toDo.domains.toDo.models.enums.Priority;
import com.anjarul.toDo.domains.toDo.models.enums.Status;
import com.anjarul.toDo.exceptions.PriorityValidationException;
import com.anjarul.toDo.helperUtilities.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class TodoItemMapper implements BaseMapper<TodoItem, TodoItemDto> {

    @Override
    public TodoItemDto map(TodoItem entity) {
        Objects.requireNonNull(entity);
        TodoItemDto dto = new TodoItemDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPriority(Priority.get(entity.getPriority()));
        dto.setStatus(Status.valueOf(entity.getStatus()));
        dto.setTags(entity.getTags());

        dto.setDate(entity.getDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    @Override
    public TodoItem map(TodoItemDto dto, TodoItem exEntity) throws PriorityValidationException {
        TodoItem entity = exEntity;
        if (dto.getDate() != null && dto.getPriority() != null)
            this.validatePriority(dto.getPriority(), dto.getDate());

        if (entity == null) entity = new TodoItem();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate() == null ? new Date() : dto.getDate());
        entity.setPriority(dto.getPriority() == null ? Priority.NORMAL.name() : dto.getPriority().name());

        entity.setStatus(dto.getStatus() == null ? Status.PENDING.name() : dto.getStatus().name());
        entity.setTags(dto.getTags());

        return entity;
    }

    private void validatePriority(Priority priority, Date date) throws PriorityValidationException {
        if (priority.name().equals("URGENT")) {

            if (new Date().after(date)) {
                throw new PriorityValidationException(ErrorMessages.PREVIOUS_DATE_ERROR_MESSAGE_FOR_PRIORITY_URGENT);
            }

            if (DateUtil.getDateDifference(date) > 2) {
                throw new PriorityValidationException(ErrorMessages.MAX_DATE_SELECTION_ERROR_MESSAGE_FOR_PRIORITY_URGENT);
            }
        }
    }
}
