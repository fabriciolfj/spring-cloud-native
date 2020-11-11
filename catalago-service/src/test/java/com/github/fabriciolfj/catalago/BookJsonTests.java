package com.github.fabriciolfj.catalago;

import com.github.fabriciolfj.catalago.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class BookJsonTests {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    void testSerialize() throws Exception {
        var book = new Book("1234567890", "Title", "Author", Year.of(1973));
        assertThat(json.write(book)).extractingJsonPathStringValue("@.isbn").isEqualTo("1234567890");
        assertThat(json.write(book)).extractingJsonPathStringValue("@.title").isEqualTo("Title");
    }

    @Test
    void testDeserialize() throws Exception {
        String content = "{\"isbn\":\"1234567890\",\"title\":\"Title\",\"author\":\"Author\",\"publishingYear\":\"1973\"}";
        assertThat(json.parse(content)).isEqualToComparingFieldByField(new Book("1234567890", "Title", "Author", Year.of(1973)));
        assertThat(json.parseObject(content).getIsbn()).isEqualTo("1234567890");
    }
}
