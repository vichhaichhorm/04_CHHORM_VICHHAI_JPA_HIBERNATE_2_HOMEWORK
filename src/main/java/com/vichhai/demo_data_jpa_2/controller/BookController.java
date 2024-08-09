package com.vichhai.demo_data_jpa_2.controller;

import com.vichhai.demo_data_jpa_2.apiResponse.APIResponse;
import com.vichhai.demo_data_jpa_2.dto.requestBook.DTOBookRequest;
import com.vichhai.demo_data_jpa_2.dto.responseBook.DTOBookResponse;
import com.vichhai.demo_data_jpa_2.entity.Book;
import com.vichhai.demo_data_jpa_2.exception.bookException.BookException;
import com.vichhai.demo_data_jpa_2.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v3/book")
@AllArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

//   how to format api response status code crud
//    @Operation(summary = "Insert book",
//            description = "The request has succeeded and a new resource has been created as a result.",
//            responses = {
//                    @ApiResponse(responseCode = "201", description = "Successful Operation"),
//                    @ApiResponse(responseCode = "400", description = "Bad Request")
//            })
    @PostMapping("insertBook")
    @Operation(summary = "Insert book",
            description = "The request has succeeded and a new resource has been created as a result.")
    public ResponseEntity<APIResponse<Object>> insertBook(@RequestBody DTOBookRequest dtoBookRequest ){
        Book book1 = new Book();
        dtoBookRequest.bookRequest(book1);
        Book saveBook = bookRepository.insertBook(book1);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Post Book successful")
                .payload(saveBook)
                .status(HttpStatus.CREATED.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getAllBook")
    @Operation(summary = "Read all book",
            description = "The request was successful, and the response contains the requested data")
    public ResponseEntity<APIResponse<Object>> getAllBook(){
        List<Book> getBooks = bookRepository.getAllBook();
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get all book successful")
                .payload(getBooks)
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBookById/{id}")
    @Operation(summary = "Read book by id",
            description = "The request was successful, and the response contains the requested data.")
    public ResponseEntity<Object> getBookById(@PathVariable String id) throws BookException {
        Book getBookId = bookRepository.getBookById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get book with id successful")
                .payload(getBookId)
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/getBookByTitle/{title}")
    @Operation(summary = "Read book by title",
            description = "The request was successful, and the response contains the requested data.")
    public ResponseEntity<Object> getBookByTitle(@PathVariable String title) throws BookException {
        List<Book> getBookName = bookRepository.getBookByTitle(title);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Get book by title successful")
                .payload(getBookName)
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteBookById/{id}")
    @Operation(summary = "Delete book by id",
            description = "The request was successful, and the resource was deleted.")
    public ResponseEntity<Object> deleteBookById(@PathVariable String id) throws BookException {
        Book deleteBookById = bookRepository.deleteBookById(id);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Deleted book successful")
                .payload(deleteBookById)
                .status(HttpStatus.OK.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/updateBookById/{id}")
    @Operation(summary = "Update book by id (Retrieve, detach, modify, and reattach an entity.)",
            description = "The request was successful, and the resource was updated")
    public ResponseEntity<APIResponse<Object>> updateBookById(@PathVariable String id, @RequestBody DTOBookRequest dtoBookRequest) throws BookException {
        Book book = bookRepository.updateBookById(id,dtoBookRequest);
        dtoBookRequest.bookRequest(book);
        DTOBookResponse dtoBookResponse = new DTOBookResponse();
        dtoBookResponse.bookResponse(book);
        APIResponse<Object> apiResponse = APIResponse.builder()
                .message("Updated book has been successful")
                .payload(dtoBookResponse)
                .status(HttpStatus.CREATED.value())
                .code(HttpStatus.OK)
                .time(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
