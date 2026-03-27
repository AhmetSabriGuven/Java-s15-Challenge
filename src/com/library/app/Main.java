package com.library.app;

import com.library.model.Book;
import com.library.model.Journals;
import com.library.model.Magazines;
import com.library.model.MemberRecord;
import com.library.model.Reader;
import com.library.model.StudyBooks;
import com.library.service.Librarian;
import com.library.service.Library;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Library library = new Library();
        Librarian librarian = new Librarian("Mehmet", "1234");

        System.out.println("===== KÜTÜPHANECİ GİRİŞİ =====");

        boolean loginSuccess = false;
        int attempt = 3;

        while (attempt > 0) {
            System.out.print("Kullanıcı Adı: ");
            String inputName = scanner.nextLine();

            System.out.print("Şifre: ");
            String inputPassword = scanner.nextLine();

            if (librarian.getName().equals(inputName) && librarian.getPassword().equals(inputPassword)) {
                loginSuccess = true;
                System.out.println("Giriş başarılı.");
                break;
            } else {
                attempt--;
                System.out.println("Hatalı giriş. Kalan deneme hakkı: " + attempt);
            }
        }

        if (!loginSuccess) {
            System.out.println("Giriş başarısız. Program kapatılıyor.");
            return;
        }

        Map<Long, Reader> readerMap = new HashMap<>();
        Map<Long, MemberRecord> memberRecordMap = new HashMap<>();

        Reader r1 = new Reader("Ahmet");
        MemberRecord m1 = new MemberRecord(1L, "Student", "2024", 5, "Ahmet", "Ankara", "5551112233");
        readerMap.put(1L, r1);
        memberRecordMap.put(1L, m1);
        library.addReader(r1);

        Reader r2 = new Reader("Çağatay");
        MemberRecord m2 = new MemberRecord(2L, "Student", "2024", 5, "Çağatay", "İstanbul", "5554445566");
        readerMap.put(2L, r2);
        memberRecordMap.put(2L, m2);
        library.addReader(r2);

        Reader r3 = new Reader("Metehan");
        MemberRecord m3 = new MemberRecord(3L, "Faculty", "2023", 5, "Metehan", "İzmir", "5557778899");
        readerMap.put(3L, r3);
        memberRecordMap.put(3L, m3);
        library.addReader(r3);

        Long activeMemberId = 1L;

        library.newBook(new StudyBooks(1L, "Clean Code", "Robert C. Martin", 250, "1", LocalDate.now()));
        library.newBook(new StudyBooks(2L, "Effective Java", "Joshua Bloch", 300, "3", LocalDate.now()));
        library.newBook(new StudyBooks(3L, "Head First Java", "Kathy Sierra", 220, "2", LocalDate.now()));
        library.newBook(new StudyBooks(4L, "Spring in Action", "Craig Walls", 280, "6", LocalDate.now()));
        library.newBook(new Magazines(5L, "National Geographic", "National Geographic Society", 90, "2026 Mart", LocalDate.now()));
        library.newBook(new Magazines(6L, "Scientific American", "Springer Nature", 85, "2026 Nisan", LocalDate.now()));
        library.newBook(new Magazines(7L, "PC Magazine", "Ziff Davis", 70, "2026 Şubat", LocalDate.now()));
        library.newBook(new Journals(8L, "Nature", "Nature Portfolio", 400, "2026 Issue 1", LocalDate.now()));
        library.newBook(new Journals(9L, "Science", "AAAS", 380, "2026 Issue 2", LocalDate.now()));
        library.newBook(new Journals(10L, "IEEE Transactions on Software Engineering", "IEEE", 450, "2026 Volume 52", LocalDate.now()));

        while (true) {

            Reader activeReader = readerMap.get(activeMemberId);
            MemberRecord activeRecord = memberRecordMap.get(activeMemberId);

            System.out.println("\n===== KÜTÜPHANE =====");
            System.out.println("Aktif Kullanıcı: " + activeRecord.getName() + " | ID: " + activeMemberId);
            System.out.println("1- Kitap Ekle");
            System.out.println("2- Kitap Sil");
            System.out.println("3- Tüm Kitapları Listele");
            System.out.println("4- Kitap Ara");
            System.out.println("5- Yeni Üye Ekle");
            System.out.println("6- Aktif Kullanıcıyı Değiştir");
            System.out.println("7- Kitap Ödünç Ver");
            System.out.println("8- Kitap İade Al");
            System.out.println("9- Aktif Kullanıcının Kitapları");
            System.out.println("10- Ceza Hesapla");
            System.out.println("11- Fatura Oluştur");
            System.out.println("12- Kullanıcıları Listele");
            System.out.println("13- Kitap Satın Al");
            System.out.println("0- Çıkış");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {

                case 1:
                    System.out.println("1- StudyBooks  2- Magazines  3- Journals");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("ID: ");
                    Long id = scanner.nextLong();
                    scanner.nextLine();

                    if (library.getBooks().containsKey(id)) {
                        System.out.println("ID zaten var.");
                        break;
                    }

                    System.out.print("Ad: ");
                    String name = scanner.nextLine();

                    System.out.print("Yazar: ");
                    String author = scanner.nextLine();

                    System.out.print("Fiyat: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Baskı: ");
                    String edition = scanner.nextLine();

                    LocalDate date = LocalDate.now();

                    Book newBook = null;

                    if (type == 1) {
                        newBook = new StudyBooks(id, name, author, price, edition, date);
                    } else if (type == 2) {
                        newBook = new Magazines(id, name, author, price, edition, date);
                    } else if (type == 3) {
                        newBook = new Journals(id, name, author, price, edition, date);
                    } else {
                        System.out.println("Geçersiz kitap türü.");
                    }

                    if (newBook != null) {
                        library.newBook(newBook);
                        System.out.println("Eklendi.");
                    }
                    break;

                case 2:
                    System.out.print("Silinecek ID: ");
                    Long deleteId = scanner.nextLong();
                    scanner.nextLine();

                    if (library.getBooks().containsKey(deleteId)) {
                        library.getBooks().remove(deleteId);
                        System.out.println("Silindi.");
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 3:
                    library.showBook();
                    break;

                case 4:
                    System.out.print("ID: ");
                    Long searchId = scanner.nextLong();
                    scanner.nextLine();

                    Book found = librarian.searchBook(library, searchId);

                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Bulunamadı.");
                    }
                    break;

                case 5:
                    System.out.print("Üye ID: ");
                    Long mid = scanner.nextLong();
                    scanner.nextLine();

                    if (readerMap.containsKey(mid) || memberRecordMap.containsKey(mid)) {
                        System.out.println("Bu ID zaten kayıtlı.");
                        break;
                    }

                    System.out.print("Ad: ");
                    String mname = scanner.nextLine();

                    System.out.print("Tür (Student/Faculty): ");
                    String mtype = scanner.nextLine();

                    System.out.print("Üyelik Tarihi: ");
                    String membershipDate = scanner.nextLine();

                    System.out.print("Adres: ");
                    String address = scanner.nextLine();

                    System.out.print("Telefon: ");
                    String phone = scanner.nextLine();

                    Reader r = new Reader(mname);
                    MemberRecord mr = new MemberRecord(mid, mtype, membershipDate, 5, mname, address, phone);

                    readerMap.put(mid, r);
                    memberRecordMap.put(mid, mr);
                    library.addReader(r);

                    System.out.println("Üye eklendi.");
                    break;

                case 6:
                    System.out.println("--- KULLANICILAR ---");
                    for (Map.Entry<Long, MemberRecord> entry : memberRecordMap.entrySet()) {
                        System.out.println("ID: " + entry.getKey() + " | Ad: " + entry.getValue().getName());
                    }

                    System.out.print("Aktif yapılacak üye ID: ");
                    Long newActiveId = scanner.nextLong();
                    scanner.nextLine();

                    if (readerMap.containsKey(newActiveId) && memberRecordMap.containsKey(newActiveId)) {
                        activeMemberId = newActiveId;
                        System.out.println("Aktif kullanıcı değiştirildi: " + memberRecordMap.get(activeMemberId).getName());
                    } else {
                        System.out.println("Geçersiz üye ID.");
                    }
                    break;

                case 7:
                    System.out.print("Kitap ID: ");
                    Long kid = scanner.nextLong();
                    scanner.nextLine();

                    librarian.issueBook(library, kid, activeReader, activeRecord);
                    break;

                case 8:
                    System.out.print("Kitap ID: ");
                    Long ridBook = scanner.nextLong();
                    scanner.nextLine();

                    librarian.returnBook(library, ridBook, activeReader, activeRecord);
                    break;

                case 9:
                    activeReader.showBooks();
                    break;

                case 10:
                    System.out.print("Gecikme günü: ");
                    int days = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ceza: " + librarian.calculateFine(days));
                    break;

                case 11:
                    System.out.print("Kitap ID: ");
                    Long billId = scanner.nextLong();
                    scanner.nextLine();

                    Book billBook = librarian.searchBook(library, billId);

                    if (billBook != null) {
                        System.out.println("Fatura: " + librarian.createBill(billBook));
                    } else {
                        System.out.println("Kitap bulunamadı.");
                    }
                    break;

                case 12:
                    System.out.println("--- KULLANICILAR ---");
                    for (Map.Entry<Long, MemberRecord> entry : memberRecordMap.entrySet()) {
                        System.out.println("ID: " + entry.getKey() + " | Ad: " + entry.getValue().getName());
                    }
                    break;

                case 13:
                    System.out.print("Satın alınacak kitap ID: ");
                    Long purchaseId = scanner.nextLong();
                    scanner.nextLine();

                    Book purchaseBook = librarian.searchBook(library, purchaseId);

                    if (purchaseBook == null) {
                        System.out.println("Kitap bulunamadı.");
                        break;
                    }

                    if (!purchaseBook.isAvailable()) {
                        System.out.println("Kitap müsait değil.");
                        break;
                    }

                    activeRecord.payBill(purchaseBook.getPrice());
                    activeReader.purchaseBook(purchaseBook);
                    System.out.println("Kitap satın alındı.");
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Hatalı seçim!");
            }
        }
    }
}