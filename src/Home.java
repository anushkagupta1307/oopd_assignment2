package src;

import src.vaccine.*;
import src.virus.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Home {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int choice;
        List<User> registeredUsers= new ArrayList<User>();

        do {
        System.out.println("Welcome To the Portal:\n" +
                "Please select your option:\n" +
                "1. New User\n" +
                "2. Existing Patient\n" +
                "3. Exit");

        choice=sc.nextInt();

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
                System.out.println(registeredUsers.size());

                System.out.println("Patient has been registered.");
                System.out.println("Username : "+username);
                System.out.println("Aadhar Number : "+aadhar);
                System.out.println("Vaccine Opted : "+user.getVaccine().getName());
            }
            if (choice == 2) {
                System.out.println("Enter Registered Username: ");
                String regUsername = sc.next();

                System.out.println("Verifying ...");

                System.out.println("Size of reg user list "+registeredUsers.size());
                if(registeredUsers.size()>0) {
                    for (User user : registeredUsers) {

                        if (user.getUsername().equals(regUsername)) {
                            System.out.println("Patient Found! ");
                            System.out.println("Welcome "+regUsername);
                            calculate(user,1,sc);
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

    public static  void calculate(User user, int wave_number, Scanner sc){

        System.out.println("You’re being tested for the "+wave_number+ "wave for the alpha variant of the virus : ");
        int boost=user.getVaccine().getBoost();
        System.out.println("Vaccine's BOOST : "+boost);
        double durability=user.getVaccine().getDurability(wave_number);
        System.out.println("Vaccine's DURABILITY : "+durability);

        CovidVirus virus=setVirusDetails(wave_number);
        int life=virus.getLife();

        System.out.println("Virus's LIFE : "+life);
        System.out.println("Virus's VARIANT : "+virus.getName());


        int choice;
        do {
            System.out.println("Please select an action:\n" +
                    "1. INJECT\n" +
                    "2. EFFECT\n" +
                    "3. Exit");

             choice=sc.nextInt();

             if(choice == 1){
                 System.out.println("Your vaccine is boosted and it reduces the life of the virus by "+user.getVaccine().getBoost());
                 System.out.println("Vaccine's DURABILITY : "+user.getVaccine().getDurability(wave_number));
                  life=life-boost;
                 System.out.println("Virus's LIFE : "+life);
                 System.out.println("VIRUS’s ACTION !");
                 double attack= (int)(Math.random() * (virus.getAttack()*life) + 1);
                 System.out.println("Virus reduces the vaccine’s DURABILITY by "+attack);
                 durability=durability-attack > 0 ? durability-attack : 0;
                 System.out.println("Vaccine’s DURABILITY : "+durability);
                 System.out.println("Virus’s LIFE : "+life);
                 if(durability<=0){
                     System.out.println("Oops! Your vaccine fails to affect the "+virus.getName() +" Variant.\n" +
                             "However, The vaccine helps you fight against several attacks of the virus and proves to\n" +
                             "be useful. This shows how important the vaccine is in the fight against COVID-19.\n" +
                             "Thanks for your participation. Now Let’s get Vaccinated !!!");
                     break;
                 }
                 if(life<=0 && (wave_number==1 ||wave_number==2 || wave_number==3)){
                     System.out.println("Virus Defeated !\n" +
                             "Vaccine proves to be effective during the "+wave_number+" wave!!!\n" +
                             "Moving on to the next wave.");
                     calculate( user,  wave_number+1,  sc);
                 }
                 if(life<=0 && wave_number==4){
                     System.out.println("Virus Defeated !\n" +
                             "Vaccine proves to be effective during the fourth wave!!!\n" +
                             "Congratulations, your vaccine has overpowered all the variants of the virus and hence\n" +
                             "has proved to be effective in all the waves. Great Job!\n" +
                             "Thanks for your participation. Now let’s get Vaccinated !!!");
                     break;
                 }
             }
             if(choice ==2){
                 int effect= user.getVaccine().getEffect();
                 System.out.println("Virus’s action reduced by "+effect);
                 System.out.println("Vaccine’s DURABILITY : "+durability);
                 System.out.println("Virus’s LIFE : "+life);
                 System.out.println("VIRUS’s ACTION !");
                 double attack=  (int) (Math.random() * (virus.getAttack()*life) + 1)-effect ;
                 if(attack<=0){
                     attack=0;
                 }
                 System.out.println("Virus reduces the vaccine’s DURABILITY by "+attack);
                 durability=durability-attack > 0 ? durability-attack : 0;
                 System.out.println("Vaccine’s DURABILITY : "+durability);
                 System.out.println("Virus’s LIFE : "+life);
                 if(durability<=0){
                     System.out.println("Oops! Your vaccine fails to affect the "+virus.getName() +" Variant.\n" +
                             "However, The vaccine helps you fight against several attacks of the virus and proves to\n" +
                             "be useful. This shows how important the vaccine is in the fight against COVID-19.\n" +
                             "Thanks for your participation. Now Let’s get Vaccinated !!!");
                     break;
                 }
             }

        }
        while(choice !=3);

        if(choice ==3){
            System.out.println("Exited at wave "+wave_number +"!\n" +
                    "Thanks for your participation. Let’s get Vaccinated !!!");
        }


    }

    public static CovidVirus setVirusDetails(int wave_number){

        CovidVirus virus=null;

        switch(wave_number) {
            case 1:{
                virus = new Aplha();
                break;
            }
            case 2:{
                virus = new Beta();
                break;
            }
            case 3:{
                virus = new Gama();
                break;
            }
            case 4:{
                virus = new Sigma();
                break;
            }

        }
        return virus;


    }




   public static Vaccine setVaccineDetails(int vac){

        Vaccine vaccine=null;

        switch(vac) {
            case 1:{
                vaccine = new CovidShield();
                break;
            }
            case 2:{
                vaccine = new Covaxin();
                break;
            }
            case 3:{
                vaccine = new Pfizer();
                break;
            }
            case 4:{
                vaccine = new Sputnik();
                break;
            }

        }
        return vaccine;
   }

}
