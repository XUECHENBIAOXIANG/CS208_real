package Lab5;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class e1{

    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n= in.nextInt();
        int m= in.nextInt();
        node[] graph=new node[n];
        for (int i=0;i<n;i++){
            graph[i]=new node();
            graph[i].x=i;
        }
        for (int i=0;i<m;i++){
            int u=in.nextInt()-1;
            int v=in.nextInt()-1;
            graph[u].child.add(graph[v]);
            graph[v].ru++;
        }
        heap a=new heap(n+1);
        for (int i=0;i<n;i++){
            if (graph[i].ru==0){
                a.insert(graph[i]);
            }
        }
        int[] ans=new int[n];
        int t=0;
        while (a.size>0){
            node temp=a.delect();
            temp.isdect=true;
            ans[t++]=temp.x;
            for (int j=0;j<temp.child.size();j++){
                temp.child.get(j).ru--;
                if (temp.child.get(j).ru==0){
                    a.insert(temp.child.get(j));
                }
            }
        }
        boolean p=false;
        for (int i=0;i<n;i++){
            if (!graph[i].isdect){
                p=true;
            }
        }
        if (p){
            out.println("impossible");
        }else {
            for (int i=0;i<n;i++){
                out.print(ans[i]+1);
                out.print(" ");
            }
            out.println("");
        }


        out.close();
    }


    private static class node{
        boolean isdect;
        int ru=0;
        int x;
        ArrayList<node> child =new ArrayList<>();
        ArrayList<Integer> weight=new ArrayList<>();
    }
    private static class heap {
        node[] heap;
        int size = 0;
        public heap(int n) {
            heap = new node[n + 1];
        }

        public void insert(node x) {//大
            size++;
            heap[size] = x;
            int index = size;
            while (index > 1) {
                if (heap[index].x < heap[index / 2].x) {
                    node temp = heap[index];
                    heap[index] = heap[index / 2];
                    heap[index / 2] = temp;
                    index = index / 2;
                } else break;
            }
        }

        public node delect() {
            node temp = heap[size];
            heap[size] = heap[1];
            node num = heap[1];
            heap[1] = temp;
            size--;
            int index = 1;
            while (index * 2 <= size) {
                if (index * 2 + 1 <= size) {
                    if (heap[index * 2].x > heap[index * 2 + 1].x) {
                        if (heap[index].x >= heap[index * 2 + 1].x) {
                            node temp1 = heap[index];
                            heap[index] = heap[index * 2 + 1];
                            heap[index * 2 + 1] = temp1;
                            index = 2 * index + 1;
                        } else {
                            break;
                        }
                    } else {
                        if (heap[index].x >= heap[index * 2].x) {
                            node temp1 = heap[index];
                            heap[index] = heap[index * 2];
                            heap[index * 2] = temp1;
                            index = 2 * index;
                        } else {
                            break;
                        }
                    }
                } else {
                    if (heap[index].x >= heap[index * 2].x) {
                        node temp1 = heap[index];
                        heap[index] = heap[index * 2];
                        heap[index * 2] = temp1;
                        index = 2 * index;
                    } else {
                        break;
                    }
                }

            }
            return num;

        }


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
