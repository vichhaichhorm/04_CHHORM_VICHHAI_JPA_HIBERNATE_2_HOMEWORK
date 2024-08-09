package com.vichhai.demo_data_jpa_2.dto.responseBook;

import com.vichhai.demo_data_jpa_2.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOBookResponse {
    private String id;
    private String title;
    private String description;
    private String author;
    private LocalDate publicationYear;
    public void bookResponse(Book book){
        id = book.getId();
        title = book.getTitle();
        description = book.getDescription();
        author = book.getAuthor();
        publicationYear = book.getPublicationYear();
    }
}
