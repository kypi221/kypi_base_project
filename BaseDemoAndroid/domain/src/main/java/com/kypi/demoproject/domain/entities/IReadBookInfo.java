package com.kypi.demoproject.domain.entities;

/**
 * Created by Khoa on 8/11/2017.
 */

public class IReadBookInfo {
    public boolean full;
    public int totalChapter;
    public int bookId = -1;
    public long updateDate;
    public String content;

    public int views;
    public String userId;
    public long createDate;
    public String title;
    public String avatar;

    public String banner;

    public long time;
    public int inBookCase = -1;
    public int words;

    public int price;
    public int chargeType; /** @See #com.gs2.iread.domain.enums.books.ChargeType*/
    public boolean isBought;
    public int score;

    public int ban = -1;
    public String slug; // Dùng cho Share
    public boolean isOffline = false; // Dùng cho tính năng offline, server ko có
    public boolean isDownloadAll = false; // Dùng cho tính năng offline, để biết sách này có download all chưa
    public long memberExpireTime = -1; // Dùng cho tính năng offline ( Sách Hội Viên )

    public String review; // Dùng cho Iread Tuyển chọn

    public IReadBookInfo(){

    }
    public IReadBookInfo(IReadBookInfo bookInfo) {
        if(bookInfo == null){
            return ;
        }

        this.bookId = bookInfo.bookId;
        this.full = bookInfo.full;
        this.totalChapter = bookInfo.totalChapter;
        this.updateDate = bookInfo.updateDate;
        this.content = bookInfo.content;

        this.views = bookInfo.views;
        this.userId = bookInfo.userId;
        this.createDate = bookInfo.createDate;
        this.title = bookInfo.title;
        this.avatar = bookInfo.avatar;

        this.banner = bookInfo.banner;

        this.time = bookInfo.time;
        this.inBookCase = bookInfo.inBookCase;
        this.words = bookInfo.words;

        this.price = bookInfo.price;
        this.chargeType = bookInfo.chargeType;
        this.isBought = bookInfo.isBought;
        this.score = bookInfo.score;

        this.ban = bookInfo.ban;
        this.slug = bookInfo.slug;
        this.isOffline = bookInfo.isOffline;
        this.isDownloadAll = bookInfo.isDownloadAll;
        this.memberExpireTime = bookInfo.memberExpireTime;

        this.review = bookInfo.review;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj instanceof IReadBookInfo){
            return ((IReadBookInfo) obj).bookId == bookId;
        }
        return super.equals(obj);
    }
}
