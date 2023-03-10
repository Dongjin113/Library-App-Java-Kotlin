package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserRepository : JpaRepository<User, Long>, UserRepositoryCustom{

    fun findByName(name: String): User?

//    @Query("select DISTINCT u from User u left join fetch u.userLoanHistories")
//    fun findAllWithHistories() : List<User>

}