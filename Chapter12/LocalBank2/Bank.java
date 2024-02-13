package LocalBank2;

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Bank {
    private ArrayList<Account> accounts;
    private int numAccts;
    private int numLowAccounts;

    public Bank(File acctsFile) {
        accounts = new ArrayList<Account>();
        Account acct;

        if (!acctsFile.exists()) {
            try {
                acctsFile.createNewFile();
                System.out.println("There are no existing accounts.");
            } catch (IOException e) {
                System.out.println("File could not be created.");
                System.err.println("IOException: " + e.getMessage());
            }
            numAccts = 0;
        } else {
            try {
                FileInputStream in = new FileInputStream(acctsFile);
                ObjectInputStream readAccts = new ObjectInputStream(in);
                numAccts = (int) readAccts.readInt();
                numLowAccounts = (int) readAccts.readInt();

                System.out.println("There are " + numAccts + " accounts, " + numLowAccounts + " of which are low balance.");

                if (numAccts == 0) {
                    System.out.println("There are no existing accounts.");
                } else {
                    for (int i = 0; i < numAccts; i++) {
                        acct = (Account) readAccts.readObject();
                        System.out.println(acct);
                        accounts.add(acct);
                    }
                }

                readAccts.close();
            } catch (FileNotFoundException e) {
                System.out.println("File could not be found.");
                System.err.println("FileNotFoundException: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("Problem with input/output.");
                System.err.println("IOException: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println("Class  could not be used to cast object.");
                System.err.println("ClassNotFoundException: " + e.getMessage());
            }
        }
    }

    public void addAccount() {
        Account newAcct;
        double bal;
        String fName, lName;

        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.print("First name: ");
        fName = input.nextLine();
        System.out.print("Last name: ");
        lName = input.nextLine();
        System.out.print("Beginning balance: ");
        bal = input.nextDouble();

        newAcct = new Account(bal, fName, lName);
        accounts.add(newAcct);
        numAccts += 1;

        System.out.println("Account created. Account ID is: " + newAcct.getID());
    }

    public void deleteAccount(String acctID) {
        int acctIndex;

        Account acctToMatch = new Account(acctID);
        acctIndex = accounts.indexOf(acctToMatch);

        if (acctIndex > -1) {
            accounts.remove(acctIndex);
            System.out.println("Account removed.");
            numAccts -= 1;

        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void transaction(int transCode, String acctID, double amt) {
        int acctIndex;
        Account acctToMatch, acct;

        acctToMatch = new Account(acctID);
        acctIndex = accounts.indexOf(acctToMatch);
        if (acctIndex > -1) {
            acct = accounts.get(acctIndex);
            if (transCode == 1) {

                acct.deposit(amt);
                accounts.set(acctIndex, acct);
                System.out.println(acct);
            } else if (transCode == 2) {
                acct.withdrawal(amt);
                accounts.set(acctIndex, acct);
                System.out.println(acct);

            }
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void checkBalance(String acctID) {
        int acctIndex;
        Account acctToMatch, acct;

        acctToMatch = new Account(acctID);
        acctIndex = accounts.indexOf(acctToMatch);
        if (acctIndex > -1) {
            acct = accounts.get(acctIndex);
            System.out.println(acct);
        } else {
            System.out.println("Account does not exist.");
        }
    }

    public void updateAccounts(File acctsFile) {
        for (Account acct : accounts) {
            if (acct.getBalance() < 20) {
                numLowAccounts ++;
            }
        }

        System.out.println("There are " + numAccts + " accounts, " + numLowAccounts + " of which are low balance.");

        try {
            FileOutputStream out = new FileOutputStream(acctsFile);
            ObjectOutputStream writeAccts = new ObjectOutputStream(out);
            writeAccts.writeInt(numAccts);
            writeAccts.writeInt(numLowAccounts);

            for (Account acct : accounts) {
                writeAccts.writeObject(acct);
            }
            writeAccts.close();
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
            System.err.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Problem with input/output.");
            System.err.println("IOException: " + e.getMessage() + e.getStackTrace());
        }
    }
}