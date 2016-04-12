package com.whackamole.game.model;

/**
 * Created by Lars on 12/04/16.
 */
public enum MoleImage {


    TRUMP(0, "p1.png"),
    KASICH(1, "p2.png"),
    CRUZ(2, "p3.png"),
    CRUZ2(3, "p4.png"),
    SANDERS(4, "p5.png"),
    WEST(5, "p6.png");


    private final int id;
    private final String filename;

    MoleImage(int id, String filename) {
        this.id = id;
        this.filename = filename;
    }

    public static String getFileNameOnImageId(int id) {
        for(MoleImage img : MoleImage.values()) {
            if(img.getId() == id) {
                return img.getFilename();
            }
        }
        return null;
    }

    public int getId() {
        return this.id;
    }

    public String getFilename() {
        return this.filename;
    }




}
