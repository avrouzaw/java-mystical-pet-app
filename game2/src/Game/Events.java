/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Game;

public class Events {
    private String name;
    private String description;
    private int loveEffect;
    private int healthEffect;
    private int independenceEffect;
    private int spiritEffect;
    private int energyEffect;
    private int moneyEffect;

    public Events(String name, String description, int loveEffect, int healthEffect, 
                  int independenceEffect, int spiritEffect, int energyEffect, int moneyEffect) {
        this.name = name;
        this.description = description;
        this.loveEffect = loveEffect;
        this.healthEffect = healthEffect;
        this.independenceEffect = independenceEffect;
        this.spiritEffect = spiritEffect;
        this.energyEffect = energyEffect;
        this.moneyEffect = moneyEffect;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getLoveEffect() {
        return loveEffect;
    }

    public int getHealthEffect() {
        return healthEffect;
    }

    public int getIndependenceEffect() {
        return independenceEffect;
    }

    public int getSpiritEffect() {
        return spiritEffect;
    }

    public int getEnergyEffect() {
        return energyEffect;
    }

    public int getMoneyEffect() {
        return moneyEffect;
    }
}
