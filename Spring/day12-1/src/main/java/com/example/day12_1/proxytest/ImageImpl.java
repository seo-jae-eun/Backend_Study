package com.example.day12_1.proxytest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageImpl implements Image {
    private BufferedImage bufferedImage;

    public ImageImpl(String path) throws IOException {
        File imageFile = new File(path);
        this.bufferedImage = ImageIO.read(imageFile);
    }

    @Override
    public BufferedImage getImage() {
        return this.bufferedImage;
    }
}