package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.loanhistory.QUserLoanHistory.userLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class UserLoanHistoryQuerydslRepository(
    private val queryFactory: JPAQueryFactory
) {
//    findByBookName
    fun find(bookName: String): UserLoanHistory?{
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName)
            )
            .limit(1)
            .fetchOne()
    }
//    findByBookNameAndStatus
    fun findByStatus(bookName: String, status: UserLoanStatus? = null): UserLoanHistory?{
        return queryFactory.select(userLoanHistory)
            .from(userLoanHistory)
            .where(
                userLoanHistory.bookName.eq(bookName),
//          status가 null인경우에는 이코드자체가 null이기때문에 where에 들어온 null은 무시해준다
//          status에 값이 들어올경우에만 and로 실행이 된다
                status?.let{ userLoanHistory.status.eq(status)}
            )
            .limit(1)
            .fetchOne()
    }

    fun count(status: UserLoanStatus): Long{
        return queryFactory.select(userLoanHistory.id.count())
            .from(userLoanHistory)
            .where(userLoanHistory.status.eq(status))
            .fetchOne() ?: 0L
    }
}