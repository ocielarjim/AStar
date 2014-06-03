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
public class AStarTree {
    
    //Listas de abiertos y cerrados para el análsis
    public ArrayList<Node> OpenList = new ArrayList<>();
    public ArrayList<Node> CloseList = new ArrayList<>();    
    //Nuevas variables, customizados
    int[][] Costs;
    public String InitValue;
    public String EndValue;
    
    
    public AStarTree (int[][] pCosts)
    {
        Costs = pCosts;
    }
    
    public Node InitProcess (Node pNodeInit, String pValueEndNode)
    {
        Node CurrentNode;
        //Asignando el inicio y fin
        InitValue = pNodeInit.ID;
        EndValue = pValueEndNode;
        //Rellenando los valores pendientes para el nodo inicial
        pNodeInit.Parent = null;
        pNodeInit.Cost = 0;
        pNodeInit.CalculateScore();
        
        OpenList.add(pNodeInit);
        CurrentNode = OpenList.get(0);
        while (!isEndNode(CurrentNode) && !OpenList.isEmpty())
        {
            OpenList.remove(0);
            CloseList.add(CurrentNode);
            CurrentNode.ChildsNodes = this.GenerateChildsNodes(CurrentNode);//Corregido
            CurrentNode.ChildsNodes = this.ClearChildsNodes(CurrentNode.ChildsNodes);//Corregido
            CurrentNode.ChildsNodes = this.SortBestScoreChildsNodes(CurrentNode);//No requiere ajuste
            this.AddOpenList(CurrentNode.ChildsNodes);
            CurrentNode = OpenList.get(0);
        }
        return CurrentNode;
    }
    
    private void AddOpenList (ArrayList<Node> pChildNodes)
    {
        for (int i = 0; i < pChildNodes.size(); i++)
        {
            OpenList.add(0, pChildNodes.get(i));
        }
    }
    
    private boolean isEndNode (Node pCurrentNode)
    {
        if (pCurrentNode.ID.equalsIgnoreCase(EndValue))
            return true;
        return false;
    }
    
    private ArrayList<Node> GenerateChildsNodes (Node pCurrentNode)
    {
        for (Node ChildNode : pCurrentNode.ChildsNodes)  
        {
            int _ParentIndex = pCurrentNode.ID.charAt(0) - 65;
            int _ChildIndex = ChildNode.ID.charAt(0) - 65;
            int _Cost = Costs[_ParentIndex][_ChildIndex];
            ChildNode.CalculateCost(pCurrentNode.Cost, _Cost);
            ChildNode.CalculateScore();
            Node Parent = new Node(pCurrentNode.ID, null, pCurrentNode.Parent, null, pCurrentNode.Cost);
            ChildNode.Parent = Parent;
        }
        return pCurrentNode.ChildsNodes;
    }

    private ArrayList<Node> SortBestScoreChildsNodes (Node pCurrentNode)
    {
        ArrayList<Node> _childsNodes = pCurrentNode.ChildsNodes;
        Node temp_node;
        for (int i = 0; i < _childsNodes.size(); i++)
        {
            for (int j = i + 1; j < _childsNodes.size(); j++)
            {
                if (_childsNodes.get(i).Score < _childsNodes.get(j).Score)
                {
                    temp_node = _childsNodes.set(i, _childsNodes.get(j));
                    _childsNodes.set(j, temp_node);
                }
            }
        }
        return _childsNodes;
    }
    
    private ArrayList<Node> ClearChildsNodes (ArrayList<Node> pChildsNodes)
    {
        for (Node NodeOpen : OpenList)
        {
            for (Node ChildNode : pChildsNodes)
            {
                if (ChildNode.ID.equalsIgnoreCase(NodeOpen.ID))
                {
                    pChildsNodes.remove(ChildNode);
                    break;
                }
            }
        }
            
        for (Node NodeClose : CloseList)
        {
            for (Node ChildNode : pChildsNodes)
            {
                if (ChildNode.ID.equalsIgnoreCase(NodeClose.ID))
                {
                    pChildsNodes.remove(ChildNode);
                    break;
                }
            }
        }
        return pChildsNodes;
    }
}
