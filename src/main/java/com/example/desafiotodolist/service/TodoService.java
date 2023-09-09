package com.example.desafiotodolist.service;

import com.example.desafiotodolist.entity.Todo;
import com.example.desafiotodolist.repository.TodoRepository;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }//no lugar do @Autowired

    public List<Todo> create(Todo todo){
        todoRepository.save(todo);
        return list();
   }

   /*
    public List<Todo> list(){
       Sort sort = Sort.by(...properties:"prioridade").descending().and(
          Sort.by(...properties:"nome").ascending()
        );
        return todoRepository.findAll(sort);
    }*/


    public List<Todo> list() {
        Order priorityOrder = new Order(Sort.Direction.DESC, "prioridade");
        Order nameOrder = new Order(Sort.Direction.ASC, "nome");

        Sort sort = Sort.by(priorityOrder, nameOrder);
        return todoRepository.findAll(sort);
    }

    public List<Todo> update(Todo todo){
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> delete(Long id){
        todoRepository.deleteById(id);
        return list();
    }
}
