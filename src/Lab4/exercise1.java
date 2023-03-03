package Lab4;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class exercise1 {
    public static void main(String[] args) {
        QReader in=new QReader();
        QWriter out=new QWriter();
        int n=in.nextInt();
        node1[] graph=new node1[n];
        boolean [] enter=new boolean[n];
        for (int i=0;i<n;i++){
            graph[i]=new node1();
            graph[i].index=i;
        }
        for (int i=0;i<n;i++){
            int u= i;
            int v= in.nextInt()-1;
            int w= 1;
            graph[u].child.add(graph[v]);
            graph[u].weight.add(w);
        }
        for (int i=0;i<n-1;i++){
            int u= i;
            int v= i+1;
            int w= 1;
            graph[u].child.add(graph[v]);
            graph[u].weight.add(w);
        }
        for (int i=0;i<n-1;i++){
            int u= i+1;
            int v= i;
            int w= 1;
            graph[u].child.add(graph[v]);
            graph[u].weight.add(w);
        }
        heap b=new heap(2*n);
        boolean []chu=new boolean[n];
        node a=new node();
        a.gen=graph[0];
        a.x=0;
        b.insert(a);
        while (b.size>0){//dj
            node temp= b.delect();
            if (!chu[temp.gen.index]){
                chu[temp.gen.index]=true;
                for (int i=0;i<temp.gen.child.size();i++){
                    if (temp.gen.child.get(i).yuan==0|temp.gen.child.get(i).yuan>temp.gen.weight.get(i)+ temp.x){
                        temp.gen.child.get(i).yuan=temp.gen.weight.get(i)+ temp.x;
                        node dd=new node();
                        dd.gen=temp.gen.child.get(i);
                        dd.x=temp.gen.weight.get(i)+ temp.x;
                        b.insert(dd);
                    }
                }
            }else {
            }
        }
        System.out.println(0);
        for (int i=1;i<n;i++){
            System.out.println(graph[i].yuan);
        }




    }

    private static class node1{
        long yuan=0;
        int index;
        ArrayList<node1> child =new ArrayList<>();
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

    private static class node {
        long x;
        node1 gen;
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
