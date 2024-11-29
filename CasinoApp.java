import java.util.ArrayList;
import java.util.List;

class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String accountCreationDate;
    private double balance;

    private static int userCount = 0; // Статическое поле для подсчёта количества пользователей

    public User(int id, String name, String username, String password, String accountCreationDate, double balance) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.accountCreationDate = accountCreationDate;
        this.balance = balance;
        userCount++; // Увеличиваем количество пользователей при создании нового объекта
    }

    public static int getUserCount() {
        return userCount; // Статический метод для получения количества пользователей
    }

    public void displayInfo() {
        System.out.printf("ID пользователя: %d\n", id);
        System.out.printf("Имя пользователя: %s\n", name);
        System.out.printf("Логин: %s\n", username);
        System.out.printf("Дата создания аккаунта: %s\n", accountCreationDate);
        System.out.printf("Баланс: %.2f рублей\n\n", balance);
    }

    public String getUsername() {
        return username;
    }
}

class Game {
    private String gameName;
    private double minBet;
    private double maxBet;
    private double payoutMultiplier;

    public Game(String gameName, double minBet, double maxBet, double payoutMultiplier) {
        this.gameName = gameName;
        this.minBet = minBet;
        this.maxBet = maxBet;
        this.payoutMultiplier = payoutMultiplier;
    }

    public void displayInfo() {
        System.out.printf("Игра: %s\n", gameName);
        System.out.printf("Минимальная ставка: %.2f рублей\n", minBet);
        System.out.printf("Максимальная ставка: %.2f рублей\n", maxBet);
        System.out.printf("Множитель выигрыша: %.2fx\n\n", payoutMultiplier);
    }

    public String getGameName() {
        return gameName;
    }
}

class Transaction {
    private int id;
    private int userId;
    private double amount;
    private String timestamp;

    public Transaction(int id, int userId, double amount, String timestamp) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма транзакции должна быть положительной");
        }
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public void displayInfo() {
        System.out.printf("ID транзакции: %d\n", id);
        System.out.printf("ID пользователя: %d\n", userId);
        System.out.printf("Сумма транзакции: %.2f рублей\n", amount);
        System.out.printf("Время транзакции: %s\n\n", timestamp);
    }
}

class GameRound {
    private User player;
    private Game game;
    private double betAmount;
    private int result;

    public GameRound(User player, Game game, double betAmount, int result) {
        this.player = player;
        this.game = game;
        this.betAmount = betAmount;
        this.result = result;
    }

    public void displayResult() {
        System.out.printf("Игрок: %s\n", player.getUsername());
        System.out.printf("Игра: %s\n", game.getGameName());
        System.out.printf("Ставка: %.2f рублей\n", betAmount);
        System.out.printf("Результат: %s\n\n", (result == 1 ? "Выигрыш" : "Проигрыш"));
    }
}

class Jackpot {
    private double currentAmount;
    private boolean isWon;

    public Jackpot(double currentAmount, boolean isWon) {
        this.currentAmount = currentAmount;
        this.isWon = isWon;
    }

    public void displayInfo() {
        System.out.printf("Текущая сумма джекпота: %.2f рублей\n", currentAmount);
        System.out.printf("Состояние джекпота: %s\n\n", (isWon ? "Сорван" : "Не сорван"));
    }
}

class Bonus {
    private String bonusType;
    private double bonusAmount;
    private boolean isActive;

    public Bonus(String bonusType, double bonusAmount, boolean isActive) {
        this.bonusType = bonusType;
        this.bonusAmount = bonusAmount;
        this.isActive = isActive;
    }

    public void displayInfo() {
        System.out.printf("Тип бонуса: %s\n", bonusType);
        System.out.printf("Сумма бонуса: %.2f рублей\n", bonusAmount);
        System.out.printf("Статус бонуса: %s\n\n", (isActive ? "Активен" : "Не активен"));
    }
}

class TransactionStatus {
    private boolean success;
    private String message;

    public TransactionStatus(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getStatus() {
        return success ? "Успешно: " + message : "Ошибка: " + message;
    }
}

public class CasinoApp {
    public static void main(String[] args) {
        try {
            User user = new User(1, "Иван Иванов", "ivanov", "password123", "2024-10-01", 100.0);
            Game game = new Game("Однорукий бандит", 5.0, 100.0, 2.5);
            Transaction transaction = new Transaction(101, 1, 50.0, "2024-10-18 15:30");
            GameRound round = new GameRound(user, game, 10.0, 1);
            Jackpot jackpot = new Jackpot(5000.0, false);
            Bonus bonus = new Bonus("Фриспины", 20.0, true);

            List<User> users = new ArrayList<>();
            users.add(user);
            users.add(new User(2, "Петр Петров", "petrov", "password456", "2024-10-02", 200.0));
            users.add(new User(3, "Сидор Сидоров", "sidorov", "password789", "2024-10-03", 300.0));

            user.displayInfo();
            game.displayInfo();
            transaction.displayInfo();
            round.displayResult();
            jackpot.displayInfo();
            bonus.displayInfo();

            for (User u : users) {
                u.displayInfo();
            }

            // Демонстрация работы с исключениями и статическим методом
            System.out.println("Количество пользователей: " + User.getUserCount());

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при создании транзакции: " + e.getMessage());
        }
    }
}
