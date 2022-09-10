import java.util.Random;

public class Main {
    public static int bossHealth = 999;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {250, 260, 270, 250, 200, 260};
    public static int[] heroesDamage = {25, 20, 15, 0, 20, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Thor"};
    public static int docHeal = 10;
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        thunderFromThor();
        isLuckyLucky();
        bossHits();
        heroesHit();
        medicHealing();
        printStatistics();
    }


    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
    }

    public static void thunderFromThor() {
        Random random = new Random();
        boolean thunder = random.nextBoolean();
        if (thunder) {
            bossDamage = 0;
            System.out.println("Boss stunned");
        }
    }

    public static void isLuckyLucky() {
        Random random = new Random();
        boolean b = random.nextBoolean();

        if (b == true) {
            heroesHealth[4] += bossDamage;
            System.out.println("Lucky was lucky this round");
        }
        if (bossDamage == 0) {
            heroesHealth[4] += 0;
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int hit = heroesDamage[i];
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(7) + 2;
                    hit = coeff * heroesDamage[i];
                    System.out.println("Critical Damage: " + hit);
                }
                if (bossHealth - hit < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - hit;
                }
            }
        }
    }

    public static void medicHealing() {
        Random random = new Random();
        int notMedic = random.nextInt(heroesHealth.length);
        int i;
        for (i = 0; i == heroesHealth.length; i++) {
            if (i == 3) {
                continue;
            }
            if (heroesHealth[3] > 0 && notMedic < 100) {
                heroesHealth[i] += bossDamage;
            }

        }
        System.out.println("Medic healed: " + notMedic);
    }
    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " --------------");
        System.out.println("Boss health: " + bossHealth + "; damage: "
                + bossDamage + "; def: " + (bossDefenceType == null ? "No def" : bossDefenceType));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + "; damage: "
                    + heroesDamage[i]);
        }
    }
}
