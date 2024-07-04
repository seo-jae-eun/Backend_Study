package com.example.day12_1.proxytest;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ProxyImage implements Image {
    private String path;
    private ImageImpl image;

    public ProxyImage(String path) {
        this.path = path;
    }

    @Override
    public BufferedImage getImage() throws IOException {
        if(this.image == null) {
            this.image = new ImageImpl(path);
        }
        return this.image.getImage();
    }
}
