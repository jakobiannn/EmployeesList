import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

/**
 1) Работник:

 ФИО
 Дата рождения
 Дата принятия на работу

 **/

public class Employee {
    private static int id = getCurrentId();
    public Employee(String fullName, LocalDate birthday, LocalDate employmentDate){
        System.out.println(fullName);
        System.out.println(birthday);
        System.out.println(employmentDate);
        System.out.println("SOUNDS GOOD");
    }

    public static void addEmployee(String fullName, LocalDate birthday, LocalDate employmentDate){
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("employees.xml");

            Node root = document.getDocumentElement();
            // Создаем нового сотрудника по элементам
            // Сам сотрудник <Employee>
            Element employee = document.createElement("Employee");
            // <FullName>
            Element fullNameTag = document.createElement("FullName");
            // Устанавливаем значение текста внутри тега
            fullNameTag.setTextContent(fullName);
            // <Birthday>
            Element birthdayTag = document.createElement("Birthday");
            birthdayTag.setTextContent(String.valueOf(birthday));
            // <employmentDate>
            Element employmentDateTag = document.createElement("employmentDate");
            employmentDateTag.setTextContent(String.valueOf(employmentDate));
            //id
            Element idTag = document.createElement("id");
            idTag.setTextContent(String.valueOf(++Employee.id));

            employee.appendChild(fullNameTag);
            employee.appendChild(birthdayTag);
            employee.appendChild(employmentDateTag);
            employee.appendChild(idTag);

            root.appendChild(employee);

            writeDocument(document);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteEmployee(int id){
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File("employees.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Функция для сохранения DOM в файл
    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream("employees.xml");
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static int getCurrentId() {
        int idCount = 0;
        try {
            File inputFile = new File("./employees.xml");
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = dBuilder.parse(inputFile);
            Node root = document.getDocumentElement();

            System.out.println("List of tags:");
            System.out.println();

            NodeList employees = root.getChildNodes();

            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                // Если нода не текст, то это сотрудник - заходим внутрь
                if (employee.getNodeType() != Node.TEXT_NODE) {
                    NodeList employeeProps = employee.getChildNodes();
                    for (int j = 0; j < employeeProps.getLength(); j++) {
                        Node employeeProp = employeeProps.item(j);
                        // Если нода не текст, то это один из параметров сотрудника - печатаем
                        if (employeeProp.getNodeType() == Node.TEXT_NODE) {
                            continue;
                        }
                        System.out.println(employeeProp.getNodeName());
                        System.out.println(employeeProp.getNodeName().equals("id"));
                        if (!employeeProp.getNodeName().equals("id")) {
                            continue;
                        }
                        int currentId = Integer.parseInt(employeeProp.getChildNodes().item(0).getTextContent());
                        System.out.println(currentId);
                        if (currentId > idCount) {
                            idCount = currentId;
                        }
                    }
                    System.out.println("===========>>>>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idCount;
    }

    public static void getEmployee() {
    }
//    public static Employee getEmployee(){
//
//    }
}
