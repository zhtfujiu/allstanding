public class test1 {
    private static boolean isAtRightArea,isWatchedByAlice,isAtFubiaozhen,isWatchedByFubiaozhen;

    public static void main(String[]args){
        Check c=new Check();
        //参数设定
        c.setT(270);//T
        c.setN(1000);
        c.setM(0);
        c.set_n(0);

        do {
            //随机数生成敌舰艇初始位置坐标
            c.initLocationOfBob();
            //随机生成敌潜艇航向
            c.initDirectionOfBob();
            c.set_t(0);//t

            do {
                //初始化6个声呐的坐标
                c.initLocationOfAlice();
                //生成敌潜艇当前位置坐标
                c.setNowLocationOfBob();

                //检测能否进入吊放声呐有效探测范围
                boolean[] flagArray = new boolean[6];
                for (int i = 0; i < 6; i++) {
                    flagArray[i] = c.isByAliceWatched(i);
                }
                //isAtRightArea表示是否在吊放声呐有效探测范围
                isAtRightArea = false;
                if (flagArray[0] && flagArray[1] && flagArray[2]
                        && flagArray[3] && flagArray[4] && flagArray[5]) {
                    isAtRightArea = true;
                }
                //若是在吊放声呐有效探测范围，则进行下面操作
                if (isAtRightArea) {
                    //检测是否被监听到
                    //isWatched表示是否被成功监听
                    isWatchedByAlice = false;
                    if (isAtRightArea && c.isOK()) {
                        isWatchedByAlice = true;
                    }
                    if (isWatchedByAlice) {
                        //顺利被监听，进行两个变量的加法操作
                        c.setM(c.getM() + 1);
                        c.set_t(c.get_t() + 1);
                    } else {
                        //没有监听到
                        Method_1(c);
                    }
                } else {
                    //若是【不】在吊放声呐有效探测范围，则进行下面操作
                    Method_1(c);
                }
            }while (c.get_t()<=c.getT());
            c.set_n(c.get_n()+1);
//            System.out.println(c.getN()+"HHHHHHH");
        }while (c.get_n()<=c.getN());
        //输出结果
        if (c.getN()!=0){
            float p=(float) c.getM()/c.getN();
            System.out.println("在 "+c.getN()+" 次搜索中，搜索到 "+c.getM()+" 次");
            System.out.println("搜索到的概率是："+p);
        }else {
            System.out.print("N 为0");
        }
    }
    //为了看着舒服，把重复的代码写外面
    //Method_1 检测是否进入浮标阵有效搜索范围
    public static void Method_1(Check c){
        //检测是否进入吊放声呐浮标阵有效搜索范围
        //isAtFubiaozhen表示是否进入吊放声呐浮标阵有效搜索范围
        isAtFubiaozhen=false;
        isAtFubiaozhen=c.isByFubiaozhenWatched();
        if (isAtFubiaozhen){
            //进入吊放声呐浮标阵有效搜索范围，能否被监听到？
            boolean isWatchedByFubiaozhen=false;
            isWatchedByFubiaozhen=isAtFubiaozhen && c.isOK();
            if (isWatchedByFubiaozhen){
                //顺利被监听，进行两个变量的加法操作
                c.setM(c.getM()+1);
                c.set_t(c.get_t()+1);
            }else {
                c.set_t(c.get_t()+1);
            }
        }else {
            //【没有】进入吊放声呐浮标阵有效搜索范围
            c.set_t(c.get_t()+1);
        }
    }

}
