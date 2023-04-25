package Controller;
import dao.DaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        System.out.println("BOOK MANAGEMENT SYSTEM");

        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DaoImpl daoImpl=new DaoImpl();

        boolean flag = true;
        while (flag) {
            System.out.println("1.Insert Animal ");
            System.out.println("5.Insert Zoo ");
            System.out.println("3.Update Animal");
            System.out.println("4.Delete Animal ");
            System.out.println("5.Show Animal");
            System.out.println("6.Insert AnimalZoowise  ");
            System.out.println("7.All Animal who age greter than enter value");
            System.out.println("8.All Animals whos catagory is: ");
            System.out.println("9.All Animals date range");
            System.out.println("10.All book according to publisher_name");

            int choice = scanner.nextInt();
            switch (choice) {

                case 1:
                    daoImpl.inserAnimal();
                    break;
                case 2:
                    daoImpl.insertZoo();
                    break;
                case 3:
                    System.out.println("Enter AnimlId to update");
                    daoImpl.updateAnimal(Integer.parseInt(br.readLine()));
                    break;
                case 4:
                    System.out.println("Enter AnimlId to delete");
                    daoImpl.deleteAnimal(Integer.parseInt(br.readLine()));
                    break;
                case 5:
                    System.out.println("Enter AnimlId to show");
                    daoImpl.ShowAnimal(Integer.parseInt(br.readLine()));
                    break;
                case 6:
                    daoImpl.insertAinmalandZoo();
                    break;
                case 7:
                    System.out.println("Enter Animal Age above you wnat");
                    daoImpl.printAnimalsByAgeGreaterThan4(Integer.parseInt(br.readLine()));
                    break;
                case 8:
                    System.out.println("Enter Catogory");
                    daoImpl.printAnimalsByCatogory(br.readLine());
                    break;
                case 9:
                    daoImpl.printAnimalsByEnteredDateRange();
                    break;
                case 10:
                    daoImpl.insert();
                    break;
                case 0:
                    flag = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
