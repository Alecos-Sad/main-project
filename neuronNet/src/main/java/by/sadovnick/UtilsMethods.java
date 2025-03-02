package by.sadovnick;

public class UtilsMethods {

    public void sendMessage(String message) {
//        System.out.println(message);
    }

    public double calculateSumWeightInNeuronNet(double in1, double w1, double in2, double w2, double dias) {
        return (in1 * w1) + (in2 * w2) + dias;
    }

    public double calculateSigmoid(double weight) {
        return 1 / (1 + Math.exp(-weight));
    }

    public double calculateError(double result, double meaning) {
        return (result - meaning) * result * (1 - result);
    }

    public double errorForWeight(double error, double resultSigmoidInNeuron) {
        return error * resultSigmoidInNeuron * (1 - resultSigmoidInNeuron);
    }

    public double calculateNewWeight(double oldWeight, double error, double training) {
        return oldWeight - (error * training);
    }
}
