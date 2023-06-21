package com.example.infomatch.gamePlay;

public class Card {
    private String content;
    private boolean isFlip;
    public Card(String content,boolean isFlip){
        setContent(content);
        setISFlip(isFlip);
    }

    public String getContent() {
        return content;
    }

    public boolean isFlip() {
        return isFlip;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setISFlip(boolean flip) {
        isFlip = flip;
    }
}
