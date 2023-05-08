package book;

public class Book {
    private final String name;
    private final String author;
    private final String createdDate;
    private final int pages;




    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return name+";"+author+";"+createdDate+";"+pages;
    }

    public Book(Builder builder){
        this.author=builder.author;
        this.name=builder.name;
        this.createdDate=builder.createdDate;
        this.pages=builder.pages;
    }

    public Builder builder(){
        return new Builder(this.author, this.name);
    }

    public Builder toBuilder(){
        return builder()
                .setPages(pages)
                .setCreatedDate(createdDate);
    }

    public static class Builder{
        public String name;
        public String author;

        public String createdDate="1970-01-01";
        public int pages=100;

        public Builder (String name, String author){
            this.name=name;
            this.author=author;
        }

        public Builder setCreatedDate(String createdDate){
            this.createdDate=createdDate;
            return this;
        }

        public Builder setPages(int pages){
            this.pages=pages;
            return this;
        }

        public  Book build(){
            return new Book(this);
        }


    }
}
