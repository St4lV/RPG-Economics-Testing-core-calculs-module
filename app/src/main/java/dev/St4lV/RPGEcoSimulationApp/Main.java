package dev.St4lV.RPGEcoSimulationApp;

import dev.St4lV.RPGEcoSimulationApp.Calculs;

public class Main {
    public static void main(String[] args) {
        Interface ui = new Interface();
        ui.displayInterface();
        Calculs.getInstance().initValue();
    }
}
