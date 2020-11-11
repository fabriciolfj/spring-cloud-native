package com.github.fabriciolfj.catalago.domain.repository;

import com.github.fabriciolfj.catalago.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, String> {
    Collection<Book> findByOrderByTitle();
}
