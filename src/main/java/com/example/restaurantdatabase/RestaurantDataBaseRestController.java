package com.example.restaurantdatabase;

import org.springframework.web.bind.annotation.*;
import org.json.*;

import java.util.List;

@RestController
public class RestaurantDataBaseRestController {

    private RestaurantDataBaseService restaurantDataBaseService;
    public RestaurantDataBaseRestController(RestaurantDataBaseService restaurantDataBaseService) {
        this.restaurantDataBaseService = restaurantDataBaseService;
    }



    @PostMapping("/restaurants")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurantToCreate) {
        return restaurantDataBaseService.createRestaurant(restaurantToCreate);
    }

    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurants() throws Exception {
        return restaurantDataBaseService.getRestaurants();
    }

    @DeleteMapping("/restaurants")
    public void removeAllRestaurants() {
        restaurantDataBaseService.removeAllRestaurants();
    }

    @PutMapping("/restaurants/{restaurantId}")
    public void updateRestaurant(@RequestBody Restaurant restaurantToUpdate, @PathVariable String restaurantId) throws Exception {
        restaurantDataBaseService.updateRestaurant(restaurantToUpdate,restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}")
    public Restaurant getRestaurant(@PathVariable String restaurantId) throws Exception {
        return restaurantDataBaseService.getRestaurant(restaurantId);

    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public void removeRestaurant(@PathVariable String restaurantId) {
        restaurantDataBaseService.removeRestaurant(restaurantId);
    }

    @PostMapping("/restaurants/{restaurantId}/tables")
    public void assignClientsOnRestaurant(@PathVariable String restaurantId, @RequestBody String jsonString) throws Exception {
        JSONObject json = new JSONObject(jsonString);
        int clients = json.getInt("clients");

        restaurantDataBaseService.assignClientsOnRestaurant(restaurantId, clients);
    }

    @GetMapping("/restaurants/{restaurantId}/tables")
    public List<Table> getTables(@PathVariable String restaurantId) throws Exception {
        return restaurantDataBaseService.getTables(restaurantId);
    }

    @DeleteMapping("/restaurants/{restaurantId}/tables")
    public void removeAllTable(@PathVariable String restaurantId) throws Exception {
        restaurantDataBaseService.removeAllTables(restaurantId);
    }

    @GetMapping("/restaurants/{restaurantId}/tables/{tableId}")
    public Table getTable(@PathVariable String restaurantId, @PathVariable String tableId) throws Exception {
        return restaurantDataBaseService.getTable(restaurantId, tableId);
    }

    @DeleteMapping("/restaurants/{restaurantId}/tables/{tableId}")
    public void removeTable(@PathVariable String restaurantId, @PathVariable String tableId) throws Exception {
        restaurantDataBaseService.removeTable(restaurantId,tableId);
    }


}
