package com.vichhai.demo_data_jpa_2.dto.requestBook;


import com.vichhai.demo_data_jpa_2.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOBookRequest {
    private String title;
    private String description;
    private String author;
    private LocalDate publicationYear;

    public void bookRequest(Book book){
        book.setTitle(title);
        book.setDescription(description);
        book.setAuthor(author);
        book.setPublicationYear(publicationYear);
    }

}
