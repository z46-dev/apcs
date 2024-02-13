package LocalBank2;

import java.io.*;
import java.util.Scanner;

public class LocalBank {
    public static void main(String[] args) {
        File root = new File(System.getProperty("user.dir"));
        File accountsFile = new File(root.getAbsolutePath() + "/Chapter12/data/LBAccounts.dat");
        Bank easySave = new Bank(accountsFile);
        Scanner input = new Scanner(System.in);
        String action, acctID;
        Double amt;

        do {
            System.out.println("\n(D)eposit\\ (W)ithdrawal\\ (C)heck balance");
            System.out.println("(A)dd an account\\ (R)emove an account");
            System.out.println("(Q)uit\n");
            System.out.print("Enter choice: ");
            action = input.next();

            if (action.equalsIgnoreCase("A")) {
                easySave.addAccount();
            } else if (!action.equalsIgnoreCase("Q")) {
                System.out.print("Enter account ID: ");
                acctID = input.next();
                if (action.equalsIgnoreCase("D")) {
                    System.out.print("Enter deposit amount: ");
                    amt = input.nextDouble();
                    easySave.transaction(1, acctID, amt);
                } else if (action.equalsIgnoreCase("W")) {
                    System.out.print("Enter withdrawal amount: ");
                    amt = input.nextDouble();
                    easySave.transaction(2, acctID, amt);
                } else if (action.equalsIgnoreCase("C")) {
                    easySave.checkBalance(acctID);
                } else if (action.equalsIgnoreCase("R")) {
                    easySave.deleteAccount(acctID);
                }
            }
        } while (!action.equalsIgnoreCase("Q"));

        easySave.updateAccounts(accountsFile);
        input.close();
    }
}