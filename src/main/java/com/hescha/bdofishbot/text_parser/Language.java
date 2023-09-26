package com.hescha.bdofishbot.text_parser;

public enum Language {
    ENG, RUS;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}