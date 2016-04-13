package sample;

/**
 * Created by NoRFolf on 13.03.2016.
 */
public class BookModel {
    private String nameBook;
    private String author;
    private String athrName;
    private String athrSurName;
    private String athrSecndName;
    private String publisher;
    private Integer numberBook;
    private Integer edition;
    private Integer all;

    BookModel(){};

    BookModel(String nB, String auth, String publ, Integer numbB, Integer edit, Integer all){
        nameBook = nB;
        author = auth;
        publisher = publ;
        numberBook = numbB;
        edition = edit;
        this.all = all;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String athrName, String athrSecndName, String athrSurName) {
        this.author = athrName + " " + athrSecndName + " " + athrSurName;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAthrName() {
        return athrName;
    }

    public void setAthrName(String athrName) {
        this.athrName = athrName;
    }

    public String getAthrSurName() {
        return athrSurName;
    }

    public void setAthrSurName(String athrSurName) {
        this.athrSurName = athrSurName;
    }

    public String getAthrSecndName() {
        return athrSecndName;
    }

    public void setAthrSecndName(String athrSecndName) {
        this.athrSecndName = athrSecndName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getNumberBook() {
        return numberBook;
    }

    public void setNumberBook(Integer numberBook) {
        this.numberBook = numberBook;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
