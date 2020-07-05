package javaapplication1;

import java.util.Scanner;
import java.io.File;

class Main {
    static int words = 0;
    static int sentence = 0;
    static int syllable = 0;
    static int polysyllable = 0;
    static int countChar = 0;
    
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Tarushi\\Desktop\\docs.txt");
        String text = "";
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String read = sc.nextLine();
                text = text + read;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
       
        if (text.charAt(text.length() - 1) != '.') {
            text = text + ".";
        }
        String regex = "[Ee][.!]";
        String s1 = text.replaceAll(regex, "#.");
        String regex2 = "[Ee][,]";
        String s2 = s1.replaceAll(regex2, "#");
        String regex3 = "[Ee][ ]";
        String s3 = s2.replaceAll(regex3, "# ");
        //Function Calling
        syllableCount(s3);
        syllableCountAgain(s3);
        calcScore();
    }
    public static void calcScore() {
        Scanner sc = new Scanner(System.in);
        double score = 0;
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String in = sc.nextLine();
        String str = "";
        double a = 0;
        double L = 0;
        double S = 0;
        switch(in) {
            case "ARI" :
                score = (4.71 * (countChar / (double)words)) + (0.5 * (words /(double)sentence)) - 21.43;
                str = String.format("Automated Readability Index: %.2f" , score);
                System.out.println(str + " (about " + ageGroup(score)+ " year olds).");
                
                break;
            case "FK" :
                score = 0.39 * (words / (double)sentence) + 11.8 * (syllable / (double)words) - 15.59;
                str = String.format("Flesch–Kincaid readability tests: %.2f" , score);
                System.out.println(str + " (about " + ageGroup(score)+ " year olds).");
                break;
            case "SMOG" :
                a = polysyllable * (30 / (double)sentence);
                score = 1.043 * Math.sqrt(a) + 3.1291;
                str = String.format("Simple Measure of Gobbledygook: %.2f" , score);
                System.out.println(str + " (about " + ageGroup(score)+ " year olds).");
                break;
            case "CL" :
                L = (countChar / (double)words) * 100;
                S = (sentence / (double)words) * 100;
                score = 0.0588 * L - 0.296 * S - 15.8;
                str = String.format("Coleman–Liau index: %.2f" , score);
                System.out.println(str + " (about " + ageGroup(score)+ " year olds).");
                break;
            case "all" :
                double score1 = (4.71 * (countChar / (double)words)) + (0.5 * (words /(double)sentence)) - 21.43;
                str = String.format("Automated Readability Index: %.2f" , score1);
                int age1 = ageGroup(score1);
                System.out.println(str + " (about " + age1 + " year olds).");
                
                double score2 = 0.39 * (words / (double)sentence) + 11.8 * (syllable / (double)words) - 15.59;
                str = String.format("Flesch–Kincaid readability tests: %.2f" , score2);
                int age2 = ageGroup(score2);
                System.out.println(str + " (about " + age2 + " year olds).");
                
                a = polysyllable * (30 / (double)sentence);
                double score3 = 1.043 * Math.sqrt(a) + 3.1291;
                str = String.format("Simple Measure of Gobbledygook: %.2f" , score3);
                int age3 = ageGroup(score3);
                System.out.println(str + " (about " + age3 + " year olds).");
                
                L = (countChar / (double)words) * 100;
                S = (sentence / (double)words) * 100;
                double score4 = 0.0588 * L - 0.296 * S - 15.8;
                str = String.format("Coleman–Liau index: %.2f" , score4);
                int age4 = ageGroup(score4);
                System.out.println(str + " (about " + age4+ " year olds).");
                
                double avgScore = (age1 + age2 + age3 + age4) / 4.0;
                str = String.format("This text should be understood in average by %.2f" , avgScore);
                System.out.println(str + " year olds.");
                break;
        }
    }
    public static int ageGroup(double score) {
       if (score <= 1) {
            return 5;
       } else if (score > 1 && score <= 2) {
            return 6;
        } else if (score > 2 && score <=3) {
            return 7;
        } else if (score > 3 && score <= 4) {
            return 9;
        } else if (score > 4 && score <= 5) {
            return 10;
        } else if (score > 5 &&  score <= 6){
            return 11;
        } else if (score > 6 && score <= 7) {
            return 12;
        } else if (score > 7 && score <= 8) {
            return 13;
        } else if (score > 8 && score <= 9) {
            return 14;
        } else if (score > 9 && score <= 10) {
            return 15;
        } else if (score > 10 && score <= 11) {
            return 17;
        } else if (score > 11 && score <= 12) {
            return 17;
        } else if (score > 12 && score <= 13) {
            return 18;
        } else {
            return 24;
        }
      
    }
    
    public static void syllableCountAgain(String text) {
        String[] parts = text.split(" ");
        
        for (int i = 0; i < parts.length; i++) {
            int count = 0;
            for (int j = 0; j < parts[i].length(); j++) {
                countChar++;
                if (parts[i].charAt(j) == 'a' || parts[i].charAt(j) == 'y' || parts[i].charAt(j) == 'Y' || parts[i].charAt(j) == 'e' || parts[i].charAt(j) == 'i' || parts[i].charAt(j) == 'o' || parts[i].charAt(j) == 'u' || parts[i].charAt(j) == 'A' || parts[i].charAt(j) == 'E' || parts[i].charAt(j) == 'I' || parts[i].charAt(j) == 'O' || parts[i].charAt(j) == 'U') {
                    syllable++;
                    count++;
                    if (j-1 != -1) {
                        if (parts[i].charAt(j-1) == 'a' || parts[i].charAt(j-1) == 'y' || parts[i].charAt(j-1) == 'Y' || parts[i].charAt(j-1) == 'e' || parts[i].charAt(j-1) == 'i' || parts[i].charAt(j-1) == 'o' || parts[i].charAt(j-1) == 'u' || parts[i].charAt(j-1) == 'A' || parts[i].charAt(j-1) == 'E' || parts[i].charAt(j-1) == 'I' || parts[i].charAt(j-1) == 'O' || parts[i].charAt(j-1) == 'U') {
                             syllable--;
                             count--;
                        }
                    }
                }
            }
            if (count >= 3) {
                polysyllable++;
            }
        }
        //Printing Characters, Syllable & Polysyllable.
        System.out.println("Characters: " + countChar);
        System.out.println("Syllables: " + syllable);
        System.out.println("Polysyllables: " + polysyllable);
    }
    
    public static void syllableCount(String text) {
        String[] parts = text.split(" ");
        
        for (int i = 0; i < parts.length; i++) {
            boolean set = true;
            words++;
            for (int j = 0; j < parts[i].length(); j++) {
                
                if (parts[i].charAt(j) == 'y' || parts[i].charAt(j) == 'Y' || parts[i].charAt(j) == 'a' || parts[i].charAt(j) == 'e' || parts[i].charAt(j) == 'i' || parts[i].charAt(j) == 'o' || parts[i].charAt(j) == 'u' || parts[i].charAt(j) == 'A' || parts[i].charAt(j) == 'E' || parts[i].charAt(j) == 'I' || parts[i].charAt(j) == 'O' || parts[i].charAt(j) == 'U') {
                    set = false;
                    break;
                } 
            }
            if (set) {
                syllable++;
            }
        }
        //Counting Sentences.
        for (int i = 0; i < parts.length; i++) {
            for (int j = 0; j < parts[i].length(); j++) {
                if (parts[i].charAt(j) == '.' || parts[i].charAt(j) == '?' || parts[i].charAt(j) == '!') {
                    sentence++;
                }
            }
        }
        //Printing Words & Characters.
        System.out.println("Words: "+ words);
        System.out.println("Sentences: " + sentence);
    }
}
