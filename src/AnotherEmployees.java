import java.time.LocalDate;
import java.util.Date;

/**
 * 3) Другие (руководство, секретари и т.д.)
 *
 *         ФИО
 *         Дата рождения
 *         Дата принятия на работу
 *         Текстовое описание сотрудника
 */
public class AnotherEmployees extends Employee{
    String employeeDescription;

    public AnotherEmployees(String fullName, LocalDate birthday, LocalDate employmentDate) {
        super(fullName, birthday, employmentDate);
    }
}
