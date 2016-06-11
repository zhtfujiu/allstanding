public class Check {
    //常量T N
    private int T,N,n,m;
    //时间t
    private int t=0;
    //坐标圆心和半径
    private static final double CENTER_X=100,CENTER_Y=80;
    private static final double RADIUS=13.6853333;
    //敌潜艇坐标
    private double X_Bob,Y_Bob;
    //敌潜艇航向
    private double Direction_Bob;
    //敌潜艇速度
    private double Speed_Bob;
    //吊放声呐当前探测点位置坐标
    private double[] X_Alice=new double[6],Y_Alice=new double[6];
    //声呐探测范围
    private static final double Length_Alice=4.3;
    //浮标阵
    private double X_Fubiaozhen=100,Y_Fubiaozhen=80,R_Fubiaozhen=10;
    //设置检测概率
    private static final int Gailv=80;

    //初始化5个声呐的坐标
    public void initLocationOfAlice(){
        X_Alice[0]=94.58;
        Y_Alice[0]=68.7;

        X_Alice[1]=90.04;
        Y_Alice[1]=72.39;

        X_Alice[2]=87.84;
        Y_Alice[2]=76.99;

        X_Alice[3]=87.78;
        Y_Alice[3]=82.75;

        X_Alice[4]=90.17;
        Y_Alice[4]=80.77;

        X_Alice[5]=94.74;
        Y_Alice[5]=91.37;


    }
    //随机数生成敌舰艇初始位置坐标
    public void initLocationOfBob(){
        //先确定横坐标
        X_Bob= CENTER_X-RADIUS+Math.random()*((CENTER_X+RADIUS)-(CENTER_X-RADIUS)+1);
        //勾股定理确定Y范围
        double half_of_Y,min_Y,max_Y;
        half_of_Y=Math.sqrt(RADIUS*RADIUS-(100-X_Bob)*(100-X_Bob));
        min_Y=80-half_of_Y;
        max_Y=80+half_of_Y;
        Y_Bob=min_Y+Math.random()*(max_Y-min_Y+1);
    }
    //随机生成敌潜艇航向
    public void initDirectionOfBob(){
        Direction_Bob=Math.random()*361;
    }
    //生成敌潜艇当前位置坐标
    public void setNowLocationOfBob(){
        Speed_Bob=Math.random()*21;
        //潜艇航行距离
        double length=t*Speed_Bob;
        //由三角函数确定潜艇航行的XY值
        double x=length*Math.cos(Direction_Bob*Math.PI/180);
        double y=length*Math.sin(Direction_Bob*Math.PI/180);
        X_Bob+=x;
        Y_Bob+=y;
    }

    //计算潜艇（Bob）与第i个声呐探测点（Alice）间的距离
    public double getDistanceFromAliceToBob(int i){
        double xx,yy;
        xx=(X_Alice[i]-X_Bob)*(X_Alice[i]-X_Bob);
        yy=(Y_Alice[i]-Y_Bob)*(Y_Alice[i]-Y_Bob);
        return Math.sqrt(xx+yy);
    }
    //检测是否被第i个探测点监视
    public boolean isByAliceWatched(int i){
        boolean flag=false;
        if (getDistanceFromAliceToBob(i)<=Length_Alice){
            flag=true;
        }
        return flag;
    }
    //是否在浮标阵范围
    public boolean isByFubiaozhenWatched(){
        boolean flag=false;
        double xx,yy;
        xx=(X_Fubiaozhen-X_Bob)*(X_Fubiaozhen-X_Bob);
        yy=(Y_Fubiaozhen-Y_Bob)*(Y_Fubiaozhen-Y_Bob);
        if ((xx+yy)<=100){
            flag=true;
        }
        return flag;
    }
    //用随机数来完成概率的功能
    public boolean isOK(){
        int i= (int) (Math.random()*101);
        if (i<=Gailv){
            return true;
        }else {
            return false;
        }
    }

    public int getT() {
        return T;
    }

    public void setT(int t) {
        T = t;
    }

    public int getN() {
        return N;
    }

    public void setN(int n) {
        N = n;
    }
    public void set_n(int n){
        this.n=n;
    }
    public int get_n(){
        return n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }
    public void set_t(int t){
        this.t=t;
    }
    public int get_t(){
        return t;
    }
}
