import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String number;
    private String ownerName;
    private int balance = 0;
    private LocalDateTime openingDate = LocalDateTime.now();
    private boolean isBlocked = false;

    public BankAccount(String name) {
        this.ownerName = name;

        Random random = new Random();
        StringBuilder stb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            stb.append(random.nextInt(10));
        }
        this.number = stb.toString();
    }

    public boolean deposit(int amount) {
        if (!isBlocked && amount > 0) {
            this.balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(int amount) {
        if (!isBlocked && amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        else return false;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (!isBlocked && amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            otherAccount.balance += amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return Objects.equals(this.number, ((BankAccount) o).number);
    }

    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return "Номер счета: " + this.number +
                "\nВладелец: " + this.ownerName +
                "\nБаланс: " + this.balance +
                "\nДата открытия счета: " + this.openingDate +
                (isBlocked ? "\nЗаблокирован" : "\nНе заблокирован");
    }
}
