package com.example.menu;

import com.example.model.Household;
import com.example.service.HouseholdDAO;

import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private HouseholdDAO householdDAO = new HouseholdDAO();
    private Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean exit = false;

        while (!exit) {
            printMainMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> addHousehold();
                case 2 -> viewHouseholds();
                case 3 -> {
                    exit = true;
                    System.out.println("\nДякуємо за використання програми. До побачення!");
                }
                default -> System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }
        householdDAO.close();
    }

    private void printMainMenu() {
        System.out.println("\n=== Головне Меню ===");
        System.out.println("1. Додати домогосподарство");
        System.out.println("2. Переглянути всі домогосподарства");
        System.out.println("3. Вийти");
        System.out.print("Оберіть опцію: ");
    }

    private int getChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print("Будь ласка, введіть число: ");
            scanner.next(); // Пропускає невірне введення
        }
        return scanner.nextInt();
    }

    private void addHousehold() {
        System.out.print("Введіть адресу домогосподарства: ");
        scanner.nextLine(); // Пропустити залишок після введення числа
        String address = scanner.nextLine();

        Household household = new Household(address);
        householdDAO.saveHousehold(household);

        System.out.println("Домогосподарство додано успішно!");
    }

    private void viewHouseholds() {
        List<Household> households = householdDAO.getAllHouseholds();

        if (households.isEmpty()) {
            System.out.println("Немає домогосподарств для відображення.");
        } else {
            System.out.println("\nСписок домогосподарств:");
            for (Household household : households) {
                System.out.println("- ID: " + household.getId() + ", Адреса: " + household.getAddress());
            }
        }
    }
}
