package com.example.javaprojet1;

import java.io.Serializable;

public class Dictionnaire implements Serializable {
    private static final long serialVersionUID = 1L;


    String  word_fr ,word_eng,type,exmple_fr,exmple_eng;
    int id;
    public Dictionnaire(int id, String word_fr , String word_eng, String type, String exmple_fr, String exmple_eng){
        this.id=id;
        this.exmple_fr=exmple_fr;
         this.exmple_eng=exmple_eng;
         this.word_fr=word_fr;
         this.type=type;
         this.word_eng=word_eng;
    }

    @Override
    public String toString() {
        return "Dictionnaire{" +
                "word_fr='" + word_fr + '\'' +
                ", word_eng='" + word_eng + '\'' +
                ", type='" + type + '\'' +
                ", exmple_fr='" + exmple_fr + '\'' +
                ", exmple_eng='" + exmple_eng + '\'' +
                ", id=" + id +
                '}';
    }

    public String getWord_fr() {
        return word_fr;
    }

    public String getWord_eng() {
        return word_eng;
    }

    public String getType() {
        return type;
    }

    public String getExmple_fr() {
        return exmple_fr;
    }

    public String getExmple_eng() {
        return exmple_eng;
    }

    public int getId() {
        return id;
    }
}
