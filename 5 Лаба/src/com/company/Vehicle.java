package com.company;

import java.io.IOException;
import java.util.List;

public interface Vehicle {

    void output();
    void edit() throws IOException;

    String getStringBrand();
    char getBrand();
    String getColor();
}
