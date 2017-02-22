package be.cegeka.bibliothouris.domain.books;

public class Book {

    private final String ISBN;
    private final String bookTitle;
    private final String authorFirstName;
    private final String authorLastName;

    public Book(String ISBN, String bookTitle, String authorFirstName, String authorLastName) {
        this.ISBN = ISBN;
        this.bookTitle = bookTitle;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getauthorFirstName() {
        return authorFirstName;
    }

    public String getauthorLastName() {
        return authorLastName;
    }


    public String getDetails()
    {
        StringBuilder sb = new StringBuilder();

        sb.append(ISBN + System.lineSeparator());
        sb.append(bookTitle + System.lineSeparator());
        sb.append(authorFirstName + System.lineSeparator());
        sb.append(authorLastName);

        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!ISBN.equals(book.ISBN)) return false;
        if (!bookTitle.equals(book.bookTitle)) return false;
        if (!authorFirstName.equals(book.authorFirstName)) return false;
        return authorLastName.equals(book.authorLastName);
    }

    @Override
    public int hashCode() {
        int result = ISBN.hashCode();
        result = 31 * result + bookTitle.hashCode();
        result = 31 * result + authorFirstName.hashCode();
        result = 31 * result + authorLastName.hashCode();
        return result;
    }

}
