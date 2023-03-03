package OJ;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_1 {
    static int []cun;
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n= in.nextInt();
        int m= in.nextInt();
        int p= in.nextInt();
        int q= in.nextInt();
        Village[] villages = new Village[n];
        for (int i = 0; i < n; i++) {
            villages[i]=new Village(i);
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            villages[a].roads.add(b);
            villages[b].roads.add(a);
        }
        cun=new int[n];
        cun[0]=1;
        bfs(villages,villages[p-1]);
        for (int i=1;i<n;i++){
            cun[i]=cun[i]+cun[i-1];
        }

        for (int i = 0; i < q; i++) {
            int x= in.nextInt();
            if (x>=n){
                out.print(cun[n-1]);
                out.print(" ");
            }else {
                out.print(cun[x]);
                out.print(" ");
            }
        }



     out.close();

    }
    public static void bfs(Village[] villages, Village s){
        Queue<Village> queue = new LinkedList<Village>();
        queue.offer(s);
        s.visited=true;
        s.tian=0;
        while (!queue.isEmpty()){
            Village v = queue.poll();
            for (int i = 0; i <v.roads.size() ; i++) {
                int next=v.roads.get(i);
                if (!villages[next].visited){
                    villages[next].visited=true;
                    queue.offer(villages[next]);
                    villages[next].tian= v.tian+1;
                    cun[v.tian+1]++;
                }
            }

        }

    }



    static class Village {
        int num;
        int tian;
        ArrayList<Integer> roads;
        boolean visited;
        public Village(int num) {
            this.num = num;
            roads = new ArrayList<Integer>();
            visited = false;
        }
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

