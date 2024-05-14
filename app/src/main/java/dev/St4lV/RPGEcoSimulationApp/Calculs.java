package dev.St4lV.RPGEcoSimulationApp;

public class Calculs {

    private static Calculs instance;

    private Calculs() {
    }

    public static Calculs getInstance() {
        if (instance == null) {
            instance = new Calculs();
        }
        return instance;
    }

    //init:

    private int InitPrice, InitPlayerAccount, Qmax, Qratio, PlayerAccount, updatedPlayerAccount, QstartRatio, ActPrice;
    private double buyPrice, sellPrice;
    public int gcPlayer, scPlayer, bcPlayer, gcBuy, scBuy, bcBuy, gcSell, scSell, bcSell, Qact;
    public void initValue() {

        InitPrice = 150000;
        Qmax = 512;
        QstartRatio = 85;
        Qratio = QstartRatio;
        Qact = Qmax * QstartRatio / 100;
        ActPrice = InitPrice;
        InitPlayerAccount = 69420;
        PlayerAccount = InitPlayerAccount;
        updatedPlayerAccount = PlayerAccount;
    }

    // Quantity of items at init for test player
    private int QJoueur = 15;

    //Initialisation of values:

    //Conversion from price Integers to in game display units
    public void updateMarketValues() {

        //differences between base, buy and sell values :
        PlayerAccount = updatedPlayerAccount;
        buyPrice  = ActPrice + ((double) (ActPrice * (101 - Qratio)) / 500);
        sellPrice = ActPrice - ((double) (ActPrice * (101 - Qratio)) / 1000);
        //Player
        gcPlayer = PlayerAccount / 10000;
        scPlayer = (PlayerAccount % 10000) / 100;
        bcPlayer = PlayerAccount % 100;

        //MarketPrices
        //Buy
        gcBuy = (int) buyPrice / 10000;
        scBuy = (int) (buyPrice % 10000) / 100;
        bcBuy = (int) buyPrice % 100;
        //Sell
        gcSell = (int) sellPrice / 10000;
        scSell = (int) (sellPrice % 10000) / 100;
        bcSell = (int) sellPrice % 100;

        System.out.println("You currently have "+ gcPlayer + "\u001B[93m gold coins\u001B[0m " + scPlayer + "\u001B[37m silver coins\u001B[0m " + bcPlayer + "\u001B[33m bronze coin\u001B[0m ");
        System.out.println("You currently have " + QJoueur + " Golden Apple");

        // Testing intended values display, uncomment for more visibility when testing :

        //System.out.println("Quantity available: " + Qact + " Golden Apple");
        //System.out.println("Actual quantity ratio(Qratio) : " + Qratio);
        //System.out.println("Actual price (PrixAct) : " + ActPrice + "bc");
        //System.out.println("Buy price : " + gcBuy + "g" + scBuy + "s" + bcBuy + "b");
        //System.out.println("Sell price : " + gcSell + "g" + scSell + "s" + bcSell + "b");
        //System.out.println("PlayerAccount : "+ (int)PlayerAccount);
    }

    //Buy case

    public void buyItem() {
        //Current item unavailable
        if (Qact <= 0) {

            System.out.println("Can't buy item: §o§7item currently unavailable");

            //Not enough money
        } else if (PlayerAccount < buyPrice) {

            System.out.println("Can't buy item: not enough money!");

            //Successful buy

        } else if (PlayerAccount >= buyPrice && Qact >= 0) {
            System.out.println("Successfully bought for " + gcBuy + "\u001B[93m⌾\u001B[0m " + scBuy + "\u001B[37m⌾\u001B[0m " + bcBuy + "\u001B[33m⌾\u001B[0m ");
            updatedPlayerAccount = PlayerAccount - (int) buyPrice;
            Qact--;
            QJoueur++;
            ActPrice += (ActPrice * (100 - Qratio) / Qmax);
            Qratio = Qact * 100 / Qmax;

            String buy_successful = "§a✔ §b⇊ §f" +
                    (gcBuy != 0 ? gcBuy + "§e§l⌾ §f" : "") +
                    (scBuy != 0 ? scBuy + "§7§l⌾ §f" : "") +
                    bcBuy + "§6§l⌾ §f : Item bought.";


            updateMarketValues();

        } else {
            System.err.println("Can't buy item: error please report.");

        }
    }

    //Sell case

    public void sellItem() {
        //Actual quantity = Max quantity
        if (Qact == Qmax) {
            System.out.println("Can't sell item : too much in the market.");

            //Player doesn't have current item
        } else if (QJoueur <= 0) {
            System.out.println("Can't sell item : you are not in possession of this item.");

        } else if (ActPrice <= 5) {
            System.out.println("Can't sell item : price too low!");

            //Successful sell
        } else if (QJoueur >= 1 && Qact <= Qmax) {
            System.out.println("Successfully sold for: " + gcSell + "\u001B[93m gold coins \u001B[0m " + scSell + "\u001B[37m silver coins \u001B[0m " + bcSell + "\u001B[33m bronze coins \u001B[0m ");
            String sell_successful = "§a✔ §d⇈ §f" +
                    (gcSell != 0 ? gcSell + "§e§l⌾ §f" : "") +
                    (scSell != 0 ? scSell + "§7§l⌾ §f" : "") +
                    bcSell + "§6§l⌾ §f : Item sold.";

            updatedPlayerAccount = PlayerAccount + (int) sellPrice;
            Qact++;
            QJoueur--;
            ActPrice -= (ActPrice * (100 - Qratio) / Qmax);
            Qratio = Qact * 100 / Qmax;

            updateMarketValues();

        } else {
            System.err.println("Can't sell item : error.");
        }
    }
}