package com.example.abednego.nekkta.data_holder;

/**
 * Created by abedkiloo on 8/29/17.
 */

public class FeedsDataSet {
    String feed_header_text, feed_header_description, feed_timer, feed_comments_counter, feed_like_counter;
    int feed_image,header_icon;

    public String getFeed_header_text() {
        return feed_header_text;
    }

    public void setFeed_header_text(String feed_header_text) {
        this.feed_header_text = feed_header_text;
    }

    public String getFeed_header_description() {
        return feed_header_description;
    }

    public void setFeed_header_description(String feed_header_description) {
        this.feed_header_description = feed_header_description;
    }

    public String getFeed_timer() {
        return feed_timer;
    }

    public void setFeed_timer(String feed_timer) {
        this.feed_timer = feed_timer;
    }

    public String getFeed_comments_counter() {
        return feed_comments_counter;
    }

    public void setFeed_comments_counter(String feed_comments_counter) {
        this.feed_comments_counter = feed_comments_counter;
    }

    public String getFeed_like_counter() {
        return feed_like_counter;
    }
    public int getFeed_image() {
        return feed_image;
    }

    public void setFeed_image(int feed_image) {
        this.feed_image = feed_image;
    }


    public void setFeed_like_counter(String feed_like_counter) {
        this.feed_like_counter = feed_like_counter;
    }

    public int getHeader_icon() {
        return header_icon;
    }

    public void setHeader_icon(int header_icon) {
        this.header_icon = header_icon;
    }

}
