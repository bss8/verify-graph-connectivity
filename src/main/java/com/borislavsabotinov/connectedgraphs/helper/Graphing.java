package com.borislavsabotinov.connectedgraphs.helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

import java.util.HashMap;
import java.util.Random;

class Node {

    int id;
    Node parent;

    public Node(int value) {
        id = value;
        parent = null;
    }
}


class Graph {
    static int count;
    static HashMap<Integer, Node> V = new HashMap<>();
    static HashMap<String, Boolean> E = new HashMap<>();
    public static HashMap<Integer, Integer> result = new HashMap<>();
    public static int numberOfVertices = 80;
    public static int total = ((numberOfVertices * (numberOfVertices - 1)) / 2);

    public Graph() {
        int counter = 0;
        count = 1;
        int a, b;
        Random randomObj = new Random();

        for (int id = 0; id < numberOfVertices; id++) {
            Node nodeObj = new Node(id);
            V.put(id, nodeObj);
        }

        for (int edges = 0; edges < total; edges++) {

            if (count == numberOfVertices) {
                counter++;
                if (result.get(edges) != null) {
                    int temp = result.get(edges);
                    temp++;
                    result.put(edges, temp);
                } else {
                    result.put(edges, 1);
                }
                if (counter == 100000) {
                    break;
                } else {
                    count = 1;
                    E = new HashMap<>();
                    for (int i = 0; i < numberOfVertices; i++) {
                        V.get(i).parent = null;
                    }
                    edges = 0;
                }
            }
            while (true) {
                a = randomObj.nextInt(numberOfVertices);
                b = randomObj.nextInt(numberOfVertices);
                String key = a + " " + b;
                if (a != b && !E.containsKey(key)) {
                    break;
                }
            }
            union(a, b);

        }

        int mid = 0;
        for (int i = numberOfVertices - 1; i <= total + 1; i++) {
            if (result.get(i) != null) {
                System.out.println("# of Edges: " + i + " , Count: " + result.get(i));
                mid += result.get(i);
                if (mid >= 50000) {
                    System.out.println("Mid = " + i);
                    mid = 0;
                }

            }
        }
    }


    static void union(int a, int b) {
        Node root1 = find(a);
        Node root2 = find(b);
        E.put(a + " " + b, true);
        E.put(b + " " + a, true);
        if (root1.id != root2.id) {
            root1.parent = root2;
            count++;
        }
    }

    static Node find(int a) {
        Node nodeObj = V.get(a);
        if (nodeObj.parent == null) {
            return nodeObj;
        } else {
            Node root = find(nodeObj.parent.id);
            nodeObj.parent = root;
            return root;
        }
    }
}


public class Graphing extends Applet implements Runnable, KeyListener {

    Color olive = new Color(0, 0, 0);
    Color dorange = new Color(183, 6, 17);

    public void init() {
        Graph g = new Graph();

        addKeyListener(this);
    }

    public void keyPressed(KeyEvent ke) {

        repaint();
    }

    public void keyReleased(KeyEvent ke) {
    }

    public void keyTyped(KeyEvent ke) {

    }

    public void start() {

    }

    public void run() {


    }


    public void paint(Graphics g) {

        g.setColor(olive);
        g.drawLine(50, 50, 50, 550);
        g.drawLine(50, 550, 550, 550);

        g.drawLine(50, 50, 40, 50);
        g.drawString(".9", 40, 50);

        g.drawLine(50, 150, 40, 150);
        g.drawString(".7", 40, 150);

        g.drawLine(50, 250, 40, 250);
        g.drawString(".5", 40, 250);

        g.drawLine(50, 350, 40, 350);
        g.drawString(".3", 40, 350);

        g.drawLine(50, 450, 40, 450);
        g.drawString(".1", 40, 450);

        g.drawLine(50, 450, 40, 450);
        g.drawString(".00", 40, 550);

        g.drawString("P", 10, 300);


        g.drawLine(50, 550, 50, 560);
        g.drawString("0", 50, 560);

        g.drawLine(150, 550, 150, 560);
        g.drawString("100", 150, 560);

        g.drawLine(250, 550, 250, 560);
        g.drawString("200", 250, 560);

        g.drawLine(350, 550, 350, 560);
        g.drawString("300", 350, 560);

        g.drawLine(450, 550, 450, 560);
        g.drawString("400", 450, 560);

        g.drawLine(550, 550, 550, 560);
        g.drawString("500", 550, 560);

        g.drawString("# Edges", 300, 575);


        for (int i = Graph.numberOfVertices - 1; i <= Graph.total + 1; i++) {
            if (Graph.result.get(i) != null) {
                g.drawLine(50 + i, 550, 50 + i, 600 - (50 + (Graph.result.get(i) / 4)));

            }
        }


    }

    public static void main(String... args) {
        JFrame jFrame = new JFrame();
        Graphing graphing = new Graphing();
        graphing.init();
        jFrame.getContentPane().add(graphing, BorderLayout.CENTER);
        jFrame.setSize(600, 600);
        Graph graph = new Graph();

        jFrame.setVisible(true);
    }


}