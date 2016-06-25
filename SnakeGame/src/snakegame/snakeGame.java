/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author stormdark
 */
public class snakeGame extends JPanel implements KeyListener{

    private int x1,y1,x2,y2,comida;
    private boolean izq,der,arr,aba,alerta;
    private boolean intercept1,intercept2,intercept3,intercept4,intercept5;
    private ArrayList <Integer> aleatoriosX, aleatoriosY;
    Timer timer;
    private Color c;
    public snakeGame(){
        
        setPreferredSize(new Dimension(600,600));
        setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(this);
        this.comida=20;
        this.x1=20;
        this.y1=20;
        this.x2=50;//
        this.y2=20;//
        this.izq=false;
        this.der=true;
        this.arr=false;
        this.aba=false;
        this.intercept1=false;
        this.intercept2=false;
        this.intercept3=false;
        this.intercept4=false;
        this.intercept5=false;
        this.alerta=false;
        generar();
        timer=new Timer(30,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(izq){
                    x1-=5;
                    x2-=5;
                }
                if(der){
                    x1+=5;
                    x2+=5;
                }
                if(aba){
                    y1+=5;
                    y2+=5;
                }
                if(arr){
                    y1-=5;
                    y2-=5;
                }
                if(y2==0 || y2==600 || x2==600 || x2==0){
                    timer.stop();
                    JOptionPane.showMessageDialog(null,"Juego terminado.");
                }
                repaint();
            }
        });
        timer.start();
    }
    
    public void generar(){
        aleatoriosX=new ArrayList();
        aleatoriosY=new ArrayList();
        for(int i=0;i<5;i++){
            aleatoriosX.add((int)(Math.random()*580));
            aleatoriosY.add((int)(Math.random()*580));
        }
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D)g;
        BasicStroke stroke=new BasicStroke(20f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        g2.setStroke(stroke);
        Line2D cobra=new Line2D.Double(x1,y1,x2,y2);
        g2.setPaint(c);
        g2.draw(cobra);
        
        
        Ellipse2D e1=new Ellipse2D.Double(aleatoriosX.get(0),aleatoriosY.get(0),20,20);
        Ellipse2D e2=new Ellipse2D.Double(aleatoriosX.get(1),aleatoriosY.get(1),20,20);
        Ellipse2D e3=new Ellipse2D.Double(aleatoriosX.get(2),aleatoriosY.get(2),20,20);
        Ellipse2D e4=new Ellipse2D.Double(aleatoriosX.get(3),aleatoriosY.get(3),20,20);
        Ellipse2D e5=new Ellipse2D.Double(aleatoriosX.get(4),aleatoriosY.get(4),20,20);
        
        if(intercept1==false){
            g2.setPaint(Color.GRAY);
            g2.fill(e1);
        }
        if(intercept2==false){
            g2.setPaint(Color.GRAY);
            g2.fill(e2);
        }
        if(intercept3==false){
            g2.setPaint(Color.GRAY);
            g2.fill(e3);
        }
        if(intercept4==false){
            g2.setPaint(Color.GRAY);
            g2.fill(e4);
        }
        if(intercept5==false){
            g2.setPaint(Color.GRAY);
            g2.fill(e5);
        }
        
        if(cobra.intersects(aleatoriosX.get(0), aleatoriosY.get(0),20,20)){
            alerta=true;
            intercept1=true;
            aleatoriosX.set(0,0);
            aleatoriosY.set(0,0);
        }
        if(cobra.intersects(aleatoriosX.get(1), aleatoriosY.get(1),20,20)){
            intercept2=true;
            alerta=true;
            aleatoriosX.set(1,0);
            aleatoriosY.set(1,0);
        }
        if(cobra.intersects(aleatoriosX.get(2), aleatoriosY.get(2),20,20)){
            intercept3=true;
            alerta=true;
            aleatoriosX.set(2,0);
            aleatoriosY.set(2,0);
        }
        if(cobra.intersects(aleatoriosX.get(3), aleatoriosY.get(3),20,20)){
            intercept4=true;
            alerta=true;
            aleatoriosX.set(3,0);
            aleatoriosY.set(3,0);
        }
        if(cobra.intersects(aleatoriosX.get(4), aleatoriosY.get(4),20,20)){
            intercept5=true;
            alerta=true;
            aleatoriosX.set(4,0);
            aleatoriosY.set(4,0);
        }

        if(alerta && der){
            x2=x2+comida;
            alerta=false;
            c=new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
            g2.setPaint(c);
            g2.draw(cobra);
            
        }
        if(alerta && izq){
            x2=x2-comida;
            alerta=false;
            c=new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
            g2.setPaint(c);
            g2.draw(cobra);
        }
        if(alerta && arr){
            y2=y2-comida;
            alerta=false;
            c=new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
            g2.setPaint(c);
            g2.draw(cobra);
        }
        if(alerta && aba){
            y2=y2+comida;
            alerta=false;
            c=new Color(((int)(Math.random()*255)),((int)(Math.random()*255)),((int)(Math.random()*255)));
            g2.setPaint(c);
            g2.draw(cobra);
        }
        
    }
    
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl","True");
        JFrame frame=new JFrame("Snake");
        snakeGame plantilla=new snakeGame();//plantilla
        frame.add(plantilla);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode()==KeyEvent.VK_LEFT){
            if(aba)
            {
                izq=true;
                der=false;
                aba=false;
                arr=false;
                x2=x1-(y2-y1);
                y1=y2;
            }
            if(arr)
            {
                izq=true;
                der=false;
                aba=false;
                arr=false;
                x2=x1-(y1-y2);
                y1=y2;
            }
        }
        if(ke.getKeyCode()==KeyEvent.VK_RIGHT){
            if(arr)
            {
                izq=false;
                der=true;
                aba=false;
                arr=false;
                x2=(y1-y2)+x1;
                y1=y2;
            }
            if(aba)
            {
                izq=false;
                der=true;
                aba=false;
                arr=false;
                x2=(y2-y1)+x1;
                y1=y2;
            }
        }
        if(ke.getKeyCode()==KeyEvent.VK_UP){
            if(izq){
                izq=false;
                der=false;
                aba=false;
                arr=true;
                y2=y1-(x1-x2);
                x1=x2;
            }
            if(der){
                izq=false;
                der=false;
                aba=false;
                arr=true;
                y2=y1-(x2-x1);
                x1=x2;
            }
        }
        if(ke.getKeyCode()==KeyEvent.VK_DOWN){
            if(der)
            {
                izq=false;
                der=false;
                aba=true;
                arr=false;
                y2=(x2-x1)+y1;
                x1=x2;
            }
            if(izq)
            {
                izq=false;
                der=false;
                aba=true;
                arr=false;
                y2=(x1-x2)+y1;
                x1=x2;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
