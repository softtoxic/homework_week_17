package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    @Test
    //1. Extract the limit
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    @Test
    //2. Extract the total
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("The value of total is : " + total);
    }

    @Test
    //3. Extract the name of 5th store
    public void extract5Store() {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of store is : " + name);
    }

    @Test
    //4. Extract the names of all the store
    public void extractAllStore() {
        List<String> allName = response.extract().path("data.name");
        System.out.println("The name of all store is : " + allName);
    }

    @Test
    //5. Extract the storeId of all the store
    public void extractStoreId() {
        List<String> allId = response.extract().path("data.id");
        System.out.println("The name of all store is : " + allId);
    }

    @Test
    //6. Print the size of the data list
    public void sizeOfData() {
        List<?> data = response.extract().path("data");
        int size = data.size();
        System.out.println("The size of data is : " + size);
    }

    @Test
    //7. Get all the value of the store where store name = St Cloud
    public void allValue() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("The size of data is : " + values);
    }

    @Test
    //8. Get the address of the store where store name = Rochester
    public void getAddress() {
        List<String> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("The address of all store is : " + address);
    }

    @Test
    //9. Get all the services of 8th store
    public void getAllServices() {
        List<String> allServices = response.extract().path("data[7].services");
        System.out.println("The all services of all store is : " + allServices);
    }

    @Test
    //10. Get storeServices of the store where service name = Windows Store
    public void getStoreServices() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data.findAll{it.services == 'Windows Store'}.services");
        System.out.println("The all services of store is : " + storeServices);
    }

    @Test
    //11. Get all the storeId of all the store
    public void getAllStoreId() {
        List<Integer> allId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("The Id of all store is " + allId);
    }

    @Test
    //12. Get id of all the store
    public void getId() {
        List<Integer> Id = response.extract().path("data.id");
        System.out.println("The Id of all store is " + Id);
    }

    @Test
    //13. Find the store names Where state = ND
    public void findStoreNameND() {
        List<String> name = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("The Id of all store is " + name);
    }

    @Test
    //14. Find the Total number of services for the store where store name = Rochester
    public void findNumberOfServices() {
        List<Integer> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        int number = services.size();
        System.out.println("The size of all services is : " + number);
    }

    @Test
    //15. Find the createdAt for all services whose name = “Windows Store”
    public void findCreatedAt() {
        List<HashMap<String, ?>> createdAt = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("All createdAt is " + createdAt);
    }

    @Test
    //16. Find the name of all services Where store name = “Fargo”
    public void findNameOfFargo() {
        List<String> allStoreName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println("store name of Fargo is " + allStoreName);
    }

    @Test
    //17. Find the zip of all the store
    public void findAllZip() {
        List<Integer> zips = response.extract().path("data.zip");
        System.out.println("zip of all store is " + zips);
    }

    @Test
    //18. Find the zip of store name = Roseville
    public void findStoreZip() {
        List<String> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println("Zip of store is " + zip);
    }

    @Test
    //19. Find the storeServices details of the service name = Magnolia Home Theater
    public void findStoreServices() {
        List<HashMap<?, ?>> details = response.extract().path("data.services*.findAll{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println(details);
    }

    @Test
    //20. Find the lat of all the stores
    public void findAllStoresLat() {
        List<Float> Lat = response.extract().path("data.lat");
        System.out.println("The lat of the store is " + Lat);
    }
}
