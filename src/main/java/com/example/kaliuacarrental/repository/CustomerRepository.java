package com.example.kaliuacarrental.repository;

import com.example.kaliuacarrental.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String UID;
    @Value("${spring.datasource.password}")
    private String PWD;

    public List<Customer> getAll(){
        List<Customer> customerList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            Statement statement = connection.createStatement();
            final String SQL_QUERY = "SELECT * FROM customer";
            ResultSet resultSet = statement.executeQuery(SQL_QUERY);

            while (resultSet.next()){
                String license = resultSet.getString(1);
                Date licenseDate = resultSet.getDate(2);
                String name = resultSet.getString(3);
                String email = resultSet.getString(4);
                int phoneNumber = resultSet.getInt(5);
                String address = resultSet.getString(6);
                int zip = resultSet.getInt(7);
                String city = resultSet.getString(8);

                Customer customer = new Customer(license, licenseDate, name, email, phoneNumber, address, zip, city);
                customerList.add(customer);
                System.out.println(customer);
            }

        }
        catch(SQLException e)
        {
            System.out.println("Could not query database");
            e.printStackTrace();
        }
        return customerList;
    }

    public Customer findCustomerById(String id){
        Customer customer = new Customer();
        //SQL query
        final String FIND_QUERY = "SELECT * FROM customer WHERE cutomer_license = ?";
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
                Date licenseDate = resultSet.getDate(2);
                String name = resultSet.getString(3);
                String email = resultSet.getString(4);
                int phoneNumber = resultSet.getInt(5);
                String address = resultSet.getString(6);
                int zip = resultSet.getInt(7);
                String city = resultSet.getString(8);

                customer.setCustomerLicenseDate(licenseDate);
                customer.setCustomerName(name);
                customer.setCustomerEmail(email);
                customer.setCustomerPhoneNumber(phoneNumber);
                customer.setCustomerAddress(address);
                customer.setCustomerZip(zip);
                customer.setCustomerCity(city);
            }

        }catch (SQLException e){
            System.out.println("Could not find customer");
            e.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(Customer customer){
        //SQL Statement
        final String UPDATE_QUERY = "UPDATE customer SET customer_email = ?, customer_phone_number = ?, customer_address = ?, customer_zip = ?, customer_city = ? WHERE cutomer_license = ?";

        try{
            // Connect db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);

            //Prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);

            //Set parameters
            preparedStatement.setString(1, customer.getCustomerEmail());
            preparedStatement.setInt(2, customer.getCustomerPhoneNumber());
            preparedStatement.setString(3, customer.getCustomerAddress());
            preparedStatement.setInt(3, customer.getCustomerZip());
            preparedStatement.setString(3, customer.getCustomerCity());


            //Execute update/query
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void addCustomer(Customer customer){
        try{
            //connect to db
            Connection connection = DriverManager.getConnection(DB_URL, UID, PWD);
            final String CREATE_QUERY = "INSERT INTO customer(cutomer_license, customer_license_date, customer_name, customer_email, customer_phone_number, customer_address, customer_zip, customer_city) VALUES  (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_QUERY);

            //set attributes in prepared statement
            preparedStatement.setString(1, customer.getCustomerLicense());
            preparedStatement.setDate(2, customer.getCustomerLicenseDate());
            preparedStatement.setString(3, customer.getCustomerName());
            preparedStatement.setString(4, customer.getCustomerEmail());
            preparedStatement.setInt(5, customer.getCustomerPhoneNumber());
            preparedStatement.setString(6, customer.getCustomerAddress());
            preparedStatement.setInt(7, customer.getCustomerZip());
            preparedStatement.setString(8, customer.getCustomerCity());

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println("Could not create customer");
            e.printStackTrace();
        }
    }

    public void deleteById(String id){
        //SQL query
        final String DELETE_QUERY = "DELETE FROM customer WHERE cutomer_license = ?";
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
            System.out.println("Could not find customer");
            e.printStackTrace();
        }
    }
}
