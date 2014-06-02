/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Ociel
 */
public class Coordinate {
    
    public int X;
    public int Y;
    
    public boolean CompareTo(Coordinate pCoordinate)
    {
        if (X == pCoordinate.X && Y == pCoordinate.Y)
            return true;
        else
            return false;
    }
}
