package dao;

import java.io.IOException;
import java.text.ParseException;

public interface Dao {
    void inserAnimal() throws IOException, ParseException;


    void updateAnimal(int id) throws IOException ;



    void deleteAnimal(int id) throws IOException;

    void ShowAnimal(int id) throws IOException;

}
