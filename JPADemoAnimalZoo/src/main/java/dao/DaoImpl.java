package dao;

import model.Animal;
import model.Zoo;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

public class DaoImpl implements Dao{
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("rt");

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public void inserAnimal() throws IOException, ParseException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter Animal name");
        String animalname=br.readLine();

        System.out.println("Enter date of AnimalEntierd (yyyy-MM-dd):");
        String dateString = br.readLine();
        Date datepulish = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

        System.out.println("enter AnimalAge ");
        int age_animal= Integer.parseInt(br.readLine());

        System.out.println("enter Animal Catogory ");
        String AnimalCatogry= br.readLine();

        Animal animal=new Animal(animalname,datepulish,age_animal,AnimalCatogry);

        entityManager.persist(animal);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void insertZoo() throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("enter Zoo name");
        String libname=br.readLine();

        Zoo library = new Zoo();
        library.setZoo_name(libname);

        entityManager.persist(library);

        entityManager.getTransaction().commit();
        entityManager.close();

    }


    @Override
    public void updateAnimal(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Animal student = entityManager.find(Animal.class, id);

        System.out.println("Enter AnimalId name you want to update");
        String name = br.readLine();

        student.setAnimal_name(name);

        entityManager.merge(student);

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public void deleteAnimal(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Animal product = entityManager.find(Animal.class,id);
        entityManager.remove(product);

        entityManager.getTransaction().commit();
        entityManager.close();


    }

    @Override
    public void ShowAnimal(int id) throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Animal product = entityManager.find(Animal.class,id);


        System.out.println(product);
        entityManager.persist(product);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
    public void insertAinmalandZoo() throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Enter Library Id in which you when add book ");
        int userFetchId = Integer.parseInt(br.readLine());
        Zoo user = entityManager.find(Zoo.class, userFetchId);

        List<Animal> productList = new ArrayList<>();
        System.out.println("Enter Number Animal to add ");
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {

            System.out.println("Enter  Animal Id ");

            int bid = Integer.parseInt(br.readLine());
            Animal animal = entityManager.find(Animal.class, bid);

            animal.setZoo(user);

            productList.add(animal);
            entityManager.persist(animal);

        }
        System.out.println("Add in list");
        user.setAnimalList(productList);
        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
    public void printAnimalsByAgeGreaterThan4(int age) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> criteriaQuery = criteriaBuilder.createQuery(Animal.class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        criteriaQuery.select(animalRoot);
        criteriaQuery.where(criteriaBuilder.greaterThan(animalRoot.<Comparable>get("animal_age"), age));

        TypedQuery<Animal> typedQuery = entityManager.createQuery(criteriaQuery);

        List<Animal> animals = typedQuery.getResultList();

        for (Animal animal : animals) {
            System.out.println(animal.getAnimal_name()+":"+animal.getAnimal_id()+":"+animal.getAnimal_age());
        }
         entityManager.getTransaction().commit();
        entityManager.close();

    }
    public void printAnimalsByCatogory(String catogoryName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
        Root<Animal> bookRoot = cq.from(Animal.class);

        cq.select(bookRoot).where(cb.equal(bookRoot.get("animal_catogery"), catogoryName));
        TypedQuery<Animal> query = entityManager.createQuery(cq);

        List<Animal> resultList = query.getResultList();
        for (Animal book : resultList) {
            System.out.println("Animal ID: " + book.getAnimal_id());
            System.out.println("Animal Name: " + book.getAnimal_name());
            System.out.println("Catogoty Name: " + book.getAnimal_age());
            System.out.println("ZooInWhichAnimal: " + book.getZoo());
            System.out.println("--------------------");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void printAnimalsByEnteredDateRange() throws IOException {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        System.out.println("Enter Range for Starting date (yyyy-mm-dd)");
        java.sql.Date StartingDate = java.sql.Date.valueOf(br.readLine());

        System.out.println("Enter Range for Ending date (yyyy-mm-dd)");
        java.sql.Date EndRange = java.sql.Date.valueOf(br.readLine());

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<Animal> animalRoot = criteriaQuery.from(Animal.class);

        criteriaQuery.multiselect(animalRoot.get("animal_name"), animalRoot.get("animal_catogery"));
        criteriaQuery.where(criteriaBuilder.between(animalRoot.<Comparable>get("animail_enitredDate"),StartingDate,EndRange));


        TypedQuery<Object[]> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object[]> results = typedQuery.getResultList();

        for (Object[] result : results) {
            System.out.println("Animal Name: " + result[0] + ", Category: " + result[1]);
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
    public void insert() throws IOException, ParseException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        System.out.println("enter name of zoo");
        String libraryName = br.readLine();
        Zoo zoo = new Zoo();
        zoo.setZoo_name(libraryName);
        entityManager.persist(zoo);
        int choice;
        do {
            System.out.println("enter 1 to enter animal\n0 to exit");
            choice = Integer.parseInt(br.readLine());
            if (choice == 1) {
                System.out.println("enter Animal name");
                String animalname=br.readLine();

                System.out.println("Enter date of AnimalEntierd (yyyy-MM-dd):");
                String dateString = br.readLine();
                Date datepulish = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

                System.out.println("enter AnimalAge ");
                int age_animal= Integer.parseInt(br.readLine());

                System.out.println("enter Animal Catogory ");
                String AnimalCatogry= br.readLine();

                Animal animal=new Animal(animalname,datepulish,age_animal,AnimalCatogry);


            }
        }while(choice!=0);
        entityManager.getTransaction().commit();
        entityManager.close();

    }




}
