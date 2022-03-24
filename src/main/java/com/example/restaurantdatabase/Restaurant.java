package com.example.restaurantdatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Restaurant {

    private String Id= UUID.randomUUID().toString();

    private static final int MAX_CAPACITY = 24;
    private static final int MAX_NUMBER_OF_TABLES = 4;

    private String name;
    private int type;
    private static final int PIZZERIA = 1;
    private static final int CHINESE = 2;
    private static final int KEBAB = 3;
    private List<Table> tables = new ArrayList<>();

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

    public void addClients(int numOfPeople) throws Exception {
        checkPeopleCanEnter(numOfPeople);

        while (numOfPeople > 0 && hasRemainingTables()) {
            Table table = new Table();
            numOfPeople = table.addClients(numOfPeople);
            tables.add(table);
        }


        if (numOfPeople > 0) throw new Exception("No quedan taules");

    }

    private void checkPeopleCanEnter(int numOfPeople) throws Exception {
        if ((this.getCurrentSeatings() + numOfPeople) > MAX_CAPACITY)
            throw new Exception("Massa gent");
    }

    public int getCurrentSeatings() {
        int result = 0;
        for (Table table : tables) {
            result += table.getCurrentSeatings();
        }
        return result;
    }

    public int getRemainingSeats() {
        return MAX_CAPACITY - getCurrentSeatings();
    }

    public List<Table> getTables() {
        return tables;
    }

    private boolean hasRemainingTables() {
        return this.tables.size() < MAX_NUMBER_OF_TABLES;
    }

    public void removeTable(String tableId) throws Exception {
        for (int i = 0; i < tables.size(); i++) {
            if (tables.get(i).getId().equals(tableId)) {
                tables.remove(i);
                break;
            }
        }
    }

    public void removeAllTables() {
        tables= new ArrayList<>();
    }

    public Table getTable(String tableId) throws Exception {

        for (Table table: tables) {
            if (table.getId().equals(tableId)) {
                return table;
            }
        }
        throw new Exception("No s'ha trobat");
    }
}
