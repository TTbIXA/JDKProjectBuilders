package org.example;

import java.util.HashMap;
import java.util.Random;

public class MontyHallSimulator {private static final Random random = new Random();

    public static void main(String[] args) {
        int totalGames = 1000;
        HashMap<Integer, String> results = new HashMap<>();

        for (int i = 1; i <= totalGames; i++) {
            boolean playerWinsIfStay = playGame(false);
            boolean playerWinsIfSwitch = playGame(true);

            String result = "Game " + i +
                    " - Stay: " + (playerWinsIfStay ? "Win" : "Lose") +
                    ", Switch: " + (playerWinsIfSwitch ? "Win" : "Lose");
            results.put(i, result);
        }

        printResults(results);
    }

    private static boolean playGame(boolean switchChoice) {
        // Инициализация дверей
        boolean[] doors = new boolean[3];
        int carDoorIndex = random.nextInt(3);
        doors[carDoorIndex] = true; // За одной дверью находится машина

        // Игрок выбирает случайную дверь
        int playerChoice = random.nextInt(3);

        // Открываем одну из оставшихся дверей, за которой нет машины
        int hostChoice = getHostChoice(doors, playerChoice);

        // Если игрок решает поменять выбор
        if (switchChoice) {
            playerChoice = 3 - playerChoice - hostChoice; // Переключаемся на другую дверь
        }

        // Проверяем, выиграл ли игрок
        return doors[playerChoice];
    }

    private static int getHostChoice(boolean[] doors, int playerChoice) {
        for (int i = 0; i < doors.length; i++) {
            // Если это не выбранная дверка игрока и за ней нет машины
            if (i != playerChoice && !doors[i]) {
                return i; // Возвращаем индекс открываемой двери
            }
        }
        return -1; // Это не должно происходить
    }

    private static void printResults(HashMap<Integer, String> results) {
        int stayWins = 0;
        int switchWins = 0;
        int stayLosses = 0;
        int switchLosses = 0;

        for (String result : results.values()) {
            System.out.println(result);
            stayWins += result.contains("Stay: Win") ? 1 : 0;
            stayLosses += result.contains("Stay: Lose") ? 1 : 0;
            switchWins += result.contains("Switch: Win") ? 1 : 0;
            switchLosses += result.contains("Switch: Lose") ? 1 : 0;
        }

        System.out.println("\nСтатистика:");
        System.out.println("Победы при оставлении выбора: " + stayWins + " из " + (stayWins + stayLosses));
        System.out.println("Победы при переключении: " + switchWins + " из " + (switchWins + switchLosses));
    }
}

