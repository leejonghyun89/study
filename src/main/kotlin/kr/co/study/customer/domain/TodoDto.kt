package kr.co.study.customer.domain

/**
 * Created by JongHyun Lee on 2021-06-24
 */
class TodoDto {

  data class ResponseTodo(var todoId: Long, var contents: String, var done: Boolean)

}