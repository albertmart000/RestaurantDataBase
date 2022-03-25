package com.example.restaurantdatabase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "restaurants")
public class Restaurant {

    @javax.persistence.Id
    private String Id= UUID.randomUUID().toString();

    private static final int MAX_CAPACITY = 24;
    private static final int MAX_NUMBER_OF_TABLES = 4;

    private String name;
    private int type;
    private static final int PIZZERIA = 1;
    private static final int CHINESE = 2;
    private static final int KEBAB = 3;

    @OneToMany(mappedBy = "restaurant")
    private List<Table> tableList = new ArrayList<>();

    public Restaurant(){
    }

    public Restaurant(String name, int type) throws Exception {
        checkName(name);
        checkType(type);
        this.name = name;
        this.type = type;
    }

    private void checkType(int type) throws Exception {
        if (type != PIZZERIA && type != CHINESE && type != KEBAB) {
            throw new Exception();
        }
    }

    private void checkName(String name) throws Exception {
        if(name.equals("")) throw new Exception();
    }

    public String getId() {
        return Id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws Exception {
        checkType(type);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        checkName(name);
        this.name = name;
    }

    public List<Table> addTables(int numOfPeople) throws Exception {
        checkPeopleCanEnter(numOfPeople);

        while (numOfPeople > 0 && hasRemainingTables()) {
            Table table = new Table();
            numOfPeople = table.addClients(numOfPeople);
            table.setRestaurant(this);
            this.tableList.add(table);
        }

        if (numOfPeople > 0) throw new Exception("No quedan taules");
        return tableList;

    }

    private void checkPeopleCanEnter(int numOfPeople) throws Exception {
        if ((this.getCurrentSeatings() + numOfPeople) > MAX_CAPACITY)
            throw new Exception("Massa gent");
    }

    public int getCurrentSeatings() {
        int result = 0;
        for (Table table : tableList) {
            result += table.getCurrentSeatings();
        }
        return result;
    }

    public int getRemainingSeats() {
        return MAX_CAPACITY - getCurrentSeatings();
    }

    public List<Table> getTableList() {
        return tableList;
    }

    private boolean hasRemainingTables() {
        return this.tableList.size() < MAX_NUMBER_OF_TABLES;
    }


}
