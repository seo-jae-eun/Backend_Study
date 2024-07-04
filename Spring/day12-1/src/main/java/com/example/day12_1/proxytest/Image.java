package com.example.day12_1.proxytest;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface Image {
    BufferedImage getImage() throws IOException;
}
