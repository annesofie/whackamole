package com.whackamole.game.model;


public enum Theme {

    PRESIDENTIAL (0, "presidential/"),
    KARDASHIAN (1, "kardashian/");

    private final String path;
    private final int id;

    Theme(int id, String path) {
        this.path = path;
        this.id = id;
    }

    public static Theme getThemeOnThemeId(int id) {
        for(Theme theme : Theme.values()) {
            if(theme.id == id) {
                return theme;
            }
        }
        throw new IllegalArgumentException("No theme found on that id.");
    }

    public String path() {
        return this.path;
    }

    public String idAsString() {
        return Integer.toString(this.id);
    }

    public int getId() {
        return this.id;
    }

}
