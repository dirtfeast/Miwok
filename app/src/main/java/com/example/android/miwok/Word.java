package com.example.android.miwok;

public class Word {

    private String mMiwokWord;
    private String mEnglishWord;
    private int mImageResourceId;

    // Constructor setter method
    // @params strings miwokWord and englishWord
    // Sets private strings mMiwokWord and mEnglishWord
    public Word(String miwokWord, String englishWord) {
        mMiwokWord = miwokWord;
        mEnglishWord = englishWord;
    } // Close method Word()

    public Word(String miwokWord, String englishWord, int imageResourceId) {
        mMiwokWord = miwokWord;
        mEnglishWord = englishWord;
        mImageResourceId = imageResourceId;
    } // Close method Word()

    // Getter method to retrieve private string mMiwokWord
    public String getmMiwokWord() {
        return mMiwokWord;
    }

    // Getter method to retrieve private string mEnglishWord
    public String getmEnglishWord() {
        return mEnglishWord;
    }

    // Getter method to retrieve private string mIcon;
    public int getmImageResourceId() { return mImageResourceId; }

} // Close class Word
