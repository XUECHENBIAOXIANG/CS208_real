package Lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class e2 {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int x=8;
        node[] w=new node[x];
        for(int i=0;i<x;i++){
            node a=new node();
            a.name=in.next();
            a.begin=in.nextInt();
            a.end=in.nextInt();
            w[i]=a;
        }

        Arrays.sort(w, new Comparator<node>() {
            @Override
            public int compare(node o1, node o2) {
                return o1.end-o2.end;
            }
        });
        ArrayList<node> ans=new ArrayList<>();
        ans.add(w[0]);
        int finish=w[0].end;
        for (int i=1;i< w.length;i++){
            if (w[i].begin>=finish){
                finish=w[i].end;
                ans.add(w[i]);
            }
        }
        for (int i = 0; i < ans.size(); i++) {
            out.print(ans.get(i).name);
            out.print(" ");
        }

        out.close();
    }

    private static class node{
       String name;
       int begin;
       int end;
    }

    private static class QReader {
        private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
    private static class QWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

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
