package Lab2;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.function.Predicate;

public class Match {
    public static void main(String[] args) {
          QReader in= new QReader();
          QWriter out= new QWriter();
          int n= in.nextInt();
          String[] man= new String[n];
          String[] Woman= new String[n];
          HashMap<String,Integer> man_i= new HashMap<String,Integer>();
          HashMap<String,Integer> woman_i= new HashMap<String,Integer>();
          int[] q= new int[4 * n];
          int front=0;
          int rear=0;
          for (int i=0;i<n;i++){
              String x= in.next();
              man[i]= x;
              man_i.put(x,i);
              q[rear++]= i;
          }
          for (int i=0;i<n;i++) {
              String x = in.next();
              Woman[i] = x;

              woman_i.put(x, i);
          }
          int [][]manlike= new int[n][n];//男人偏好列表
          int [][]womanlike= new int[n][n];//女人偏好列表
          int [][]womanlike_i= new int[n][n];//直接查男人
          int []man_f= new int[n];//男人倾向于自己列表里第几个
          int []woman_f= new int[n];//原先选择的男人序号
          for (int i=0;i<n;i++){
              for (int j=0;j<n;j++){
                  manlike[i][j]=woman_i.get(in.next());
              }
          }
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                int x=man_i.get(in.next());
                womanlike[i][j]=x;
                womanlike_i[i][x]=j;
            }
        }
        for (int i=0;i<n;i++){
            woman_f[i]=-1;
            man_f[i]=-1;//还是记录编号把
        }
          while (rear!=front){
              int nan=q[front++];//man是出来的人
              while (true){
                  int woman_try=manlike[nan][++man_f[nan]];
                  if (woman_f[woman_try]==-1){
                      woman_f[woman_try]=nan;
                      break;//完成结婚
                  }else {
                      int nan2=woman_f[woman_try];
                      if (womanlike_i[woman_try][nan]<womanlike_i[woman_try][nan2]){
                          //换
                          woman_f[woman_try]=nan;
                          q[rear++]=nan2;
                          break;
                      }else {

                      }
                  }

              }
          }
          for (int i=0;i<n;i++){
              out.print(man[i]);
              out.print(" ");
              out.println(Woman[manlike[i][man_f[i]]]);
          }




          out.close();
    }
    static class QReader {
        private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private StringTokenizer tokenizer = new StringTokenizer("");

        private String innerNextLine() {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }

        public boolean hasNext() {
            while (!tokenizer.hasMoreTokens()) {
                String nextLine = innerNextLine();
                if (nextLine == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(nextLine);
            }
            return true;
        }

        public String nextLine() {
            tokenizer = new StringTokenizer("");
            return innerNextLine();
        }

        public String next() {
            hasNext();
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }
    }

    static class QWriter implements Closeable {
        private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                return;
            }
        }

        public void println(Object object) {
            try {
                writer.write(object.toString());
                writer.write("\n");
            } catch (IOException e) {
                return;
            }
        }

        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException e) {
                return;
            }
        }
    }
}
