package com.ben.words.data.model;

public enum PartOfSpeech {

    NOUN("noun", "существительное"), PRONOUN("pronoun", "местоимение"), VERB("verb", "глагол"),
    ADJECTIVE("adjective", "прилагательное"), ADVERB("adverb", "наречие"), PREPOSITION("preposition", "предлог"),
    CONJUNCTION("conjunction", "союз"), INTERJECTION("interjection", "междометие");

    private String value;
    private String translate;

    PartOfSpeech(String value, String translate) {
        this.value = value;
        this.translate = translate;
    }

    public String getValue() {
        return value;
    }

    public String getTranslate() {
        return translate;
    }
}
