package com.zym.memorymasterserver.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * Created by 12390 on 2019/3/2.
 */
@Table("mm_book_information")
public class BookInformation {
    @Id
    private Integer bookInfromationId;
    @Column("book_name")
    private String bookName;
    @Column("book_type")
    private Integer bookType;
    @Column("book_records_num")
    private Integer bookRecordsNum;
    @Column("book_author")
    private String bookAuthor;
    @Column("book_path")
    private String bookPath;
    @Column("book_img_src")
    private String bookImgSrc;
    @Column("book_price")
    private Integer bookPrice;
    @Column("book_buied_num")
    @Default("0")
    private Integer bookLoadingNum;
    @Column("book_chapter_num")
    private String bookChapterNum;
    @Column("book_description")
    private String bookDescription;

    public Integer getBookInfromationId() {
        return bookInfromationId;
    }

    public void setBookInfromationId(Integer bookInfromationId) {
        this.bookInfromationId = bookInfromationId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getBookType() {
        return bookType;
    }

    public void setBookType(Integer bookType) {
        this.bookType = bookType;
    }

    public Integer getBookRecordsNum() {
        return bookRecordsNum;
    }

    public void setBookRecordsNum(Integer bookRecordsNum) {
        this.bookRecordsNum = bookRecordsNum;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPath() {
        return bookPath;
    }

    public void setBookPath(String bookPath) {
        this.bookPath = bookPath;
    }

    public String getBookImgSrc() {
        return bookImgSrc;
    }

    public void setBookImgSrc(String bookImgSrc) {
        this.bookImgSrc = bookImgSrc;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Integer bookPrice) {
        this.bookPrice = bookPrice;
    }

    public Integer getBookLoadingNum() {
        return bookLoadingNum;
    }

    public void setBookLoadingNum(Integer bookLoadingNum) {
        this.bookLoadingNum = bookLoadingNum;
    }

    public String getBookChapterNum() {
        return bookChapterNum;
    }

    public void setBookChapterNum(String bookChapterNum) {
        this.bookChapterNum = bookChapterNum;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }
}
