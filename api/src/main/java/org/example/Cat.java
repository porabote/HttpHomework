package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {

    private final String id;
    private final Integer upvotes;
    private final String text;
    private final String type;
    private final String user;


    public Cat(
            @JsonProperty("id") String id,
            @JsonProperty("upvotes") Integer upvotes,
            @JsonProperty("text") String text,
            @JsonProperty("type") String type,
            @JsonProperty("user") String user
    ) {
        this.id = id;
        this.upvotes = upvotes;
        this.text = text;
        this.type = type;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Cat:" +
                "\n id=" + id +
                "\n upvotes=" + upvotes +
                "\n text=" + text;
    }

    public Integer getUpvotes() {
        return (this.upvotes == null) ? 0 : this.upvotes;
    }
}
