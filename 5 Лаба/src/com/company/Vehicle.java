package com.company;

import java.io.IOException;

public interface Vehicle {

    void output();
    void edit() throws IOException;

    String getStringBrand();
    char getBrand();
    String getColor();

    String toSave();
}
