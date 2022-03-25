package com.example.restaurantdatabase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantDataBaseService {

    private RestaurantRepository restaurantRepository;
    private TableRepository tableRepository;

    public RestaurantDataBaseService(RestaurantRepository restaurantRepository,
                                     TableRepository tableRepository) {
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
    }

    public Restaurant createRestaurant(Restaurant restaurantToCreate) {
        this.restaurantRepository.save(restaurantToCreate);
        return restaurantToCreate;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        this.restaurantRepository.findAll().forEach(restaurants::add);
        return restaurants;
    }

    public void removeAllRestaurants() {
        this.restaurantRepository.deleteAll();
    }

    public Restaurant updateRestaurant(Restaurant restaurantToUpdate, String restaurantId) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
                restaurant.setType(restaurantToUpdate.getType());
                restaurant.setName(restaurantToUpdate.getName());
                restaurantRepository.save(restaurant);
                return restaurant;

    }

    public Restaurant getRestaurant(String restaurantId) throws Exception {
        return this.restaurantRepository.findById(restaurantId).get();
    }

    public void removeRestaurant(String restaurantId) {
        this.restaurantRepository.deleteById(restaurantId);
    }

    public void assignClientsOnRestaurant(String restaurantId, int clients) throws Exception {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
                 List<Table> tables =  restaurant.addTables(clients);
                 tableRepository.saveAll(tables);
    }


    public List<Table> getTables(String restaurantId) throws Exception {
        Restaurant restaurant = searchRestaurant(restaurantId);
        return restaurant.getTableList();
    }

    public void removeAllTables(String restaurantId) throws Exception {
        Restaurant restaurant = searchRestaurant(restaurantId);
        tableRepository.deleteAllByRestaurant(restaurant);
    }

    public Table getTable(String restaurantId, String tableId) throws Exception {
        return tableRepository.findById(tableId).get();
    }

    public void removeTable(String tableId) throws Exception {
        this.tableRepository.deleteById(tableId);
    }

    private Restaurant searchRestaurant(String restaurantId) {
        return restaurantRepository.findById(restaurantId).get();
    }
}
