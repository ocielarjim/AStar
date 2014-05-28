/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Ociel
 */
public class Node {
    
    public String ID;
    public int Heuristics;
    public int Cost;
    public int Score;
    public Node Parent;
    
    public Node (String pID, Node pParent)
    {
        ID = pID;
        Parent = pParent;
    }
    
    public int CalculateHeuristics (int pX, int pY, int pGoalX, int pGoalY)
    {
        Heuristics = Math.abs(pGoalX - pX) + Math.abs(pGoalY - pY);
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
