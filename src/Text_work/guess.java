package Text_work;

public class guess {
    static int max=50;                     //定义默认大小
    static int[] x=new int[max];           //判断颜色是否相同
    static int[][] map= {              //该地图的邻接矩阵
            {0,0,0,1,1,1,1,1,1,1,1,1,1},
            {0,0,0,0,0,1,1,1,1,1,1,1,1},
            {0,0,0,0,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,1,1,1,0,0,1,1},
            {1,1,1,0,0,0,1,1,1,0,1,1,1},
            {1,1,1,1,1,1,0,0,0,1,1,1,1},
            {1,1,1,1,1,1,0,0,0,1,1,1,1},
            {1,1,1,1,1,1,0,0,0,1,1,0,1},
            {1,1,1,1,0,0,1,1,1,0,0,1,1},
            {1,1,1,1,0,1,1,1,1,0,0,1,1},
            {1,1,1,1,1,1,1,1,0,1,1,0,0},
            {1,1,1,1,1,1,1,1,1,1,1,0,0}
    }; //图的邻接矩阵
    static int sum=0;                      //记录解的个数
    static int n,m;                    //n 为节点个数   m是色号种数  edge是边数

    public static void main(String [] args) {
        n=13;//六个节点
        m=7;//七种颜色
        backTrack(0);
    }

    private static void backTrack(int t) {        //第t个节点
        if(t>=n) {     //达到叶子节点  记录可行解 且sum++
            sum++;
            System.out.println("第"+sum+"种方案:");
            for(int i=0;i<n;i++) {
                System.out.print((x[i]+1)+" ");          //打印出第sum种方案具体解向量
            }
            System.out.println();
        }else {                                     //每个节点尝试m种颜色  按顺序尝试
            for(int i=0;i<m;i++) {
                x[t]=i;
                if(ok(t))                            //判断是否与前t-1个节点是否相连却不同色
                    backTrack(t+1);              // yes的话则  继续搜索下一扩展节点   no则继续循环
            }
        }
    }


    /*判断是否与前t-1个节点是否相连却不同色*/
    private static boolean ok(int t) {
        for(int j=0;j<t;j++) {                           //与前t-1个节点进行比较
            if(map[t][j]==1) {                       //t与j相连
                if(x[t]==x[j])        //判断色号是否相同   若同  则返回false
                    return false;
            }
        }
        return true;
    }

}