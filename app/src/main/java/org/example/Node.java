package org.example;

public class Node implements Comparable<Node> {
    public final char ch;
    public final int freq;
    public final Node left, right;

    public Node(char ch, int freq) {
        this.ch= ch;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    public Node(int freq, Node left, Node right) {
        this.ch= '\0';
        this.freq = left.freq + right.freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.freq, other.freq);
    }
}
