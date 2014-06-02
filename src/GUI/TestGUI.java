/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import astar.AStar;
import astar.Coordinate;
import astar.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Ociel
 */
public class TestGUI extends javax.swing.JFrame {

    ArrayList<char[]> Map = new ArrayList<>();
    JLabel[][] imageMap;
    char[][] tMap;
    
    public TestGUI()
    {
        initComponents();
    }
    
    private void LoadMap (String pFile)
    {
        Map.clear();
        BufferedReader br = null;
        try
        {
            String line;
            br = new BufferedReader(new FileReader(pFile));
            while ((line = br.readLine()) != null)
            {
                char[] unit = new char[line.length()];
                for (int i = 0; i < line.length(); i++)
                    unit[i] = line.substring(i, i + 1).charAt(0);
                Map.add(unit);
            }
            br.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
	}
    }
    
    private void LoadTree (String pFile)
    {
        ArrayList<Node> Nodes = new ArrayList<>();
        ArrayList<Node> Tree = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            int estado = -1;
            String line;
            br = new BufferedReader(new FileReader(pFile));
            while ((line = br.readLine()) != null)
            {
                if (line.contains("$"))
                    estado++;
                if (!line.contains("#"))
                {
                    if (estado == 0)
                    {
                        String[] Info = line.split(",");
                        Nodes.add(new Node(Info[0], Integer.parseInt(Info[1])));
                    }
                    if (estado == 1)
                    {
                        String[] Values = line.split("-");
                        for (int i = 0; i < Nodes.size(); i++)
                        {
                            if (Nodes.get(i).ID == Values[0])
                            {
                                String[] Childs = Values[1].split(",");
                                for (int j = 0; j <  Childs.length; j++)
                                {
                                    String[] ChildValue = Childs[j].split(".");
                                    for (int k = 0; k < Nodes.size(); k++)
                                    {
                                        if (Nodes.get(k).ID.equalsIgnoreCase(ChildValue[0]))
                                        {
                                            Nodes.get(i).ChildsNodes.get(0).Parent = Nodes.get(i);
                                            Nodes.get(i).ChildsNodes.add(0, Nodes.get(k));
                                            Nodes.get(i).ChildsNodes.get(0).CalculateCost(Nodes.get(i).Cost, Integer.valueOf(ChildValue[1]));
                                            Nodes.get(i).ChildsNodes.get(0).CalculateScore();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            br.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
	}
    }
    
    private char[][] TransformMap ()
    {
        char[][] CompatibleMap = new char[Map.size()][];
        for (int i = 0; i < Map.size(); i++)
            CompatibleMap[i] = Map.get(i);
        return CompatibleMap;
    }
    
    private void PaintMap (char[][] pMap)
    {
        this.PanelMap.removeAll();
        imageMap = new JLabel[pMap.length][pMap[0].length];
        for (int i = 0; i < pMap.length; i++)
        {
            for (int j = 0; j < pMap[i].length; j++)
            {
                imageMap[i][j] = new JLabel();
                imageMap[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/" + String.valueOf(pMap[i][j]) + ".png")));
                imageMap[i][j].setBounds(j*24, i*24, 24, 24);
                imageMap[i][j].setVisible(true);
                this.PanelMap.add(imageMap[i][j]);
            }
        }
        this.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PanelMap = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("GeneraMap");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Solve");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMapLayout = new javax.swing.GroupLayout(PanelMap);
        PanelMap.setLayout(PanelMapLayout);
        PanelMapLayout.setHorizontalGroup(
            PanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelMapLayout.setVerticalGroup(
            PanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 111, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(PanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.LoadMap("C:\\Users\\Ociel\\Desktop\\Mapas\\mapa2.txt");
        //Coordinate _coCoordinate = this.FindInitMap();
        tMap = TransformMap();
        PaintMap(tMap);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        AStar _astar = new AStar(tMap);
        Node _camino = _astar.InitProcess().Parent;
        while (!_camino.Coordinates.CompareTo(_astar._coordinateInit))
        {
            imageMap[_camino.Coordinates.X][_camino.Coordinates.Y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/solve.png")));
            _camino = _camino.Parent;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.LoadTree("C:\\Users\\Ociel\\Desktop\\Mapas\\mapa3.txt");
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
