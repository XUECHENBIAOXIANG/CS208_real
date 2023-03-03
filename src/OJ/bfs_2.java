package OJ;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bfs_2 {
    static int quan=-1;

    public static void main(String[] args) {
        QReader in= new QReader();
        QWriter out= new QWriter();
        int T= in.nextInt();
        for (int i = 0; i < T; i++) {
            int n= in.nextInt();
            int m= in.nextInt();
            node[] tu=new node[2*n];//进=chu+n
            for (int j=0;j<2*n;j++){
                tu[j]=new node();
            }
            for (int j = 0; j < m; j++) {
                int a = in.nextInt()-1;
                int b = in.nextInt()-1+n;
                tu[a].lian.add(b);
                tu[b].lian.add(a);
            }
            for (int j = 0; j < n; j++) {
                 bfs(tu,tu[j]);
                 System.out.println(j+"  "+quan);
                for (int k = 0; k < 2*n; k++) {
                    tu[k].isvisited=false;
                    tu[k].distance=0;
                }
                if (quan==4){
                    break;
                }
            }
            out.println(quan);

           quan=-1;


            
        }


        out.close();

    }
    static class node{
        ArrayList<Integer> lian=new ArrayList<Integer>();
        int distance;
        boolean isvisited;
    }
    public static void bfs(node[]nodes,node start){
        start.distance=0;
        Queue<node> queue=new LinkedList<>();
        queue.offer(start);
        start.isvisited=true;
        while (!queue.isEmpty()){
            node a=queue.poll();
            for (int i=0;i<a.lian.size();i++){
                int next=a.lian.get(i);
                if (!nodes[next].isvisited){
                    nodes[next].isvisited=true;
                    queue.offer(nodes[next]);
                    nodes[next].distance=a.distance+1;
                }else {
                    if (a.distance+1==nodes[next].distance){
                        if (quan==-1){
                            quan=2*(a.distance+1);
                        }else {
                            if (quan>2*(a.distance+1)){
                                quan=2*(a.distance+1);
                            }
                        }

                        break;
                    }
                }
            }
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
