% 鸢尾花(iris)数据集训练BP神经网络
% Author: Mr.King
% Email: 18329960625@163.com

% Step1：读取数据。采用textread函数读取文本数据
[f1, f2, f3, f4, class] = textread('trainData.txt','%f%f%f%f%f',75);
% Step2：特征值归一化。将数据映射至区间[0,1]或者[-1,-1]
[input, minI, maxI] = premnmx( [f1, f2, f3, f4 ]');
% Step3：构造输出矩阵。用于对训练数据的目标输出结果进行存储
s = length(class);
output = zeros(s, 3);
for i = 1:s
    output(i,class(i)) = 1;
end
% Step4：创建神经网络。利用matlab自带函数newff实现神经网络的创建，第一层10个神经元，第二层3个神经元，其中第一层传递函数为logsig，输出层的传递函数为linear，
% minmax()函数获取数组中每一行的最小值和最大值，即s行2列，用于表示输入向量的每个元素的范围从min到max
net = newff(minmax(input),[10 3],{ 'logsig' 'purelin' },'traingdx');
% Step5：设置训练参数。设置网络训练次数为1000次，显示频率为30次，训练最小目标误差0.01和学习率0.01
net.trainparam.epochs = 1000;
net.trainparam.show = 30 ;
net.trainparam.goal = 0.01 ;
net.trainParam.lr = 0.01 ;
% Step6：模型训练
model = train(net, input, output');
% Step7：读取测试数据，并将数据归一化。
[t1 t2 t3 t4 c] = textread('testData.txt','%f%f%f%f%f',75);
testInput = tramnmx([t1, t2, t3, t4]',minI,maxI);
% Step8：使用训练好的网络model对测试数据进行仿真
Y = sim(model, testInput);
% Step9：统计识别测试结果的正确率
[s1, s2] = size(Y);
hitNum = 0;
for i=1:s2
    [m,Index] = max( Y(:,i)) ;
    if(Index == c(i)) 
        hitNum = hitNum + 1 ; 
    end
end
sprintf('模型识别率是 %3.3f%%',100 * hitNum / s2 )
% Step10：输出显示训练后的权值和阈值，其中model.iw显示网络的权重，model.b显示训练后的神经网络的偏置值
iw1 = model.IW{1};
b1 = model.b{1};
iw2 = model.IW{2};
b2 = model.b{2};