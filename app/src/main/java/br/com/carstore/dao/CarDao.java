package br.com.carstore.dao;

import br.com.carstore.model.Car;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarDao {

    public void createCar(Car car) {

        String SQL = "INSERT INTO CAR (NAME) VALUES (?)";

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, car.getName());
            preparedStatement.execute();

            System.out.println("Sucesso ao cadastrar o carro no DB!");


        } catch (Exception e) {

            System.out.println("Falha ao inserir o carro no DB!");

        }

    }

    public List<Car> findAllCars() {

        String SQL = "SELECT * FROM CAR";

        ArrayList<Car> cars = new ArrayList<>();

        try {

            Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "sa");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                String id = resultSet.getString("ID");
                String name = resultSet.getString("NAME");

                Car car = new Car();
                car.setName(name);

                cars.add(car);

            }

            System.out.println("Sucesso ao consultar os carros no DB!");

            return cars;

        } catch (Exception e) {

            System.out.println("Erro ao consultar os carros do DB!");

            return Collections.emptyList();

        }

    }

}
