package org.example.chessMaster.core.model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Player {
    Color color;
    Scanner scanner = new Scanner(System.in);

    public String setCurrentField() {
        String field = "";
        while(!validateField(field)) {
        System.out.println("Podaj oznaczenie biezacego pola: ");
            field = scanner.nextLine();
        }
        return field;
    }
    public String setTargetField() {
        String field = "";
        while(!validateField(field)) {
            System.out.println("Podaj oznaczenie pola docelowego: ");
            field = scanner.nextLine();
        }
        return field;
    }
    private boolean validateField(String field) {
        String regex = "[a-h][1-8]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        return matcher.matches();
    }
}
