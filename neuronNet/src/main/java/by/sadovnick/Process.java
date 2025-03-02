package by.sadovnick;

import java.util.Random;

public class Process {

    UtilsMethods utils = new UtilsMethods();
    Random rand = new Random();

    //answer YES
    double in1 = 0;
    //answer NO
    double in2 = 1;

    double N1;
    double N2;

    double resultProcessNeuronNet;

    public double getResultProcessNeuronNet() {
        return resultProcessNeuronNet;
    }

    double w11 = rand.nextDouble();
    double w12 = rand.nextDouble();
    double w21 = rand.nextDouble();
    double w22 = rand.nextDouble();

    double v1 = rand.nextDouble();
    double v2 = rand.nextDouble();

    double dias = 0.1;
    double trainingSpeed = 0.5;

    double meaning = 1;

    public void process() {
        utils.sendMessage("v1 = " + v1);
        utils.sendMessage("v2 = " + v2);
        N1 = utils.calculateSumWeightInNeuronNet(in1, w11, in2, w21, dias);
        utils.sendMessage("Взвешенная сумма нейрона N1 " + N1);
        double sigmoidN1 = utils.calculateSigmoid(N1);
        utils.sendMessage("Результат функции активации N1: " + sigmoidN1);

        N2 = utils.calculateSumWeightInNeuronNet(in1, w12, in2, w22, dias);
        utils.sendMessage("Взвешенная сумма нейрона N2 " + N2);
        double sigmoidN2 = utils.calculateSigmoid(N2);
        utils.sendMessage("Результат функции активации N2: " + sigmoidN2);

        double resultWeight = utils.calculateSumWeightInNeuronNet(sigmoidN1, v1, sigmoidN2, v2, dias);
        utils.sendMessage("Взвешенная сумма выходного нейрона: " + resultWeight);

        resultProcessNeuronNet = utils.calculateSigmoid(resultWeight);
        utils.sendMessage("Предсказание нейронной сети для текущих данных входа: " + resultProcessNeuronNet);

        utils.sendMessage("-= Запущена работа по обратному распространению ошибки =-");

        double error = utils.calculateError(resultProcessNeuronNet, meaning);

        utils.sendMessage("Ошибка в предсказанном значении выходного нейрона: " + error);

        double errorForOutputNeuron = utils.errorForWeight(error, resultProcessNeuronNet); //градиент ошибки для каждого веса

        double errorForV1 = errorForOutputNeuron * sigmoidN1;
        utils.sendMessage("Производная ошибки по весу v1: " + errorForV1); //насколько нужно уменьшить вес
        double errorForV2 = errorForOutputNeuron * sigmoidN2;
        utils.sendMessage("Производная ошибки по весу v2: " + errorForV2); //насколько нужно уменьшить вес

        v1 = utils.calculateNewWeight(v1, errorForV1, trainingSpeed);
        utils.sendMessage("Обновленный вес выходного слоя v1: " + v1);
        v2 = utils.calculateNewWeight(v2, errorForV2, trainingSpeed);
        utils.sendMessage("Обновленный вес выходного слоя v2: " + v2);

        double errorN1 = error * v1;
        utils.sendMessage("Ошибка в предсказанном значении взвешенной суммы нейрона N1: " + errorN1);

        double errorForW11 = utils.errorForWeight(errorN1, sigmoidN1) * in1;
        utils.sendMessage("Производная ошибки по весу w11: " + errorForW11);
        double errorForW21 = utils.errorForWeight(errorN1, sigmoidN1) * in2;
        utils.sendMessage("Производная ошибки по весу w21: " + errorForW21);

        w11 = utils.calculateNewWeight(w11, errorForW11, trainingSpeed);
        utils.sendMessage("Обновленный вес для расчета внутреннего слоя w11: " + w11);
        w21 = utils.calculateNewWeight(w21, errorForW21, trainingSpeed);
        utils.sendMessage("Обновленный вес для расчета внутреннего слоя w21: " + w21);

        double errorN2 = error * v2;
        utils.sendMessage("Ошибка в предсказанном значении взвешенной суммы нейрона N2: " + errorN2);

        double errorForW12 = utils.errorForWeight(errorN2, sigmoidN2) * in1;
        utils.sendMessage("Производная ошибки по весу w12: " + errorForW12);
        double errorForW22 = utils.errorForWeight(errorN2, sigmoidN2) * in2;
        utils.sendMessage("Производная ошибки по весу w22: " + errorForW22);

        w12 = utils.calculateNewWeight(w12, errorForW12, trainingSpeed);
        utils.sendMessage("Обновленный вес для расчета внутреннего слоя w21: " + w21);
        w22 = utils.calculateNewWeight(w22, errorForW22, trainingSpeed);
        utils.sendMessage("Обновленный вес для расчета внутреннего слоя w22: " + w22);

    }
}
