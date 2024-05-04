import java.util.Scanner;

//Intended to test main mod maths, basically this project is the mod without interface

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //!! remind !! : 101010 is 10gc 10sc 10bc

        //has to be >1000 for better results
        int PrixBase = 10500;

        //Max quantity available in market
        int Qmax = 512;

        // RANGE : 1 - 100 ! default 85 : Quantity Available in market at init
        int QstartRatio = 85;

        // value : positive integer >= 0 : Start money in test player account
        int CompteJoueur = 169420;

        // Quantity of items at init for test player
        int QJoueur = 15;

        //Initialisation of values:
        int Qratio = QstartRatio;
        int PrixAct = PrixBase;
        int Qact = Qmax * QstartRatio / 100;
        boolean BourseLoop = true;

        //program core loop:
        while (BourseLoop) {

            //differences between base, buy and sell values :
            double PrixAchat = PrixAct + (int) (PrixAct * (101 - Qratio) / 1000);
            double PrixVente = PrixAct - (int) (PrixAct * (101 - Qratio) / 1000);
            //Conversion from price Integers to in game display units

                //Player
            int gcJoueur = CompteJoueur / 10000;
            int scJoueur = (CompteJoueur % 10000) / 100;
            int bcJoueur = CompteJoueur % 100;

            //MarketPrices
            //Buy
            int gcAchat = (int) PrixAchat / 10000;
            int scAchat = (int) (PrixAchat % 10000) / 100;
            int bcAchat = (int) PrixAchat % 100;
            //Sell
            int gcVente = (int) PrixVente / 10000;
            int scVente = (int) (PrixVente % 10000) / 100;
            int bcVente = (int) PrixVente % 100;

            //UI Terminal display :
            System.out.println("------------------------------------ ");
            System.out.println("Choose an action (buy/sell/exit): ");
            System.out.println("Quantity available: " + Qact + " Golden Apple");
            System.out.println("Buy : " + gcAchat + "\u001B[93m\u233E\u001B[0m " + scAchat + "\u001B[37m\u233E\u001B[0m " + bcAchat + "\u001B[33m\u233E\u001B[0m ");
            System.out.println("Sell : " + gcVente + "\u001B[93m\u233E\u001B[0m " + scVente + "\u001B[37m\u233E\u001B[0m " + bcVente + "\u001B[33m\u233E\u001B[0m ");
            System.out.println("You currently have "+ gcJoueur + "\u001B[93m\u233E\u001B[0m " + scJoueur + "\u001B[37m\u233E\u001B[0m " + bcJoueur + "\u001B[33m\u233E\u001B[0m ");
            System.out.println("You currently have " + QJoueur + " Golden Apple");

            // Testing intended values display, uncomment for more visibility when testing :

            //System.out.println("Actual quantity ratio(Qratio) : " + Qratio);
            //System.out.println("Actual price (PrixAct) : " + PrixAct + "bc");
            //System.out.println("Buy price : " + PrixAchat);
            //System.out.println("Sell price : " + PrixVente);
            
            //value prompt :
            String command = scanner.nextLine();

            switch (command) {

                //Buy case

                case "buy":

                    //Current item unavailable
                    if (Qact <= 0) {
                        System.out.println("Can't buy item : item currently unavailable");

                    //Not enough money
                    } else if (CompteJoueur < PrixAchat) {
                        System.out.println("Can't buy item : not enough money !");

                    //Successful buy
                    } else if (CompteJoueur >= PrixAchat && Qact >= 0) {
                        System.out.println("Successfully bought for " + gcAchat + "\u001B[93m\u233E\u001B[0m " + scAchat + "\u001B[37m\u233E\u001B[0m " + bcAchat + "\u001B[33m\u233E\u001B[0m ");
                        CompteJoueur = CompteJoueur - (int) PrixAchat;
                        Qact = Qact - 1;
                        QJoueur = QJoueur + 1;
                        PrixAct = PrixAct + (PrixAct * (100 - Qratio) / Qmax);
                        Qratio = Qact * 100 / Qmax;
                    } else {
                        System.out.println("Can't buy item : error");
                    }

                    break;

                //Sell case

                case "sell":

                    //Actual quantity = Max quantity
                    if (Qact == Qmax) {
                        System.out.println("Can't sell item : too much in the market");

                    //Player doesn't have current item
                    } else if (QJoueur <= 0) {
                        System.out.println("Can't sell item : you are not in possession of this item.");

                    } else if (PrixAct <= 5) {
                        System.out.println("Can't sell item : price too low !"); // TEMP PROTECTION /!\ : price will not change whatever buy or sell is executed when prices too low, will have to fix this;

                    //Successful sell
                    } else if (QJoueur >= 0 && Qact <= Qmax) {
                            System.out.println("Successfully sold for : " + gcVente + "\u001B[93m\u233E\u001B[0m " + scVente + "\u001B[37m\u233E\u001B[0m " + bcVente + "\u001B[33m\u233E\u001B[0m ");

                            CompteJoueur = CompteJoueur + (int) PrixVente;
                            Qact = Qact + 1;
                            QJoueur = QJoueur - 1;
                            PrixAct = PrixAct - (PrixAct * (100 - Qratio) / Qmax);
                            Qratio = Qact * 100 / Qmax;
                        } else {
                            System.out.println("Can't sell item : error");
                        }
                    break;

                case "exit":
                    System.out.println("Exiting program.");
                    BourseLoop = false;
                    break;

                default:
                    System.out.println("Unknown command.");
            }
        }

        scanner.close();
    }
}
