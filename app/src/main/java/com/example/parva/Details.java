package com.example.parva;

public class Details {
    private String post_title;
    private String post_thumbnail;
    private String post_author_name;
    private String post_description;
    private String post_type_title;
    private String community_title;

    public Details(String post_title, String post_thumbnail, String post_author_name, String post_description, String post_type_title, String community_title) {
        this.post_title = post_title;
        this.post_thumbnail = post_thumbnail;
        this.post_author_name = post_author_name;
        this.post_description = post_description;
        this.post_type_title = post_type_title;
        this.community_title = community_title;
    }

    public String getCommunity_title() {
        return community_title;
    }

    public void setCommunity_title(String community_title) {
        this.community_title = community_title;
    }

    public Details() {
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_thumbnail() {
        return post_thumbnail;
    }

    public void setPost_thumbnail(String post_thumbnail) {
        this.post_thumbnail = post_thumbnail;
    }

    public String getPost_author_name() {
        return post_author_name;
    }

    public void setPost_author_name(String post_author_name) {
        this.post_author_name = post_author_name;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getPost_type_title() {
        return post_type_title;
    }

    public void setPost_type_title(String post_type_title) {
        this.post_type_title = post_type_title;
    }
}
