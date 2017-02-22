package be.cegeka.bibliothouris.domain.rentals;

import be.cegeka.bibliothouris.domain.books.Book;
import be.cegeka.bibliothouris.domain.members.Member;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by stijnli on 26/01/2017.
 */
public class Rental {
    private Member member;
    private Book book;
    private LocalDate dueDate;

    public Rental(Member member, Book book) {
        this.member = member;
        this.book = book;
        this.dueDate = LocalDate.now().plusWeeks(3);
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
