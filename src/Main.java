import java.util.Scanner;

//Intended to test main mod maths, basically this project is the mod without interface

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //!! remind !! : 101010 is 10gc 10sc 10bc

        //has to be >1000 for better results
        int PrixBase = 10000;

        //default quantity available in market
        int Qmax = 1280;

        // RANGE : 1 - 100 ! default 40
        int QstartRatio = 40;

        // value : positive integer >= 0
        int CompteJoueur = 60000;
        int QJoueur = 15;

        //Initialisation of values:
        int Qratio = QstartRatio;
        int PrixAct = PrixBase;
        int Qact = Qmax * QstartRatio / 100;
        boolean BourseLoop = true;

        //program core loop:
        while (BourseLoop) {

            //differences between base, buy and sell values :
            double PrixAchat = PrixAct + (int) (PrixAct * (100 - Qratio)/1000);
            double PrixVente = PrixAct - (int) (PrixAct * (100 - Qratio)/1000);

            //UI Terminal display :
            System.out.println("------------------------------------ ");
            System.out.println("Choose an action (buy/sell/exit): ");
            System.out.println("Quantity available: " + Qact + " Golden Apple");
            System.out.println("Buy : " + PrixAchat + " bc");
            System.out.println("Sell : " + PrixVente + " bc");
            System.out.println("You currently have " + CompteJoueur + " bc");
            System.out.println("You currently have " + QJoueur + " Golden Apple");

            /* Testing intended values display, uncomment for more visibility when testing : */
            //System.out.println("Actual quantity ratio(Qratio) : " + Qratio);
            //System.out.println("Actual rrice (PrixAct) : " + PrixAct + "bc");

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
                        System.out.println("Successfully sold for " + PrixAchat + " bc");
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

                    //Successful sell
                    } else if (QJoueur >= 0 && Qact <= Qmax) {
                            System.out.println("Successfully bought for : " + PrixVente + " bc");

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
