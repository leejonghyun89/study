package kr.co.study.customer.service.impl

import kr.co.study.customer.domain.Todo
import kr.co.study.customer.repo.TodoRepo
import kr.co.study.customer.service.TodoService

import org.springframework.stereotype.Component

import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

/**
 * Created by JongHyun Lee on 2021-06-21
 */
@Component
class TodoServiceImpl(private val todoRepo: TodoRepo) : TodoService {

  override fun getTodoList(): Mono<List<Todo>> {
    return todoRepo.findAll().toMono()
  }

  override fun getTodoById(todoId: Long): Mono<Todo> {
    return Mono.justOrEmpty(todoRepo.findById(todoId))
  }

  override fun createTodo(todo: Todo): Mono<Todo> {
    return todoRepo.save(todo).toMono()
  }

  override fun updateTodo(todo: Todo): Mono<Todo> {
    return todoRepo.save(todo).toMono()
  }

  override fun deleteTodo(todo: Todo) {
    todoRepo.delete(todo)
  }

}