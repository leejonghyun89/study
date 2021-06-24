package kr.co.study.customer.service

import kr.co.study.customer.domain.Todo

import reactor.core.publisher.Mono

/**
 * Created by JongHyun Lee on 2021-06-21
 */
interface TodoService {

  fun getTodoList() : Mono<List<Todo>>

  fun getTodoById(todoId: Long): Mono<Todo>

  fun createTodo(todo: Todo): Mono<Todo>

  fun updateTodo(todo: Todo): Mono<Todo>

  fun deleteTodo(todo: Todo)

}