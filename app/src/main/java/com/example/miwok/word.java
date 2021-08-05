package com.example.miwok;

import android.content.Context;
import android.view.View;

public class word{
    /** Default translation for the word */
    private String mDefaultTranslation;

    /** Miwok translation for the word */
    private String mMiwokTranslation;

    /** Image resource ID for the word */

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** Constant value that represents no image was provided for this word */
    private static final int NO_IMAGE_PROVIDED = -1;


    /** Audio resource for the word */

    private int mMediaResourceId;

    @Override
    public String toString() {
        return "word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mMediaResourceId=" + mMediaResourceId +
                '}';
    }

    /**
     * create a new Word constructor
     *
     * @param defaultTranslation is the word in a language that the user is already familiar with
     *                           (such as English)
     * @param miwokTranslation is the word in the Miwok language
     *
     * @param mediaResourceId is the resource id of the image to be displayed on the ListView
     */
    public word(String defaultTranslation, String miwokTranslation,int mediaResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mMediaResourceId = mediaResourceId;
    }

    public word(String defaultTranslation, String miwokTranslation,int imgResourceId,int mediaResourceId){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceId = imgResourceId;
        mMediaResourceId = mediaResourceId;
    }


    /**
     * Get the default translation of the word.
     */
    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    /**
     * Get the Miwok translation of the word.
     */
    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    /**
     * Get the image resource id.
     */
    public int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;

    }

    /**
     * get the media resource id..
     */
    public int getMediaResourceId(){
        return mMediaResourceId;
    }
}
