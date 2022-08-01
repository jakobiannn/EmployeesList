import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String relativePath = "./employees.xml";
        File file = new File(relativePath);
        if(file.createNewFile()){
            System.out.printf("File %s created successfully \n", "employees.xml");
        }else System.out.printf("File %s already exist \n", "employees.xml");

        System.out.println("Enter your option to use");
        System.out.println("1. Add employee");
        System.out.println("2. Delete employee");
        System.out.println("3. Change employee type");
        System.out.println("4. Link employee to manager");
        System.out.println("5. Sort employees by surname");
        System.out.println("6. Sort employees by employment date");
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1 -> {

                System.out.println("Print your filepath (file should consist fullName, birthday, employmentDate in " +
                        "every next line with this order)");
                String path = scanner.nextLine();
                try {
                    Scanner sc = new Scanner(new File(path));
                    String fullName = sc.nextLine();
                    String birthday = sc.nextLine();
                    String employmentDate = sc.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                    Employee.addEmployee(fullName, LocalDate.parse(birthday, formatter), LocalDate.parse(employmentDate, formatter));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                System.out.println("Print employee id to delete");
                try {
                    int employeeId = scanner.nextInt();
                    scanner.nextLine();
                    Employee.deleteEmployee(employeeId);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
//    LocalDate myDate =LocalDate.parse("2014-02-14");
//    // or
//    LocalDate myDate2 = new LocalDate(2014, 2, 14);
//    // or, in JDK 8+ Time
//    LocalDate myDate3 = LocalDate.of(2014, 2, 14);
/**
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            // создаем пустой объект Document, в котором будем
            // создавать наш xml-файл
            Document doc = builder.newDocument();
            // создаем корневой элемент
            Element rootElement =
                    doc.createElementNS("https://javadevblog.com/language", "Languages");
            // добавляем корневой элемент в объект Document
            doc.appendChild(rootElement);

            // добавляем первый дочерний элемент к корневому
            rootElement.appendChild(getLanguage(doc, "1", "Java", "21"));

            //добавляем второй дочерний элемент к корневому
            rootElement.appendChild(getLanguage(doc, "2", "C", "44"));

            //создаем объект TransformerFactory для печати в консоль
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // для красивого вывода в консоль
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //печатаем в консоль или файл
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("/Users/prologistic/languages.xml"));

            //записываем данные
            transformer.transform(source, console);
            transformer.transform(source, file);
            System.out.println("Создание XML файла закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
 **/
    }
}
