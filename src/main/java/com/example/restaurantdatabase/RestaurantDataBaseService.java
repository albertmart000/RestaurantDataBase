package com.example.restaurantdatabase;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RestaurantDataBaseService {

    private List<Restaurant> restaurants = new ArrayList<>();

    public Restaurant createRestaurant(Restaurant restaurantToCreate) {
        restaurants.add(restaurantToCreate);
        return restaurantToCreate;
    }

    public List<Restaurant> getRestaurants() {return restaurants;}

    public void removeAllRestaurants() {
        restaurants = new ArrayList<>();
    }


    public void updateRestaurant(Restaurant restaurantToUpdate, String restaurantId) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getId().equals(restaurantId)) {
                restaurant.setType(restaurantToUpdate.getType());
                restaurant.setName(restaurantToUpdate.getName());
                return;
            }
        }
        throw new Exception("No s'ha trobat");
    }

    public Restaurant getRestaurant(String restaurantId) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getId().equals(restaurantId)) {
                return restaurant;
            }
        }
        throw new Exception("No s'ha trobat");

    }

    public void removeRestaurant(String restaurantId) {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getId().equals(restaurantId)) {
                restaurants.remove(restaurant);
            }
        }
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

    private Restaurant findRestaurant(String restaurantId) throws Exception {
        for (Restaurant restaurant : new ArrayList<>(restaurants)) {
            if (restaurant.getId().equals(restaurantId)) {
                return restaurant;
            }
        }
        throw new Exception("No s'ha trobat");
    }

}
