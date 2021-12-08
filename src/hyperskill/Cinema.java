package hyperskill;

import java.util.Scanner;

public class Cinema {

    private static char[][] plan;
    private static int rows;
    private static int seats;
    private static int selection = 1;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        defineRoom();
        preSet();

        while (selection != 0) {
            menu();
            if (selection == 1) {
                print();
            } else if (selection == 2) {
                takePlace();
            } else if (selection == 3) {
                statistics();
            } else {
                break;
            }
        }

        scanner.close();

    }

    public static void defineRoom() {
        System.out.println("Enter the number of rows: ");
        rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        seats = scanner.nextInt();
    }

    public static void preSet() {
        plan = new char[rows + 1][seats + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                plan[i][j] = 'S';
            }
        }
        int rowCounter = '0';
        for (int i = 0; i < rows + 1; i++) {
            plan[i][0] = (char)rowCounter;
            rowCounter++;
        }
        int seatCounter = '0';
        for (int i = 0; i < seats + 1; i++) {

            plan[0][i] = (char)seatCounter;
            seatCounter++;
        }
        plan[0][0] = ' ';
    }

    public static int menu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        selection = scanner.nextInt();
        if (selection < 0 || selection > 3) {
            menu();
        }
        return selection;
    }

    public static void print() {
        System.out.println("Cinema: ");
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                System.out.print(plan[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void takePlace() {
        System.out.println();
        System.out.println("Enter a row number: ");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seat = scanner.nextInt();

        int frontRows = rows / 2;
        int price = 0;
        if (rows * seats <= 60) {
            price = 10;
        } else if (rows * seats > 60 && row <= frontRows) {
            price = 10;
        } else {
            price = 8;
        }
        try {
            if (plan[row][seat] == 'B') {
                System.out.println();
                System.out.println("That ticket has already been purchased!");
                takePlace();
            } else {
                plan[row][seat] = 'B';
                System.out.println();
                System.out.println("Ticket price: $" + price);
            }
        }catch (ArrayIndexOutOfBoundsException e) {
                System.out.println();
                System.out.println("Wrong input!");
        }
    }

    public static void statistics() {
        System.out.println();
        System.out.println("Number of purchased tickets: " + soldSeats());
        System.out.printf("Percentage: %.2f%c%n", percentageSold(), '%');
        System.out.println("Current income: $" + currentIncome());
        System.out.println("Total income: $" + totalIncome());
    }

    public static int soldSeats() {
        int soldSeats = 0;
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (plan[i][j] == 'B') {
                    soldSeats++;
                }
            }
        }
        return soldSeats;
    }

    public static double percentageSold() {
        return (double)(soldSeats() * 100) / (rows * seats);
    }

    public static int currentIncome() {
        int currentIncome = 0;
        if (rows * seats <= 60) {
            for (int i = 0; i < rows + 1; i++) {
                for (int j = 0; j < seats + 1; j++) {
                    if (plan[i][j] == 'B') {
                        currentIncome+=10;
                    }
                }
            }
        } else {
            for (int i = 0; i < rows / 2 + 1; i++) {
                for (int j = 0; j < seats + 1; j++) {
                    if (plan[i][j] == 'B') {
                        currentIncome+=10;
                    }
                }
            }
            for (int i = rows / 2 + 1; i < rows + 1; i++) {
                for (int j = 0; j < seats + 1; j++) {
                    if (plan[i][j] == 'B') {
                        currentIncome+=8;
                    }
                }
            }
        }
        return currentIncome;
    }

    public static int totalIncome() {
            int totalPossibleIncome = 0;
            if (rows * seats <= 60) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < seats; j++) {
                            totalPossibleIncome+=10;
                    }
                }
            } else {
                for (int i = 0; i < rows / 2; i++) {
                    for (int j = 0; j < seats; j++) {
                        totalPossibleIncome+=10;
                    }
                }
                for (int i = rows / 2; i < rows; i++) {
                    for (int j = 0; j < seats; j++) {
                            totalPossibleIncome+=8;
                    }
                }
            }
            return totalPossibleIncome;
    }
}