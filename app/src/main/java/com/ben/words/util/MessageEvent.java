package com.ben.words.util;

public class MessageEvent {
    /*public static final int MULTI_SELECTED_MODE_ON = 1;
    public static final int MULTI_SELECTED_MODE_OFF = 2;
    public static final int ON_BACK_PRESSED = 3;
    public static final int ITEMS_IS_PURCHASED = 4;
    public static final int PAGE_CHANGE_STATE = 5;
    public static final int SELECT_ALL_ITEMS = 6;*/

    public static final int ADD_NEW_ITEM = 1;
    public static final int ADD_NEW_ITEM_IS_ERROR = 2;
    public static final int ON_BACK_PRESSED = 3;
    public static final int ITEMS_IS_PURCHASED = 4;
    public static final int PAGE_CHANGE_STATE = 5;
    public static final int SELECT_ALL_ITEMS = 6;

    public final int msg;

    public MessageEvent(int message) {
        this.msg = message;
    }
}
