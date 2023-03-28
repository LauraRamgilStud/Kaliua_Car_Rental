package com.example.kaliuacarrental.repository;
import com.example.kaliuacarrental.model.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {

    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String UID;
    @Value("${spring.datasource.password}")
    private String PWD;

    public List<Car> getAll(){
        List<Car> carList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM car";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next()){
                String carLicense = resultSet.getString(1);
                String carStatus = resultSet.getString(2);
                String carBrand = resultSet.getString(3);
                String carModel = resultSet.getString(4);
                String carFuelType = resultSet.getString(5);
                Date carRegistrationDate = resultSet.getDate(6);
                int carKmDriven = resultSet.getInt(7);
                int groupID = resultSet.getInt(8);

                Car car = new Car(carLicense, carStatus, carBrand, carModel, carFuelType, carRegistrationDate, carKmDriven, groupID);
                carList.add(car);
                System.out.println(car);
            }

        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return carList;
    }

    public Car findCarById(String id){
        Car car = new Car();
        //SQL query
        final String FIND_QUERY = "SELECT * FROM car WHERE car_license = ?";
        try{
            //connect to db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_QUERY);

            //set parameters
            preparedStatement.setString(1, id);

            //execute statement
            ResultSet resultSet = preparedStatement.executeQuery();

            //Get car from result-set
            if(resultSet.next()) {
                String carStatus = resultSet.getString(2);
                String carBrand = resultSet.getString(3);
                String carModel = resultSet.getString(4);
                String carFuelType = resultSet.getString(5);
                Date carRegistrationDate = resultSet.getDate(6);
                int carKmDriven = resultSet.getInt(7);
                int groupID = resultSet.getInt(8);

                car.setCarLicense(id);
                car.setCarStatus(carStatus);
                car.setCarBrand(carBrand);
                car.setCarModel(carModel);
                car.setCarFuelType(carFuelType);
                car.setCarRegistrationDate(carRegistrationDate);
                car.setCarKmDriven(carKmDriven);
                car.setGroupID(groupID);
            }

        }catch (SQLException e){
            System.out.println("Could not find car");
            e.printStackTrace();
        }
        return car;
    }

    public void updateCar(Car car){
        //SQL Statement
        final String UPDATE_QUERY = "UPDATE car SET car_status = ?, car_km_driven = ? WHERE car_license = ?";

        try{
            // Connect db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //Set parameters
            preparedStatement.setString(1, car.getCarStatus());
            preparedStatement.setInt(2, car.getCarKmDriven());
            preparedStatement.setString(3, car.getCarLicense());


            //Execute update/query
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addCar(Car car){
        try{
            //connect to db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO car(car_license, car_status, car_brand, car_model, car_fuel_type, car_registration_date, car_km_driven, group_id) VALUES  (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributes in prepared statement
            preparedStatement.setString(1, car.getCarLicense());
            preparedStatement.setString(2, car.getCarStatus());
            preparedStatement.setString(3, car.getCarBrand());
            preparedStatement.setString(4, car.getCarModel());
            preparedStatement.setString(5, car.getCarFuelType());
            preparedStatement.setDate(6, car.getCarRegistrationDate());
            preparedStatement.setInt(7, car.getCarKmDriven());
            preparedStatement.setInt(8, car.getGroupID());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not create product");
            e.printStackTrace();
        }
    }

    public void deleteById(String id){
        //SQL query
        final String DELETE_QUERY = "DELETE FROM car WHERE car_license = ?";
        try{
            //connect to db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);

            //set parameters
            preparedStatement.setString(1, id);

            //execute statement
            preparedStatement.executeUpdate();


        }catch (SQLException e){
            System.out.println("Could not find car");
            e.printStackTrace();
        }
    }
}