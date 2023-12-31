package com.BrickNet.TaskCenter.service;

import com.BrickNet.TaskCenter.dto.NotificationDTO;
import com.BrickNet.TaskCenter.dto.TodoDTO;
import com.BrickNet.TaskCenter.exception.TodoException;

import javax.management.ServiceNotFoundException;
import java.util.List;

public interface TodoService {

    TodoDTO addToDo(TodoDTO todoDTO) throws TodoException , ServiceNotFoundException;

    List<TodoDTO> showCreatedToDo(String employeeCode) throws TodoException;

    TodoDTO updateCreatedToDo(String id, TodoDTO todoDTO) throws TodoException;

    void deleteToDo(String id1) throws TodoException;

    public void pushNotification(NotificationDTO notificationDto) throws ServiceNotFoundException;


}
