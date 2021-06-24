package kr.co.study.customer.repo

import kr.co.study.customer.domain.Todo

import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by JongHyun Lee on 2021-06-21
 */
interface TodoRepo : JpaRepository<Todo, Long>