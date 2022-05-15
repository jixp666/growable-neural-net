package com.example.neuralnet;
/*
author:姬小鹏
QQ：2032926026
Email: 2032926026@qq.com
*/
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class InputNeuron {
    double sample;//样本输入特征
}

class SeparatingNeuron {
    double lowerBias;//低阈值
    double upperBias;//高阈值

    public SeparatingNeuron(){
        lowerBias = Double.MAX_VALUE;
        upperBias = -Double.MAX_VALUE;
    }
}



@Slf4j(topic = "c.test2")
public class NeuralNet {
    int inNum;//输入特征维数
    int outNum;//输出特征维数
    ArrayList<InputNeuron> inputNeurons;//输入层
    ArrayList<ArrayList<SeparatingNeuron>> separatingNeurons;//分离层
    public ArrayList<ArrayList<Double>> weights;//存权值
    int maxIter;//最大迭代数
//    double growthFactor;//生长因子
    int trainingGoal;//训练目标
    int testingResult ;//测试结果
    public static String trainPath;//训练txt文件路径
    public static String testPath;//测试txt文件路径
    int[] categories;//目标类别标志
    ArrayList<double[]> lastUpdateInputNeurons;//记录最近更新权值的样本


//    ArrayList<MyFrame> myFrames;

//    MyPanel myPanel;

    public NeuralNet(int inNum, int[] categories, int maxIter) {
        this.categories = categories;
        this.inNum = inNum;//
        this.outNum = categories.length;
//        this.growthFactor = growthFactor;
        this.maxIter = maxIter;
        inputNeurons = new ArrayList<>();
        separatingNeurons = new ArrayList<>();
        weights = new ArrayList<>();
        lastUpdateInputNeurons = new ArrayList<>();

        for (int i = 0; i < outNum-1; i++) {
            separatingNeurons.add(new ArrayList<>());
            separatingNeurons.get(i).add(new SeparatingNeuron());
        }
        for (int i = 0; i < inNum; i++) {
            inputNeurons.add(new InputNeuron());
        }
        //初始化权值
        //有两种方式，随机数，或者固定值（0.5）,但经测试，固定值0.5最佳。
        for (int i = 0; i < outNum-1; i++) {
            weights.add(new ArrayList<>());
            for (int j = 0; j < inNum; j++) {
//                weights.get(i).add(Math.random());
                weights.get(i).add(0.5);
            }
        }
        for (int i = 0; i < outNum-1; i++) {
            lastUpdateInputNeurons.add(new double[inNum]);
            lastUpdateInputNeurons.add(new double[inNum]);
        }

    }

    
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        /*mnist*/
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\mnist\\test_mnist_long.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\mnist\\train_mnist_long.txt";
//        NeuralNet network = new NeuralNet(784, new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9},20);

        /*gaussian quantiles （4特征  2分类） */
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\quantilies\\test_quantiles_4_3feature.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\quantilies\\train_quantiles_4_3feature.txt";
//        NeuralNet network = new NeuralNet(4,  new int[]{ 1, 0},100);

        /*gaussian quantiles （2特征  4分类） */
//        int[] category = {3, 2, 1, 0};
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\quantilies\\test_quantiles_4.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\quantilies\\train_quantiles_4.txt";
//        NeuralNet network = new NeuralNet(2,  category,100);

//        /*blobs 4分类*/
//        int[] category = {3,2,1,0};
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\blobs\\test_blobs_4.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\blobs\\train_blobs_4.txt";
//        NeuralNet network = new NeuralNet(2, category,100);


        /*鸢尾花*/
//        int[] category = {3,1,2};
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\iris\\trainData.txt";
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\iris\\testData.txt";
//        NeuralNet network = new NeuralNet(4, category,100);




        /*以下为二分类*/

//        /*blobs二分类*/
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\blobs\\train_blobs_2.txt";
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\blobs\\test_blobs_2.txt";
//        NeuralNet network = new NeuralNet(2, new int[]{1,0},100);
//        /*心脏病数据集*/
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\heart\\heart_test.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\heart\\heart_train.txt";
//        NeuralNet network = new NeuralNet(13,  new int[]{1, 0},1000);
//
////        /*钞票数据集*/
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\banknote\\banknote_test.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\linear\\banknote\\banknote_train.txt";
//        NeuralNet network = new NeuralNet(4,  new int[]{0,1},100);
//
//
//        /*测试非线性数据集*/
//
//        /*make_moons*/
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\moons\\test.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\moons\\train.txt";
//        NeuralNet network = new NeuralNet(2,  new int[] {1,0},100);
//
//        /*make_circles*/
        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\circles\\test_circles.txt";
        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\circles\\train_circles.txt";
        NeuralNet network = new NeuralNet(2,  new int[] {1,0},100);

////        /*异或*/
//        testPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\yihuo\\yihuotest.txt";
//        trainPath = "D:\\idea\\ideaworkspace\\neural_network\\neural-net\\src\\main\\resources\\nonlinear\\yihuo\\yihuotrain.txt";
//        Network network = new Network(2, 2, new int[]{1,0});


        int bestTrainTimes= network.train();
        System.out.println("**************************   训练结果   ********************************");
        System.out.println("迭代次数:   "+bestTrainTimes);

        /*blobs*/
        System.out.println("神经网络分离层有 "+network.separatingNeurons.size()+" 层:");
        for (int m = 0; m < network.outNum-1; m++) {
            System.out.println(m+"  层长度为  "  +network.separatingNeurons.get(m).size());

            for (int i = 0; i < network.separatingNeurons.get(m).size(); i++) {
                System.out.print(network.separatingNeurons.get(m).get(i).lowerBias+",");
                System.out.print(network.separatingNeurons.get(m).get(i).upperBias+",");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("神经网络权值:");
        for (int m = 0; m < network.outNum - 1; m++) {
            System.out.println(m+"  层权值: ");
            for (int i = 0; i < network.weights.get(m).size(); i++) {
                System.out.print(network.weights.get(m).get(i)+",");

            }
            System.out.println();
        }
        System.out.println();
        System.out.println("**************************   测试结果   ********************************");

        network.predict();

        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("程序运行时间为：   " + (end - start));





    }

    /**
     * 向神经网络输入一组样本
     * @param in;扫描器
     */
    public void getOneGroupSample(Scanner in) {

        for (int i = 0; i < inNum; i++) {
            inputNeurons.get(i).sample=in.nextDouble();
        }
        trainingGoal= (int) in.nextDouble();

    }

    /**
     * 训练神经网络
     * @return
     * @throws Exception
     */

    public int train() throws Exception {

        int lastSeparatingNeuronSize=0;//最近一遍训练前，神经网络的长度
        int nowSeparatingNeuronSize = 0;//当前长度

        int bestTrainingTimes=0;//最佳训练次数
        //第一层循环，控制训练遍数。（训练完整个训练数据集记作一遍）
        //终止条件为，在训练三次以上时，分离层不再生长时终止。
        while ( bestTrainingTimes <maxIter ) {
//        while ((lastSeparatingNeuronSize < nowSeparatingNeuronSize || bestTrainingTimes<=3) && bestTrainingTimes <100 ) {

                lastSeparatingNeuronSize = 0;
            for (int i = 0; i < outNum-1; i++) {
                lastSeparatingNeuronSize += separatingNeurons.get(i).size();
            }
            bestTrainingTimes++;
            Scanner in = new Scanner(new File(trainPath));

            //第二层循环，训练一遍
            while (in.hasNext()) {
                getOneGroupSample(in);//读入一组训练样本

                int m = 0;//指向分离层的第m层
                int n = 0;//指向分离层第m层的第n个神经元
                boolean resume = false;//一组样本数据训练结束的标志，resume值为true时结束。
                ArrayList<SeparatingNeuron> activeLayer = separatingNeurons.get(0);
                ArrayList<Double> weight = weights.get(0);

                //第三层循环，训练一组
                while (!resume) {
                    double sum = 0;
                    SeparatingNeuron activeNeuron = activeLayer.get(n);
                    //求和
                    for (int i = 0; i < inNum; i++) {
                        sum += inputNeurons.get(i).sample * weight.get(i + inNum * n);
                    }
                    if (trainingGoal == categories[m]) {
                        if (sum < activeNeuron.lowerBias) {
                            activeNeuron.lowerBias = sum;
                            if (n == activeLayer.size() - 1) {
                                for (int i = 0; i < inNum; i++) {
                                    lastUpdateInputNeurons.get(2*m)[i] = inputNeurons.get(i).sample;
                                }
                            }
                            resume = true;
                        } else if (sum > activeNeuron.upperBias) {
                            resume = true;
                        } else if (n >= separatingNeurons.get(m).size() - 1) {
                            grow(m, 1);
                            n++;
                        } else {
                            n++;
                        }
                    } else {
                        if (sum > activeNeuron.upperBias) {
                            activeNeuron.upperBias = sum;
                            if (n == activeLayer.size() - 1) {
                                for (int i = 0; i < inNum; i++) {
                                    lastUpdateInputNeurons.get(2*m+1)[i] = inputNeurons.get(i).sample;
                                }
                            }
                            resume = true;
                        } else if (sum < activeNeuron.lowerBias) {
                            if(m<outNum-2){
                                m++;
                                activeLayer=separatingNeurons.get(m);
                                weight = weights.get(m);
                                n = 0;

                            }else {
                                resume = true;
                            }
                        } else if (n >= activeLayer.size() - 1) {
                            grow(m, 0);
                            n++;
                        } else {
                            n++;
                        }
                    }
                }
            }
            in.close();
            nowSeparatingNeuronSize=0;
            for (int i = 0; i < outNum-1; i++) {
                nowSeparatingNeuronSize += separatingNeurons.get(i).size();
            }

            //在训练了两遍以上的前提下 ,某遍训练结束,神经网络没有生长,且无预测失败组数,则退出循环.
            if(lastSeparatingNeuronSize==nowSeparatingNeuronSize && bestTrainingTimes>=2 ){
                if(predictToTrain()==0) break;
            }
        }
        return bestTrainingTimes;
    }


    /**
     * 神经网络生长
     * @param m;在第m层生长
     * @param category;取值0或1,表示为神经元"0"类或"1"类生长
     */
    public void grow(int m, int category) {
        int s = separatingNeurons.get(m).size() - 1;//第m层的长度
        separatingNeurons.get(m).add(new SeparatingNeuron());
        ArrayList<Double> weight = weights.get(m);//指向第m层的权值
        double[] last = lastUpdateInputNeurons.get(m + m + category);
        double max=0;
        if (category == 1) {
            for (int i = 0; i < inNum; i++) {
                max = Math.max(Math.abs(inputNeurons.get(i).sample), Math.abs(last[i]));
                if(max==0) max = 1;
                weight.add(weight.get(s * inNum + i) +   (inputNeurons.get(i).sample - last[i]) / max);
            }
        } else {
            for (int i = 0; i < inNum; i++) {
                max = Math.max(Math.abs(inputNeurons.get(i).sample), Math.abs(last[i]));
                if(max==0) max = 1;
                weight.add(weight.get(s * inNum + i) + (last[i] - inputNeurons.get(i).sample) / max);
            }
        }


    }

    /**
     * 测试神经网络
     * @return
     * @throws Exception
     */
    public int predict() throws Exception {
        Scanner in = new Scanner(new File(testPath));
        double totalGroups = 0.0;//参与预测的总样本组数
        double errorGroups = 0.0;//预测错误的样本组数
        int failureGroups=0;//预测失败，没得到任何结果的样本组数
        int correctTotalGroups = 0;
        HashMap<Integer, Integer> actualGroupsOfAnyCategory = new HashMap<>();
        HashMap<Integer, Integer> correctGroupsOfAnyCategory = new HashMap<>();
        for (int i = 0; i < categories.length; i++) {
            actualGroupsOfAnyCategory.put(categories[i], 0);
            correctGroupsOfAnyCategory.put(categories[i], 0);
        }

        while (in.hasNext()) {
            //初始化数据
            testingResult = -10;
            totalGroups++;
            getOneGroupSample(in);//读入一组样本

            actualGroupsOfAnyCategory.put(trainingGoal, actualGroupsOfAnyCategory.get(trainingGoal) + 1);
            ArrayList<SeparatingNeuron> activeLayer = separatingNeurons.get(0);
            ArrayList<Double> weight = weights.get(0);

            int m = 0;
            boolean resume = false;
            while (!resume) {
                int i;
                for (i = 0; i < activeLayer.size(); i++) {
                    /*求和*/
                    double sum = 0;
                    for (int j = 0; j < inNum; j++) {
                        sum += inputNeurons.get(j).sample * weight.get(i * inNum + j);
                    }

                    if (sum < activeLayer.get(i).lowerBias) {//剩余其他类

                        if (m < outNum - 2) {
                            m++;
                            activeLayer = separatingNeurons.get(m);
                            weight = weights.get(m);
                            i = 0;
                            break;
                        }else {
                            testingResult = categories[m+1];
                            resume = true;
                            break;
                        }

                    } else if (sum > activeLayer.get(i).upperBias) {//分出某一特定类
                        testingResult = categories[m];
                        resume = true;
                        break;
                    }
                }
                if(i==activeLayer.size()){//如果走出了某层的所有神经元，还未被分类，则分类失败，跳出循环。
                    break;
                }

            }

            if (testingResult == trainingGoal) {
                correctTotalGroups++;
                correctGroupsOfAnyCategory.put(trainingGoal, correctGroupsOfAnyCategory.get(trainingGoal) + 1);
            }else if( testingResult!=-10){
                errorGroups++;
            }else {
                failureGroups++;
            }
        }
        System.out.println("测试准确率：   "+correctTotalGroups/totalGroups);
        System.out.println("测试错误率： " + errorGroups / totalGroups);
        System.out.println("测试失败组数有:  " + failureGroups+" 组");

        for (int i : actualGroupsOfAnyCategory.keySet()){
//            System.out.println(correctGroupsOfAnyCategory.get(i)+" "+actualGroupsOfAnyCategory.get(i));
            System.out.println(i+"  类准确率： "+(double)correctGroupsOfAnyCategory.get(i)/actualGroupsOfAnyCategory.get(i));
        }

        return failureGroups;
    }

    /**
     * 用于训练的测试方法
     * @return
     * @throws Exception
     */
    private int predictToTrain() throws Exception {
        Scanner in = new Scanner(new File(testPath));
        double totalGroups = 0.0;//参与预测的总样本组数
        double errorGroups = 0.0;//预测错误的样本组数
        int failureGroups=0;//预测失败，没得到任何结果的样本组数
        int correctTotalGroups = 0;
        HashMap<Integer, Integer> actualGroupsOfAnyCategory = new HashMap<>();
        HashMap<Integer, Integer> correctGroupsOfAnyCategory = new HashMap<>();
        for (int i = 0; i < categories.length; i++) {
            actualGroupsOfAnyCategory.put(categories[i], 0);
            correctGroupsOfAnyCategory.put(categories[i], 0);
        }

        while (in.hasNext()) {
            //初始化数据
            testingResult = -10;
            totalGroups++;
            getOneGroupSample(in);//读入一组样本

            actualGroupsOfAnyCategory.put(trainingGoal, actualGroupsOfAnyCategory.get(trainingGoal) + 1);
            ArrayList<SeparatingNeuron> activeLayer = separatingNeurons.get(0);
            ArrayList<Double> weight = weights.get(0);

            int m = 0;
            boolean resume = false;
            while (!resume) {
                int i;
                for (i = 0; i < activeLayer.size(); i++) {
                    /*求和*/
                    double sum = 0;
                    for (int j = 0; j < inNum; j++) {
                        sum += inputNeurons.get(j).sample * weight.get(i * inNum + j);
                    }

                    if (sum < activeLayer.get(i).lowerBias) {//剩余其他类

                        if (m < outNum - 2) {
                            m++;
                            activeLayer = separatingNeurons.get(m);
                            weight = weights.get(m);
                            i = 0;
                            break;
                        }else {
                            testingResult = categories[m+1];
                            resume = true;
                            break;
                        }

                    } else if (sum > activeLayer.get(i).upperBias) {//分出某一特定类
                        testingResult = categories[m];
                        resume = true;
                        break;
                    }
                }
                if(i==activeLayer.size()){//如果走出了某层的所有神经元，还未被分类，则分类失败，跳出循环。
                    break;
                }

            }
            if( testingResult==-10){
                failureGroups++;
            }
        }

        return failureGroups;
    }

}
