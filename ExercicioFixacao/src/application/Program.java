package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Map<String, Integer> urna = new TreeMap<>();


        System.out.print("Digite o caminho do Arquivo: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();

            while (line != null) {

                String[] fields = line.split(",");
                String name = fields[0];
                int votos = Integer.parseInt(fields[1]);

                if (urna.containsKey(name)) {

                    int votosAtuais = urna.get(name);
                    urna.put(name, votosAtuais + votos);

                } else {
                    urna.put(name, votos);
                }
                line = br.readLine();
            }
            System.out.print("\nCandidatos: ");
            for(String keys : urna.keySet()){
                System.out.print(keys + " " );
            }
            System.out.println("\n\nContagem de Votos:");
            for (String key : urna.keySet()) {
                System.out.println(key + ": " + urna.get(key));
            }
            System.out.println();
            int maiorValor = Integer.MIN_VALUE;
            String chave = "";
            for(Map.Entry<String,Integer> entry : urna.entrySet()){
                if (entry.getValue() > maiorValor ){
                    maiorValor = entry.getValue();
                    chave = entry.getKey();
                }
            }
            System.out.print("Vencedor da Eleição: \n" + chave + ": " + maiorValor + " Votos");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}