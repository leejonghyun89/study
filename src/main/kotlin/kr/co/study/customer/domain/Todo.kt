package kr.co.study.customer.domain

import java.time.LocalDateTime
import javax.persistence.*

/**
 * Created by JongHyun Lee on 2021-06-21
 */
@Entity
data class Todo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var todoId: Long? = null,

    @Lob
    var contents: String? = null,

    var done: Boolean = false,

    var created: LocalDateTime = LocalDateTime.now(),

    var modified: LocalDateTime = created
)