package com.library.service;

import com.library.model.Book;
import com.library.model.MemberRecord;
import com.library.model.Reader;

public class Librarian {

    private String name;
    private String password;

    public Librarian(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Book searchBook(Library library, Long bookId) {
        return library.getBooks().get(bookId);
    }

    public boolean verifyMember(MemberRecord memberRecord) {
        return memberRecord != null && memberRecord.canBorrow();
    }

    public void issueBook(Library library, Long id, Reader reader, MemberRecord memberRecord) {
        if (!verifyMember(memberRecord)) {
            System.out.println("Üye doğrulanamadı veya kitap limiti dolu.");
            return;
        }

        library.lendBook(id, reader);
        memberRecord.incBookIssued();
    }

    public double calculateFine(int lateDays) {
        if (lateDays <= 0) {
            return 0;
        }
        return lateDays * 2.0;
    }

    public double createBill(Book book) {
        return book.getPrice();
    }

    public void returnBook(Library library, Long id, Reader reader, MemberRecord memberRecord) {
        Book book = library.getBooks().get(id);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (book.isAvailable()) {
            System.out.println("Kitap zaten kütüphanede.");
            return;
        }

        library.takeBackBook(id, reader);
        memberRecord.decBookIssued();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}