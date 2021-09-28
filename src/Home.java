package src;

import src.vaccine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Home {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;

        do {
        System.out.println("Welcome To the Portal:\n" +
                "Please select your option:\n" +
                "1. New User\n" +
                "2. Existing Patient\n" +
                "3. Exit");

        choice=sc.nextInt();

        List<User> registeredUsers= new ArrayList<User>();

            if (choice == 1) {
                System.out.println("Enter Username: ");
                String username = sc.next();
                System.out.println("Enter Aadhar Number: ");
                String aadhar = sc.next();
                System.out.println("Choose your vaccine:\n" +
                        "1. Covishield\n" +
                        "2. Covaxin\n" +
                        "3. Pfizer\n" +
                        "4. Sputnik");

                int vac= sc.nextInt();

                User user= new User();
                user.setUsername(username);
                user.setAadharNumber(aadhar);
                Vaccine vaccine= setVaccineDetails(vac);
                user.setVaccine(vaccine);
                registeredUsers.add(user);

                System.out.println("Patient has been registered.");
                System.out.println("Username : "+username);
                System.out.println("Aadhar Number : "+aadhar);
                System.out.println("Vaccine Opted : "+user.getVaccine().getName());
            }
            if (choice == 2) {
                System.out.println("Enter Registered Username: ");
                String regUsername = sc.next();

                System.out.println("Verifying ...");

                if(registeredUsers.size()>0) {
                    for (User user : registeredUsers) {

                        if (user.getUsername().equals(regUsername)) {

                        } else {
                            System.out.println("Patient not found !!! If you haven’t registered yet then please register first.");
                        }
                    }
                }
                else{
                    System.out.println("Patient not found !!! If you haven’t registered yet then please register first.");
                }
            }
        }while(choice!=3);




    }

   public static Vaccine setVaccineDetails(int vac){

        Vaccine vaccine=null;

        switch(vac) {
            case 1:{
                vaccine = new CovidShield();
                vaccine.setId(1);
                vaccine.setName("Covidshield");
            }
            case 2:{
                vaccine = new Covaxin();
                vaccine.setId(2);
                vaccine.setName("Covaxin");
            }
            case 3:{
                vaccine = new Pfizer();
                vaccine.setId(3);
                vaccine.setName("Pfizer");
            }
            case 4:{
                vaccine = new Sputnik();
                vaccine.setId(4);
                vaccine.setName("Sputnik");
            }

        }
        return vaccine;
   }

}
