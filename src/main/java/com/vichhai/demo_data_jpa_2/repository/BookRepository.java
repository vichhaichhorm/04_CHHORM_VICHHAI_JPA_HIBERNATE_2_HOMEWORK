package com.vichhai.demo_data_jpa_2.repository;

import com.vichhai.demo_data_jpa_2.dto.requestBook.DTOBookRequest;
import com.vichhai.demo_data_jpa_2.entity.Book;
import com.vichhai.demo_data_jpa_2.exception.bookException.BookException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Repository
@AllArgsConstructor
@Transactional
public class BookRepository {
    @PersistenceContext
    private final EntityManager entityManger;

    public Book insertBook(Book book) {
        Book book1 = new Book();
        book1.setTitle(book.getTitle());
        book1.setAuthor(book.getAuthor());
        book1.setDescription(book.getDescription());
        book1.setPublicationYear(book.getPublicationYear());
        entityManger.persist(book1);
        return book1;
    }

    public List<Book> getAllBook() {
        return entityManger.createQuery("SELECT book FROM Book_db book", Book.class).getResultList();
    }

    public Book getBookById(String id) throws BookException {
        if (Objects.isNull(entityManger.find(Book.class, id))) {
            throw BookException.bookNotFound();
        } else {
            return entityManger.find(Book.class, id);
        }
    }
//    public List<Book> getBookByTitle(String title) throws BookException {
//        String queryStr = "SELECT book FROM Book_db book WHERE book.title LIKE :title";
//        TypedQuery<Book> query = entityManger.createQuery(queryStr, Book.class);
//        query.setParameter("title", "%" + title + "%");
//        List<Book> resultList = query.getResultList();
//        if (resultList.isEmpty()) {
//            throw BookException.bookNotFound();
//        }
//        return resultList;
//    }

    public List<Book> getBookByTitle(String title) throws BookException {
        String queryStr = "SELECT book FROM Book_db book WHERE LOWER(book.title) LIKE LOWER(:title)";
        TypedQuery<Book> query = entityManger.createQuery(queryStr, Book.class);
        query.setParameter("title", "%" + title.toLowerCase() + "%");

        List<Book> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            throw BookException.bookNotFound();
        }

        return resultList;
    }



    public Book deleteBookById(String id) throws BookException {
        Book book = entityManger.find(Book.class, id);
        if (Objects.isNull(entityManger.find(Book.class, id))) {
            throw BookException.bookNotFound();
        }
        if (book != null) {
            entityManger.remove(book);
        }
        return book;
    }

    public Book updateBookById(String id, DTOBookRequest dtoBookRequest) throws BookException {
        Book book = entityManger.find(Book.class, id);
        if (Objects.isNull(book)) {
            throw BookException.bookNotFound();
        } else {
            entityManger.detach(book);
            book.setId(id);
            book.setTitle(dtoBookRequest.getTitle());
            book.setAuthor(dtoBookRequest.getAuthor());
            book.setDescription(dtoBookRequest.getDescription());
            book.setPublicationYear(dtoBookRequest.getPublicationYear());
            entityManger.merge(book);
        }
        return book;
    }

}
