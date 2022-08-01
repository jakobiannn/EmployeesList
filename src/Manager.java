import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *         2) Менеджер
 *
 *         ФИО
 *         Дата рождения
 *         Дата принятия на работу
 *         Список работников в подчинении данного менеджера
 */
public class Manager extends Employee {
    ArrayList<Employee> subordinates = new ArrayList<>();

    public Manager(String fullName, LocalDate birthday, LocalDate employmentDate) {
        super(fullName, birthday, employmentDate);
    }
}
