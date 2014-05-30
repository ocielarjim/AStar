/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import astar.AStar;
import astar.Coordinate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Ociel
 */
public class TestGUI extends javax.swing.JFrame {

    ArrayList<char[]> Map = new ArrayList<>();
    
    public TestGUI()
    {
        initComponents();
    }
    
    private void LoadMap (String pFile)
    {
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
    
    private Coordinate FindInitMap ()
    {
        Coordinate _coordinate =  new Coordinate();
        for (int i = 0; i < Map.size(); i++)
        {
            for (int j = 0; j < Map.get(i).length; j++)
            {
                if (Map.get(i)[j] == 'i')
                {
                    _coordinate.X = i;
                    _coordinate.Y = j;
                    return _coordinate;
                }
            }
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(317, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addContainerGap(266, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.LoadMap("C:\\Users\\Ociel\\Desktop\\Mapas\\mapa1.txt");
        //Coordinate _coCoordinate = this.FindInitMap();
        AStar _astar = new AStar(TransformMap());
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
