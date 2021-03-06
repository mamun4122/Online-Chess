/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*  
    soinno 6
    nouka 1
    ghora 2
    hati 3
    rani 4
    montri 5
 */
package onlinechess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dipta007
 */
public class GUI extends javax.swing.JFrame {

    //changable variable
    int white = 1, black = 0;
    int row = 4, col = 4;
    String ipOfArduino = "192.168.1.7";
    String ipOfOpponent = "192.168.1.15";

    //DO NOT CHANGE
    DatagramSocket d1;
    DatagramPacket send, rec;
    InetAddress ip;
    int my, opponent;
    int[][] mat = new int[row][col];
    int[][] tmp = new int[row][col];
    int flag = 1;
    int strt = 1, cnt = 0;
    int error;
    
    String msgOfMy, msgOfOpponent;

    //guti from nicher side
//    int[][] guti = {{1,2,3,4,5,3,2,1}, {6,6,6,6,6,6,6,6}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,0,0,0}, {16,16,16,16,16,16,16,16}, {11,12,13,15,14,13,12,11}};
    int[][] guti = {{16, 16, 16, 16}, {0,0,0,0}, {0,0,0,0}, {6, 6, 6, 6}};
//    int[][] prev = {{11, 12, 13, 15, 14, 13, 12, 11}, {16, 16, 16, 16, 16, 16, 16, 16}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {6, 6, 6, 6, 6, 6, 6, 6}, {1, 2, 3, 5, 4, 3, 2, 1}};
    int[][] prev = {{16, 16, 16, 16}, {0,0,0,0}, {0,0,0,0}, {6, 6, 6, 6}};

    public GUI() {
        initComponents();
        my = opponent = -1;
        mySide.setEditable(false);
        opponentSide.setEditable(false);
//        mySide.setText("mamun");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        mySide = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        opponentSide = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        mySide.setColumns(20);
        mySide.setRows(5);
        jScrollPane1.setViewportView(mySide);

        opponentSide.setColumns(20);
        opponentSide.setRows(5);
        jScrollPane2.setViewportView(opponentSide);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                .addComponent(connectButton)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(connectButton)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        error = 0;
        if (white == 1) {
            call();
        } else {
            try {
                receieveDataFunc();
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    String messageFromArduino = "";

    public void call() {
        try {
            // TODO add your handling code here:
            int sx, sy, dx, dy;
            sx = sy = dx = dy = -1;
            while (true) {
                String sendString = "1";
                byte[] sendData = sendString.getBytes();
                String ipString = new String(ipOfArduino);
                ip = InetAddress.getByName(ipString);
                d1 = new DatagramSocket();
                send = new DatagramPacket(sendData, sendData.length, ip, 7777);
                d1.send(send);

                byte[] receiveData = new byte[1024];
                d1.setSoTimeout(100000);
                rec = new DatagramPacket(receiveData, receiveData.length, ip, 8888);
                d1.receive(rec);
                String getData = new String(rec.getData());

                //System.out.println(getData);
                int in = 0;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (getData.charAt(in) == '0') {
                            mat[i][j] = 0;
                        } else {
                            mat[i][j] = 1;
                        }
                        in++;
                    }
                }
                
                for(int i=0; i<row; i++)
                {
                    for(int j=0; j<col; j++)
                    {
                        tmp[i][j] = prev[i][j];
                    }
                }

                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (prev[i][j] != 0 && mat[i][j] == 0) {
                            if (prev[i][j] < 10) {
                                char a = (char) (i + 49);
                                char b = (char) (j + 97);
                                messageFromArduino += a;
                                messageFromArduino += b;
                                messageFromArduino += " to ";
                                sx = i;
                                sy = j;
                                my = prev[i][j];
                            } else {
                                dx=i;
                                dy=j;
                                opponent = prev[i][j];
                            }
                            prev[i][j] = 0;
                        }
                        if ((prev[i][j] == 0) && mat[i][j] == 1) {
                            char a = (char) (i + 49);
                            char b = (char) (j + 97);
                            messageFromArduino += a;
                            messageFromArduino += b;
                            messageFromArduino += "\n";
                            dx = i;
                            dy = j;
                            opponent = prev[i][j];
                            
                            System.out.println(messageFromArduino);
                            System.err.println(sx + " " + sy + " " + dx + " " + dy);
                            int turn;
                            if(strt==1) turn=1;
                            else turn=0;
                            if (guti[sx][sy] == 1 && Check.checkNouka(sx, sy, dx, dy, guti) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else if (guti[sx][sy] == 2 && Check.checkGhora(sx, sy, dx, dy, guti) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else if (guti[sx][sy] == 3 && Check.checkHati(sx, sy, dx, dy, guti, row, col) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else if (guti[sx][sy] == 4 && Check.checkRani(sx, sy, dx, dy, guti) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else if (guti[sx][sy] == 5 && Check.checkMontri(sx, sy, dx, dy, guti, row, col) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else if (guti[sx][sy] == 6 && Check.checkSoinno(sx, sy, dx, dy, guti, turn) == 1) {
                                changeBoard(sx, sy, dx, dy);
                            } else {
                                //error
                                System.err.println("Error");
                                error = 1;
                                recoverFromError();
                                messageFromArduino = "";
                                call();
                            }
                            //cnt++;
                            System.err.println(messageFromArduino);
                            msgOfMy += messageFromArduino;
                            mySide.setText(msgOfMy);
                            prev[i][j] = my;
                            my = -1;
                            opponent = -1;
                        }
                    }
                }
                System.err.println(cnt + " " + strt);
                System.out.println(messageFromArduino+" "+messageFromArduino.length());
                if (messageFromArduino.length() > 7) {
                    if (white == 1) {
                        if (cnt == 0 && strt == 0) {
                            messageFromArduino = "";
                            cnt = 1;
                            sx = sy = dx = dy = -1;
                        } else if (cnt == 1 || strt == 1) {
                            System.out.println("Sent data");
                            System.err.println(messageFromArduino);
                            strt = 0;
                            cnt = 0;
                            sx = sy = dx = dy = -1;

                            sendDataFunc(messageFromArduino);
//                        messageFromArduino = "";
                        }
                    } 
                    else if (cnt == 0) {
                        messageFromArduino = "";
                        cnt = 1;
                        sx = sy = dx = dy = -1;
                    } 
                    else if (cnt == 1) {
                        System.out.println("Sent data");
                        System.err.println(messageFromArduino);
                        strt = 0;
                        cnt = 0;
                        sx = sy = dx = dy = -1;

                        sendDataFunc(messageFromArduino);
//                        messageFromArduino = "";
                    }
                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SocketException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void receieveDataFunc() throws SocketException, IOException {
        DatagramSocket serverSocket = new DatagramSocket(9876);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("RECEIVED: " + sentence);

            if (sentence.length() > 0) {
                sentence += "\n";
                msgOfOpponent += sentence;
                opponentSide.setText(msgOfOpponent);
                serverSocket.close();
                call();
                break;
            }
        }
    }

    public void sendDataFunc(String st) throws SocketException, UnknownHostException, IOException {
        this.messageFromArduino = "";
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName(ipOfOpponent);
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = st;
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
        clientSocket.send(sendPacket);
        clientSocket.close();
        receieveDataFunc();
    }

    void changeBoard(int sx, int sy, int dx, int dy) {
        guti[dx][dy] = guti[sx][sy];
        guti[sx][sy] = 0;
        prev[dx][dy] = prev[sx][sy];
        prev[sx][sy] = 0;
        tmp[dx][dy]=tmp[sx][sy];
        tmp[sx][sy]=0;
    }

    void recoverFromError() {
        try {
            // TODO add your handling code here:
            int sx, sy, dx, dy;
            sx = sy = dx = dy = -1;
            while (true) {
                //create dialogue
                Object[] options = {"Yes!!",
                    "No!"};
                
                int n = JOptionPane.showOptionDialog(this,
                        "Correct your Errors",
                        "",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null, //do not use a custom Icon
                        options, //the titles of buttons
                        options[0]); //default button title
                
                String sendString = "1";
                byte[] sendData = sendString.getBytes();
                String ipString = new String(ipOfArduino);
                d1 = new DatagramSocket();
                send = new DatagramPacket(sendData, sendData.length, ip, 7777);
                d1.send(send);

                byte[] receiveData = new byte[1024];
                d1.setSoTimeout(100000);
                rec = new DatagramPacket(receiveData, receiveData.length, ip, 8888);
                d1.receive(rec);
                d1.close();
                String getData = new String(rec.getData());

                //System.out.println(getData);
                int in = 0;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (getData.charAt(in) == '0') {
                            mat[i][j] = 0;
                        } else {
                            mat[i][j] = 1;
                        }
                        in++;
                    }
                }

                int f = 1;
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < col; j++) {
                        if (tmp[i][j] > 0 && mat[i][j] == 0) {
                            f = 0;
                        }
                    }
                }
                if (f == 1) {
                    error = 0;
                    return;
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mySide;
    private javax.swing.JTextArea opponentSide;
    // End of variables declaration//GEN-END:variables
}