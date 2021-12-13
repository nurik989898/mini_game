package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";

    public static int[] heroesHealth = {270, 260, 250, 300, 400, 300, 250, 200};
    public static int[] heroesDamage = {15, 20, 25, 0, 5, 20, 12, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }
    public static void changeBossDefence(){
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }


    public static void round() {
        round_number++;
        changeBossDefence();
        bossHits();
        heroesHits();
        medic();
        Golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();
    }
    public static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (i == 3){continue;}
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0){
                heroesHealth[i] += 50;
                System.out.println("Medic za hilil " + heroesAttackType[i]);
                break;
            }
        }
        }
        public static void Golem(){
            int dmg = bossDamage / 5;
            int colGeroi = 0;
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 4){continue;}
                if(heroesHealth[i] > 0 && heroesHealth[4] > 0) {
                    colGeroi++;
                    heroesHealth[i] += dmg;

                }

            }
            heroesHealth[4] -= dmg * colGeroi;
            System.out.println("Golem poluchil 1/5 geroya" + dmg * colGeroi);
        }
         public static void Lucky(){
        Random random = new Random();
        boolean randomm = random.nextBoolean();
        if (heroesHealth[5] > 0 && randomm){
            heroesHealth[5] += bossDamage;
            System.out.println("UKlonilsya ot udara " + randomm);
        }

         }

         public static void Berserk(){
             for (int i = 0; i < heroesHealth.length; i++) {
                 if(heroesHealth[6] > 0){
                     heroesHealth[6] += bossDamage / 2;
                     bossHealth -= bossDamage / 2;
                     System.out.println("Poglotil polovinu urona bossa " + bossDamage / 2);
                     break;

                 }

             }
         }
         public static void Thor(){
        Random random = new Random();
        boolean nur = random.nextBoolean();
        if(heroesHealth[7] > 0 && nur){
            bossDamage = 0;
            System.out.println("THor oglushil bossa ");
        }
        else {
            bossDamage = 50;
        }
         }
    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(8);
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage " + heroesDamage[i] * coeff);
                }
                }else{
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) ;
            {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Herous won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            ;
            System.out.println("Boss won");
            return true;
        }
        return false;*/
    boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won");
        }
        return allHeroesDead;

        }


    public static void printStatistics(){
        System.out.println(round_number + "ROUND ________________");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
            + heroesHealth[i] + " (" + heroesDamage[i] + ")");

        }
        System.out.println("__________________");
    }
}
