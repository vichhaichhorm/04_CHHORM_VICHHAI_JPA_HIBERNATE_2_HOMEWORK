package com.vichhai.demo_data_jpa_2.repository;

import com.vichhai.demo_data_jpa_2.dto.requestBook.DTOBookRequest;
import com.vichhai.demo_data_jpa_2.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public Book getBookById(String id) {
        return entityManger.find(Book.class, id);
    }

    public List<Book> getBookByTitle(String title) {
        String queryStr = "SELECT book FROM Book_db book WHERE book.title LIKE :title";
        TypedQuery<Book> query = entityManger.createQuery(queryStr, Book.class);
        query.setParameter("title", "%" + title + "%");
        return query.getResultList();
    }

    public Book deleteBookById(String id) {
        Book book = entityManger.find(Book.class, id);
        if (book != null) {
            entityManger.remove(book);
        }
        return book;
    }

    public Book updateBookById(String id, DTOBookRequest dtoBookRequest) {
        Book book = entityManger.find(Book.class, id);
        if (book != null) {
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
