/*
1) Scrivere un metodo Java inizialiParole(String fileIn, String fileOut) che:
- Prende in input due percorsi di file di testo sottoforma di stringa.
- Legge il fileIn e genera una mappa le cui chiavi sono le lettere iniziali delle parole
contenute nel file di testo sottoforma di char e i valori sono gli insiemi di quelle paro-
le che iniziano con
tale lettera.
ESEMPIO: se il file di testo contiene "il cane cammina per strada", la mappa sarà
{'i': ("il"), 'c': ("cane", "cammina"), 'p':("per"), 's':("strada")}
- Scrive su un file con indirizzo fileOut, riga per riga, ogni coppia della mappa.
 */

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SecondoCP {

        public static void inizialiParole(String fileIn, String fileOut) throws IOException {
            FileReader fileReader = new FileReader(fileIn);
            BufferedReader br = new BufferedReader(fileReader);
            Map<Character, Set<String>> mappa = new HashMap<>();
            while(br.ready()) {
                String riga = br.readLine();
                riga = riga.trim().toLowerCase().replaceAll("[^a-z\s]", "");
                String[] parole = riga.split(" ");
                for (String parola : parole) {
                    char iniziale = parola.charAt(0);
                    if (mappa.containsKey(iniziale)) {
                        Set<String> oldParole = mappa.get(iniziale);
                        oldParole.add(parola);
                        mappa.put(iniziale, oldParole);
                    }
                    else  {
                        Set<String> oldParole = new HashSet<>();
                        oldParole.add(parola);
                        mappa.put(iniziale, oldParole);
                    }
                }
            }
            fileReader.close();
            FileWriter fileWriter = new FileWriter(fileOut);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Character c : mappa.keySet()) {
                bw.write(c + " " + mappa.get(c));
            }
            bw.close();
        }

}
