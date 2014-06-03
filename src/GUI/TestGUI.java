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
    AStar _astar;
    
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
    
    private Node LoadTree (String pFile)
    {
        ArrayList<Node> Nodes = new ArrayList<>();
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
                if (!line.contains("#") && !line.contains("$"))
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
                            if (Nodes.get(i).ID.equalsIgnoreCase(Values[0]))
                            {
                                String[] Childs = Values[1].substring(1, Values[1].length()-1).split(",");
                                for (int j = 0; j <  Childs.length; j++)
                                {
                                    String[] ChildValue = Childs[j].split("&");
                                    for (int k = 0; k < Nodes.size(); k++)
                                    {
                                        if (Nodes.get(k).ID.equalsIgnoreCase(ChildValue[0]))
                                        {
                                            Nodes.get(i).ChildsNodes.add(0, Nodes.get(k));
                                            Nodes.get(i).ChildsNodes.get(0).Parent = Nodes.get(i);
                                            Nodes.get(i).ChildsNodes.get(0).Cost = Integer.valueOf(ChildValue[1]);
                                            //Nodes.get(i).ChildsNodes.get(0).CalculateCost(Nodes.get(i).Cost, Integer.valueOf(ChildValue[1]));
                                            //Nodes.get(i).ChildsNodes.get(0).CalculateScore();
                                            break;
                                        }
                                    }
                                }
                                break;
                            }
                        }
                    }
                    if (estado == 2)
                    {
                        for (int i = 0; i < Nodes.size(); i++)
                        {
                            if (Nodes.get(i).ID.equalsIgnoreCase(line))
                            {
                                Nodes.get(i).PosNode = "end";
                                break;
                            }
                            
                        }
                    }
                    if (estado == 3)
                    {
                        for (int i = 0; i < Nodes.size(); i++)
                        {
                            if (Nodes.get(i).ID.equalsIgnoreCase(line))
                            {
                                Nodes.get(i).PosNode = "init";
                                return Nodes.get(i);
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
        return null;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        PanelMap = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

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
            .addGap(0, 348, Short.MAX_VALUE)
        );
        PanelMapLayout.setVerticalGroup(
            PanelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jButton3.setText("Pending");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("SolveMore");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PanelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
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
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //this.LoadMap("C:\\Users\\Ociel\\Desktop\\Mapas\\mapa2.txt");
        this.LoadMap("C:\\MyProject\\AStar-master\\Mapas\\mapa4.txt");
        //Coordinate _coCoordinate = this.FindInitMap();
        tMap = TransformMap();
        PaintMap(tMap);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        _astar = new AStar(tMap);
        Node _track = _astar.InitProcess().Parent;
        while (!_track.Coordinates.CompareTo(_astar._coordinateInit))
        {
            imageMap[_track.Coordinates.X][_track.Coordinates.Y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/solve.png")));
            _track = _track.Parent;
        }
    }                                        

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        this.LoadTree("C:\\MyProject\\AStar-master\\Mapas\\mapa3.txt");
    }                                        

    int colorsolve = 0;
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        Node _track = _astar.ContinuesSeek();
        while (!_track.Coordinates.CompareTo(_astar._coordinateInit))
        {
            imageMap[_track.Coordinates.X][_track.Coordinates.Y].setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/solve" + String.valueOf(colorsolve) + ".png")));
            _track = _track.Parent;
        }
        colorsolve++;
    }                                        

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
    // Variables declaration - do not modify                     
    private javax.swing.JPanel PanelMap;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    // End of variables declaration                   
}
