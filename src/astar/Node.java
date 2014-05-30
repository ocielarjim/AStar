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
public class Node {
    
    public String ID;
    public Coordinate Coordinates;
    public int Heuristics;
    public int Cost;
    public int Score;
    public Node Parent;
    public ArrayList<Node> ChildsNodes = new ArrayList<>();
    
    public Node (String pID, Coordinate pCoordinates, Node pParent, Coordinate pGoalCoordinates)
    {
        ID = pID;
        Coordinates = pCoordinates;
        Parent = pParent;
        this.CalculateHeuristics(pCoordinates, pGoalCoordinates);
        if (pParent != null)
            this.CalculateCost(pParent.Cost, 10);
        else
            this.CalculateCost(0, 0);
        this.CalculateScore();
    }
    
    public int CalculateHeuristics (Coordinate pPosition, Coordinate pGoal)
    {
        Heuristics = (Math.abs(pGoal.X - pPosition.X) + Math.abs(pGoal.Y - pPosition.Y) - 3)*10;
       return Heuristics;
    }
    
    public void SetHeuristics (int pValue)
    {
        Heuristics = pValue;
    }
    
    public int CalculateCost (int pParentCost, int pValueMoveCost)
    {
        Cost = pParentCost + pValueMoveCost;
        return Cost;
    }
    
    private int CalculateScore ()
    {
        Score = Heuristics + Cost;
        return Score;
    }
}
