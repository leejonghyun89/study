package kr.co.study.customer.router

import kr.co.study.customer.handler.TodoHandler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RequestPredicates.path
import org.springframework.web.reactive.function.server.RouterFunctions.nest
import org.springframework.web.reactive.function.server.router

/**
 * Created by JongHyun Lee on 2021-06-21
 */
@Configuration
class TodoRouter(private val handler: TodoHandler) {

  @Bean
  fun routerFun() = nest(path("/api/todo"),
      router {
          listOf(
              GET("", handler::getTodoList),
              GET("/{id}", handler::getTodoById),
              POST("", handler::save),
              PUT("/{id}", handler::done),
              DELETE("/{id}", handler::delete)
          )
      }
  )

}