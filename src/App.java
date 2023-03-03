import java.util.*;
public class App {
    public static void main(String[] args) {
        //System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);

        //HashMaps as DB to store the data
        //Linking of account holder to account number
        HashMap<String,String> accountNumberToAccountHolder = new HashMap<>();
        //Linking of balance amount of a account
        HashMap<String,Integer> accountNumberToBalance=new HashMap<>();

        //guide lines
        System.out.println("For create operation: statement should be CREATE Acc.No Acc.HolderName");
        System.out.println("For deposit operation: statement should be Deposit Acc.No Amount");
        System.out.println("For withdraw operation: statement should be WITHDRAW Acc.No Amount");
        System.out.println("For show balance operation: statement should be BALANCE Acc.No");
        System.out.println("Provide your state below:");

        //operations start here
        while(sc.hasNextLine()){
            String input=sc.nextLine();
            String[] commands=input.split(" ");

            String accountNumber;
            String balance;
            int balanceInInteger=0;
            String accountHolder;

            //if operation is create, create an account
            if(commands[0].equalsIgnoreCase("create"))
            {
                accountNumber=commands[1];
                accountHolder=commands[2];
                if(accountNumberToAccountHolder.containsKey(accountNumber))
                    System.out.println("Account already exists. Please provide other account number");
                else{
                    accountNumberToAccountHolder.put(accountNumber, accountHolder);
                    accountNumberToBalance.put(accountNumber,0);
                }
            }

            //if operation is deposit, deposit the money and add the account to exisiting balance
            else if(commands[0].equalsIgnoreCase("deposit"))
            {
                accountNumber=commands[1];
                balance=commands[2];
                //checking whether the given account exists or not
                if(!accountNumberToAccountHolder.containsKey(accountNumber))
                {
                    System.out.println("Account number entered doesn't Exist. Please create a account first");
                }
                else{
                    balanceInInteger=accountNumberToBalance.get(accountNumber)+Integer.parseInt(balance);
                    accountNumberToBalance.put(accountNumber, balanceInInteger);
                }
            }

            //if operation is withdraw, Take the money from the account
            else if(commands[0].equalsIgnoreCase("withdraw"))
            {
                accountNumber=commands[1];
                String amount=commands[2];
                int amountInInteger=Integer.parseInt(amount);
                //checking whether the given account exists or not
                if(!accountNumberToAccountHolder.containsKey(accountNumber))
                {
                    System.out.println("Account number entered doesn't Exist. Please create a account first");
                }
                else{
                    //checking whether the amount that has to be with draw is less than existing balance
                    if(accountNumberToBalance.get(accountNumber)>amountInInteger)
                    {
                        balanceInInteger=accountNumberToBalance.get(accountNumber)-amountInInteger;
                        accountNumberToBalance.put(accountNumber, balanceInInteger);
                    }
                    else{
                        System.out.print("Insufficient funds");
                    }
                }
            }

            //if operation is balance, show the balance of the account
            else if(commands[0].equalsIgnoreCase("balance"))
            {
                String accNo=commands[1];
                //checking whether the given account exists or not
                if(!accountNumberToAccountHolder.containsKey(accNo))
                {
                    System.out.println("Account number entered doesn't Exist. Please create a account first");
                }
                else{
                    System.out.println(accountNumberToBalance.get(accNo));
                }
            }
        }
    }
}

