% �β��(iris)���ݼ�ѵ��BP������
% Author: Mr.King
% Email: 18329960625@163.com

% Step1����ȡ���ݡ�����textread������ȡ�ı�����
[f1, f2, f3, f4, class] = textread('trainData.txt','%f%f%f%f%f',75);
% Step2������ֵ��һ����������ӳ��������[0,1]����[-1,-1]
[input, minI, maxI] = premnmx( [f1, f2, f3, f4 ]');
% Step3����������������ڶ�ѵ�����ݵ�Ŀ�����������д洢
s = length(class);
output = zeros(s, 3);
for i = 1:s
    output(i,class(i)) = 1;
end
% Step4�����������硣����matlab�Դ�����newffʵ��������Ĵ�������һ��10����Ԫ���ڶ���3����Ԫ�����е�һ�㴫�ݺ���Ϊlogsig�������Ĵ��ݺ���Ϊlinear��
% minmax()������ȡ������ÿһ�е���Сֵ�����ֵ����s��2�У����ڱ�ʾ����������ÿ��Ԫ�صķ�Χ��min��max
net = newff(minmax(input),[10 3],{ 'logsig' 'purelin' },'traingdx');
% Step5������ѵ����������������ѵ������Ϊ1000�Σ���ʾƵ��Ϊ30�Σ�ѵ����СĿ�����0.01��ѧϰ��0.01
net.trainparam.epochs = 1000;
net.trainparam.show = 30 ;
net.trainparam.goal = 0.01 ;
net.trainParam.lr = 0.01 ;
% Step6��ģ��ѵ��
model = train(net, input, output');
% Step7����ȡ�������ݣ��������ݹ�һ����
[t1 t2 t3 t4 c] = textread('testData.txt','%f%f%f%f%f',75);
testInput = tramnmx([t1, t2, t3, t4]',minI,maxI);
% Step8��ʹ��ѵ���õ�����model�Բ������ݽ��з���
Y = sim(model, testInput);
% Step9��ͳ��ʶ����Խ������ȷ��
[s1, s2] = size(Y);
hitNum = 0;
for i=1:s2
    [m,Index] = max( Y(:,i)) ;
    if(Index == c(i)) 
        hitNum = hitNum + 1 ; 
    end
end
sprintf('ģ��ʶ������ %3.3f%%',100 * hitNum / s2 )
% Step10�������ʾѵ�����Ȩֵ����ֵ������model.iw��ʾ�����Ȩ�أ�model.b��ʾѵ������������ƫ��ֵ
iw1 = model.IW{1};
b1 = model.b{1};
iw2 = model.IW{2};
b2 = model.b{2};