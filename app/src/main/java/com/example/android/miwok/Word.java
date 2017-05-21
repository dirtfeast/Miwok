package com.example.android.miwok;

/**
 * Created by DanW on 5/21/2017.
 */

public class Word {

    private String mMiwokWord;
    private String mEnglishWord;

    // Constructor setter method
    // @params strings miwokWord and englishWord
    // Sets private strings mMiwokWord and mEnglishWord
    public Word(String miwokWord, String englishWord) {
        mMiwokWord = miwokWord;
        mEnglishWord = englishWord;
    } // Close method Word()

    // Getter method to retrieve private string mMiwokWord
    public String getmMiwokWord() {
        return mMiwokWord;
    }

    // Getter method to retrieve private string mEnglishWord
    public String getmEnglishWord() {
        return mEnglishWord;
    }

} // Close class Word
