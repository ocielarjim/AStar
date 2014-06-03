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
    
    public Node (String pID, Coordinate pCoordinates, Node pParent, Coordinate pGoalCoordinates, int pCost)
    {
        ID = pID;
        Coordinates = pCoordinates;
        Parent = pParent;
        Cost = pCost;
        if (pCoordinates != null)
            this.CalculateHeuristics(pCoordinates, pGoalCoordinates);
        if (pParent != null)
            this.CalculateCost(pParent.Cost, Cost);
        else
            this.CalculateCost(0, Cost);
        this.CalculateScore();
    }
    
    public Node (String pID, int pHeuristics)
    {
        ID = pID;
        Heuristics = pHeuristics;
    }
    
    public int CalculateHeuristics (Coordinate pPosition, Coordinate pGoal)
    {
        Heuristics = (Math.abs(pGoal.X - pPosition.X) + Math.abs(pGoal.Y - pPosition.Y))*10;
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
    
    public int CalculateScore ()
    {
        Score = Heuristics + Cost;
        return Score;
    }
}
