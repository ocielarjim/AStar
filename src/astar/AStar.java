/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;

/**
 *
 * @author Ociel
 */
public class AStar {

    //Listas de abiertos y cerrados para el análsis
    public ArrayList<Node> OpenList = new ArrayList<>();
    public ArrayList<Node> CloseList = new ArrayList<>();
    //Mapa del tablero
    char[][] Map;
    
    public AStar (char[][] pMap)
    {
        Map = pMap;
    }
    
    public Coordinate FindInitPosition ()
    {
        for (int i = 0; i < Map.length; i++)
        {
            for (int j = 0; j < Map[0].length; i++)
            {
                if (Map[i][j] != 'i')
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
