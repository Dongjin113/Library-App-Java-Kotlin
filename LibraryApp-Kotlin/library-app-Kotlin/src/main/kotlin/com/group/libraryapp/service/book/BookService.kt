package com.group.libraryapp.service.book

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import com.group.libraryapp.dto.book.request.BookReturnRequest
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.group.libraryapp.repository.book.BookQuerydslRepository
import com.group.libraryapp.repository.user.UserLoanHistoryQuerydslRepository
import com.group.libraryapp.util.fail
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BookService (
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val bookQuerydslRepository: BookQuerydslRepository,
    private val userLoanHistoryQuerydslRepository: UserLoanHistoryQuerydslRepository
        ){

    @Transactional
    fun saveBook(request: BookRequest){
        val book = Book(request.name, request.type)
        bookRepository.save(book)
    }

    @Transactional
    fun loanBook(request: BookLoanRequest){
        val book = bookRepository.findByName(request.bookName) ?: fail()

        if(userLoanHistoryQuerydslRepository.findByStatus(request.bookName,UserLoanStatus.LOANED) != null){
            throw IllegalArgumentException("진작 대출되어 있는 책입니다")
        }

        val user = userRepository.findByName(request.userName) ?: fail()
        user.loanBook(book)
    }

    @Transactional
    fun returnBook(request: BookReturnRequest){
        val user = userRepository.findByName(request.userName) ?: fail()
        user.returnBook(request.bookName)

    }

    @Transactional(readOnly = true)
    fun countLoanedBook(): Int {
//        return userLoanHistoryRepository.findAllByStatus(UserLoanStatus.LOANED).size
    return  userLoanHistoryQuerydslRepository.count(UserLoanStatus.LOANED).toInt()
    }

//    }

    //    @Transactional(readOnly = true)
//    fun getBookStatistics(): List<BookStatResponse> {
//        val results = mutableListOf<BookStatResponse>()
//        val books = bookRepository.findAll()
//        for (book in books){
//            val targetDto = results.firstOrNull {dto -> book.type == dto.type}?.plusOne()
//                ?: results.add(BookStatResponse(book.type,1))
//        }
//        return results

//    코드개선1
//    @Transactional(readOnly = true)
//    fun getBookStatistics(): List<BookStatResponse> {
//        return bookRepository.findAll()
//            .groupBy { book -> book.type }
//            .map { (type, books) -> BookStatResponse(type, books.size.toLong()) }
//    }

//    코드개선3 SpringJPA 에서 JPQL을 사용`
//    @Transactional(readOnly = true)
//    fun getBookStatistics(): List<BookStatResponse> {
//        return bookRepository.getStatus()
//    }
    
//    코드개선4 Querydsl로 custom하여 사용
    @Transactional
    fun getBookStatus(): List<BookStatResponse>{
        return bookQuerydslRepository.getStats()
    }


}