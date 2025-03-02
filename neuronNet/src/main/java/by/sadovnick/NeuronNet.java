package by.sadovnick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NeuronNet {
    public static void main(String[] args) throws IOException {
        Process process = new Process();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ask neuro net");
        if (reader.readLine().equals("Wish you to star nuclear war?")){
            int i = 0;
            while (i < 200){
                process.process();
                i++;
                if (process.getResultProcessNeuronNet() >= 0.8){
                    System.out.println("Neuro net ask - YES");
                } else if (process.getResultProcessNeuronNet() < 0.8 && process.getResultProcessNeuronNet() > 0.3){
                    System.out.println("Neuro net ask - I dont know");
                } else if (process.getResultProcessNeuronNet() > 0.3){
                    System.out.println("Neuro net ask - NO");
                }
            }
        } else {
            System.out.println("Neuro net ask - cant aswer");
        }
        reader.close();
    }
}