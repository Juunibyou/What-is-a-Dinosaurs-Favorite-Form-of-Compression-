package org.example;

import java.util.*;

public class HuffmanTree {
    private Node root;
    private Map<Character, String> codeTable;

    public HuffmanTree(String text) {
        Map<Character, Integer> freqMap = buildFrequencyMap(text);
        this.root = buildHuffmanTree(freqMap);
        this.codeTable = new HashMap<>();
        buildCodeTable(this.root, "", this.codeTable);
    }

    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : text.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    private Node buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            Node parent = new Node(left.freq + right.freq, left, right);
            pq.add(parent);
        }
        return pq.poll();
    }

    private void buildCodeTable(Node node, String code, Map<Character, String> codeTable) {
        if (node == null) {return;}
        if (node.left == null && node.right == null) {
            codeTable.put(node.ch, code.length() > 0 ? code : "0");
            return;
        }
        buildCodeTable(node.left, code + '0', codeTable);
        buildCodeTable(node.right, code + '1', codeTable);
    }

    // Compress and Decompress methods
    public String compress(String text) {
        StringBuilder compressed = new StringBuilder();
        for (char ch : text.toCharArray()) {
            compressed.append(codeTable.get(ch));
        }
        return compressed.toString();
    }

    public String decompress(String binary) {
        StringBuilder decompressed = new StringBuilder();
        Node current = root;
        for (char bit : binary.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decompressed.append(current.ch);
                current = root;
            }
        }
        return decompressed.toString();
    }

    public void printCodeTable(Map <Character, Integer> freqMap) {
        System.out.println("Symbol\tFrequency\tHuffman Code");
        for (char c : codeTable.keySet()){
            String displayChar = (c == ' ') ? "space" : String.valueOf(c);
            System.out.println(displayChar + "\t" + freqMap.get(c) + "\t\t" + codeTable.get(c));
        }
    }

    public Map<Character, String> getCodeTable() {
        return codeTable;
    }

    public Node getRoot() {
        return root;
    }
}