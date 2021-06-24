package kr.co.study.customer.handler

import kr.co.study.customer.domain.Todo
import kr.co.study.customer.domain.TodoDto
import kr.co.study.customer.service.TodoService

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromValue
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*

import reactor.core.publisher.Mono

import java.time.LocalDateTime
import java.util.*

/**
 * Created by JongHyun Lee on 2021-06-21
 */
@Component
class TodoHandler(private val todoService: TodoService) {

  fun getTodoList(request: ServerRequest): Mono<ServerResponse> =
      ok().body(todoService.getTodoList(), Todo::class.java)

  fun getTodoById(request: ServerRequest): Mono<ServerResponse> =
      todoService.getTodoById(request.pathVariable("id").toLong())
          .flatMap {
            val (todoId, contents, done) = it
            val responseTodo = TodoDto.ResponseTodo(todoId!!, contents!!, done)
            ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(responseTodo))
          }
          .switchIfEmpty(status(HttpStatus.BAD_REQUEST).build())

  fun save(request: ServerRequest): Mono<ServerResponse> =
      request.bodyToMono(Todo::class.java)
          .filter(Objects::nonNull)
          .flatMap { todo ->
              Mono.fromCallable {
                todoService.createTodo(todo)
              }.then(status(HttpStatus.CREATED).build())
          }
          .switchIfEmpty(status(HttpStatus.BAD_REQUEST).build())

  fun done(request: ServerRequest): Mono<ServerResponse> =
      todoService.getTodoById(request.pathVariable("id").toLong())
          .filter(Objects::nonNull)
          .flatMap { todo ->
            Mono.fromCallable {
              todo.done = false
              todo.modified = LocalDateTime.now()
              todoService.updateTodo(todo)
            }.then(status(HttpStatus.OK).build())
          }
          .switchIfEmpty(status(HttpStatus.BAD_REQUEST).build())

  fun delete(request: ServerRequest): Mono<ServerResponse> =
      todoService.getTodoById(request.pathVariable("id").toLong())
          .filter(Objects::nonNull)
          .flatMap { todo ->
            Mono.fromCallable {
              todoService.deleteTodo(todo)
            }.then(status(HttpStatus.OK).build())
          }
          .switchIfEmpty(status(HttpStatus.BAD_REQUEST).build())

}