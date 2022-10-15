import java.util.function.BiFunction;
import java.util.function.Function;

public class Employee {

    String name;
    double salary;
    double workHours;
    int hireYear;


    public Employee(String name, double salary, double workHours, int hireYear) {
        this.name = name;
        this.salary = salary;
        this.workHours = workHours;
        this.hireYear = hireYear;
    }


    //VERGİ HESAPLAMA
    Function<Double, Double> tax = salary -> salary > 1000 ? salary * 3 / 100 : 0;

    //BONUS HESAPLAMA
    Function<Double, Double> bonus = workHours -> workHours > 40 ? (workHours - 40) * 30 : 0;

    //MAAŞ ARTIŞI HESAPLA
    BiFunction<Integer, Double, Double> raiseSalary = (hireYear, salary) -> 2021 - hireYear < 10 ? (salary + bonus.apply(workHours) - tax.apply(salary)) * 5 / 100
            : 2021 - hireYear > 9 && 2021 - hireYear < 20 ? (salary + bonus.apply(workHours) - tax.apply(salary)) * 10 / 100
            : (salary + bonus.apply(workHours) - tax.apply(salary)) * 15 / 100;


    @Override
    public String toString() {
        double taxAndBonusSalary = bonus.apply(workHours) + salary - tax.apply(salary);
        double totalSalary = salary + bonus.apply(workHours);
        return name + " " +
                salary + " " +
                workHours + " " +
                hireYear + " " +
                tax.apply(salary) + " " +
                bonus.apply(workHours) + " " +
                raiseSalary.apply(hireYear, salary) + " " +
                taxAndBonusSalary + " " +
                totalSalary;
    }

    public static void main(String[] args) {
        Employee employee = new Employee("Omer", 3000, 60, 1994);
        System.out.println(employee);
    }
}
