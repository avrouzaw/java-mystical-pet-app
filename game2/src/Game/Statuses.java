/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

public class Statuses {
    private String name;
    private int love;
    private int health;
    private int independence;
    private int spirit;
    private int energy;
    private int money;

    public Statuses(String name, int love, int health, int independence, int spirit, int energy, int money) {
        this.name = name;
        this.love = love;
        this.health = health;
        this.independence = independence;
        this.spirit = spirit;
        this.energy = energy;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getIndependence() {
        return independence;
    }

    public void setIndependence(int independence) {
        this.independence = independence;
    }

    public int getSpirit() {
        return spirit;
    }

    public void setSpirit(int spirit) {
        this.spirit = spirit;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
