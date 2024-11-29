#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <cstring>
#include <stdexcept> // Для исключений
#include <string>

using namespace std;

// 1. Класс для представления пользователя
class User {
private:
    int id;                        // ID пользователя
    char name[50];                 // Имя пользователя
    char username[50];             // Логин
    char password[50];             // Пароль
    char accountCreationDate[20];  // Дата создания аккаунта
    double balance;                // Баланс
    static int userCount;          // Статическое поле для подсчета пользователей

public:
    User(int userId, const char* userName, const char* userUsername,
        const char* userPassword, const char* creationDate, double userBalance) {
        id = userId;
        strncpy(name, userName, sizeof(name));
        strncpy(username, userUsername, sizeof(username));
        strncpy(password, userPassword, sizeof(password));
        strncpy(accountCreationDate, creationDate, sizeof(accountCreationDate));
        balance = userBalance;
        userCount++;  // Увеличиваем количество пользователей при создании нового объекта
    }

    // Статический метод для получения количества пользователей
    static int getUserCount() {
        return userCount;
    }

    // Геттер для имени пользователя
    const char* getUsername() const {
        return username;
    }

    // Перегрузка оператора присваивания
    User& operator=(const User& other) {
        if (this != &other) {
            id = other.id;
            strncpy(name, other.name, sizeof(name));
            strncpy(username, other.username, sizeof(username));
            strncpy(password, other.password, sizeof(password));
            strncpy(accountCreationDate, other.accountCreationDate, sizeof(accountCreationDate));
            balance = other.balance;
        }
        return *this;
    }

    // Конструктор копии
    User(const User& other) {
        id = other.id;
        strncpy(name, other.name, sizeof(name));
        strncpy(username, other.username, sizeof(username));
        strncpy(password, other.password, sizeof(password));
        strncpy(accountCreationDate, other.accountCreationDate, sizeof(accountCreationDate));
        balance = other.balance;
    }

    void displayInfo() const {
        printf("ID пользователя: %d\n", id);
        printf("Имя пользователя: %s\n", name);
        printf("Логин: %s\n", username);
        printf("Дата создания аккаунта: %s\n", accountCreationDate);
        printf("Баланс: %.2f рублей\n\n", balance);
    }

    // Дружественная функция для получения информации о пользователе
    friend void displayUserInfo(const User& user);
};

// Статическое поле для подсчета пользователей
int User::userCount = 0;

// Дружественная функция для вывода информации о пользователе
void displayUserInfo(const User& user) {
    cout << "Дружественная функция:" << endl;
    user.displayInfo();
}

// 2. Класс для представления игры казино
class Game {
private:
    string gameName;       // Название игры
    double minBet;         // Минимальная ставка
    double maxBet;         // Максимальная ставка
    double payoutMultiplier; // Множитель выигрыша

public:
    Game(const string& name, double min, double max, double multiplier)
        : gameName(name), minBet(min), maxBet(max), payoutMultiplier(multiplier) {}

    void displayInfo() const {
        printf("Игра: %s\n", gameName.c_str());
        printf("Минимальная ставка: %.2f рублей\n", minBet);
        printf("Максимальная ставка: %.2f рублей\n", maxBet);
        printf("Множитель выигрыша: %.2fx\n\n", payoutMultiplier);
    }

    // Перегрузка оператора <<
    friend ostream& operator<<(ostream& out, const Game& game) {
        out << "Игра: " << game.gameName << "\nМинимальная ставка: " << game.minBet
            << "\nМаксимальная ставка: " << game.maxBet << "\nМножитель выигрыша: "
            << game.payoutMultiplier << "x" << endl;
        return out;
    }
};

// 3. Класс для представления транзакции
class Transaction {
private:
    int id;                  // ID транзакции
    int userId;              // ID пользователя
    double amount;           // Сумма транзакции
    string timestamp;        // Время транзакции

public:
    Transaction(int transId, int usrId, double transAmount, const string& time)
        : id(transId), userId(usrId), amount(transAmount), timestamp(time) {}

    void displayInfo() const {
        printf("ID транзакции: %d\n", id);
        printf("ID пользователя: %d\n", userId);
        printf("Сумма транзакции: %.2f рублей\n", amount);
        printf("Время транзакции: %s\n\n", timestamp.c_str());
    }

    // Перегрузка оператора +
    Transaction operator+(const Transaction& other) const {
        if (this->userId == other.userId) {
            return Transaction(this->id, this->userId, this->amount + other.amount, this->timestamp);
        }
        else {
            throw invalid_argument("Транзакции принадлежат разным пользователям.");
        }
    }
};

// Основная функция программы
int main() {
    setlocale(LC_ALL, "rus");

    try {
        User user1(1, "Иван Иванов", "ivanov", "password123", "2024-10-01", 100.0);
        User user2(2, "Петр Петров", "petrov", "password456", "2024-10-02", 200.0);

        Game game("Однорукий бандит", 5.0, 100.0, 2.5);
        Transaction transaction1(101, 1, 50.0, "2024-10-18 15:30");
        Transaction transaction2(102, 1, 30.0, "2024-10-19 10:15");

        // Перегрузка оператора +
        Transaction transaction3 = transaction1 + transaction2;

        // Дружественная функция
        displayUserInfo(user1);

        // Статический метод
        cout << "Количество пользователей: " << User::getUserCount() << endl;

        // Перегрузка оператора <<
        cout << game;

        // Исключение
        if (user1.getUsername() == "ivanov") {
            throw runtime_error("Ошибка: Логин 'ivanov' не разрешен.");
        }

    }
    catch (const exception& e) {
        cout << "Произошла ошибка: " << e.what() << endl;
    }

    return 0;
}
