package dmme.kuvid.domain.database.dbtest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HashMap<String, List<Members>> companyMap = new HashMap<>();

        Department rnd = new Department("R&D");

        Employee employee = new Employee("aycan", 123);
        Employer employer = new Employer("hasan", 934);
        Employee employee1 = new Employee("ata", 267);

        List<Members> membersList = new ArrayList<>();
        membersList.add(employee);
        membersList.add(employer);
        membersList.add(employee1);

        rnd.setMembersList(membersList);
        companyMap.put(rnd.getName(), rnd.getMembersList());

        System.out.println("Original Map: " + companyMap);

        // Serialization
        Gson gson = new Gson();
        String jsonString = gson.toJson(companyMap);
        System.out.println("SERIALIZED");
        System.out.println("Company Info: " + jsonString);

        // Deserialization
        System.out.println("DESERIALIZED");
        Type type = new TypeToken<HashMap<String, List<?>>>(){}.getType();
        HashMap<String, List<Members>> clonedMap = gson.fromJson(jsonString, type);
        System.out.println("Company Info: " + clonedMap);
    }
}
