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
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.addClients(clients);
    }


    public List<Table> getTables(String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        List<Table> tables = restaurant.getTables();
        return tables;
    }

    public void removeAllTables(String restaurantId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.getTables().clear();
    }

    public Table getTable(String restaurantId, String tableId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        Table table = restaurant.getTable(tableId);
        return table;
    }

    public void removeTable(String restaurantId, String tableId) throws Exception {
        Restaurant restaurant = findRestaurant(restaurantId);
        restaurant.removeTable(tableId);
    }

//    private Restaurant findRestaurant(String restaurantId) throws Exception {
//        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
//            if (restaurant.getId().equals(restaurantId)) {
//                return restaurant;
//            }
//        }
//        throw new Exception("No s'ha trobat");
//    }

    private Restaurant searchRestaurant(String restaurantId) {
        return restaurantRepository.findById(restaurantId).get();
    }
}
