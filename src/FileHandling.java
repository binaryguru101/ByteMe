import java.io.*;
import java.net.IDN;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileHandling {

    private static final String FILE = "users.csv";

    
    public static void Register(String Username, String Password, int ID, Map<Integer, String> PreviousOrders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            String previousOrdersString = PreviousOrders.entrySet().stream()
                    .map(entry -> entry.getKey() + ":" + entry.getValue())
                    .collect(Collectors.joining("|"));
            writer.write(ID + "," + Username + "," + Password+","+previousOrdersString); 
            writer.newLine(); 
        } catch (IOException e) {
            System.out.println("An error occurred during registration: " + e.getMessage());
        }
    }
    public static Customer Login(int ID, String Password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                
                if (Integer.parseInt(parts[0])==ID && parts[2].equals(Password)) {
                    String Name  = parts[1];

                    
                    Map<Integer, String> previousOrders = new HashMap<>();
                    if (parts.length > 3) {
                        String[] orderParts = parts[3].split("\\|");
                        for (String orderEntry : orderParts) {
                            String[] entry = orderEntry.split(":");
                            previousOrders.put(Integer.parseInt(entry[0]), entry[1]);
                        }
                    }

                    
                    return new Customer(ID, Name, Password, previousOrders);
                }
            }
        } catch (IOException e) {
            System.out.println("Login error: " + e.getMessage());
        }
        return null; 
    }

    public static void updateCustomerOrders(int ID, String Username, String Password, Map<Integer, String> PreviousOrders) {
        try {
            

            Path path = Paths.get(FILE);
            List<String> lines = Files.readAllLines(path);

            
            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");

                
                if (parts[0].equals(String.valueOf(ID)) &&
                        parts[1].equals(Username) &&
                        parts[2].equals(Password)) {

                    
                    String updatedOrdersString = PreviousOrders.entrySet().stream()
                            .map(entry -> entry.getKey() + ":" + entry.getValue())
                            .collect(Collectors.joining("|"));

                    
                    String updatedLine = String.format("%d,%s,%s,%s",
                            ID, Username, Password, updatedOrdersString);

                    
                    lines.set(i, updatedLine);
                    break;
                }
            }

            
            Files.write(path, lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        } catch (IOException e) {
            System.err.println("Error updating orders: " + e.getMessage());
        }
    }
}
