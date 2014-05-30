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
    //Coordenadas del Nodo Final;
    Coordinate _coordinateEnd;
    
    public AStar (char[][] pMap)
    {
        Map = pMap;
        _coordinateEnd = this.FindGoalPosition();
        this.InitProcess();
    }
    
    private Coordinate FindInitPosition ()
    {
        Coordinate _coordinate = new Coordinate();
        for (int i = 0; i < Map.length; i++)
        {
            for (int j = 0; j < Map[i].length; j++)
            {
                if (Map[i][j] == 'i')
                {
                    _coordinate.X = i;
                    _coordinate.Y = j;
                    return _coordinate;
                } 
            }
        }
        return null;
    }
    
    private Coordinate FindGoalPosition ()
    {
        Coordinate _coordinate = new Coordinate();
        for (int i = 0; i < Map.length; i++)
        {
            for (int j = 0; j < Map[i].length; j++)
            {
                if (Map[i][j] == 'f')
                {
                    _coordinate.X = i;
                    _coordinate.Y = j;
                    return _coordinate;
                } 
            }
        }
        return null;
    }
    
    private void InitProcess ()
    {
        Node CurrentNode;
        Node NodeInit = new Node("Init", FindInitPosition(), null, _coordinateEnd);
        OpenList.add(NodeInit);
        CurrentNode = OpenList.get(0);
        while (!isEndNode(CurrentNode) && !OpenList.isEmpty())
        {
            OpenList.remove(0);
            CloseList.add(CurrentNode);
            CurrentNode.ChildsNodes = this.GenerateChildsNodes(CurrentNode);
            CurrentNode.ChildsNodes = this.ClearChildsNodes(CurrentNode.ChildsNodes);
            OpenList.addAll(CurrentNode.ChildsNodes);
            CurrentNode = OpenList.get(0);
        }
    }
    
    private boolean isEndNode (Node pCurrentNode)
    {
        if (pCurrentNode.Coordinates == _coordinateEnd)
            return true;
        return false;
    }
    
    private ArrayList<Node> GenerateChildsNodes (Node pCurrentNode)
    {
        ArrayList<Node> _childsNodes = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <=1; j++)
            {
                if (!(i == pCurrentNode.Coordinates.X && j == pCurrentNode.Coordinates.Y))
                {
                    if (pCurrentNode.Coordinates.X + i >=0 &&
                        pCurrentNode.Coordinates.Y + j >=0 &&
                        Map[pCurrentNode.Coordinates.X + i][pCurrentNode.Coordinates.Y + j] == 'c')
                    {
                        Coordinate _childCordinate = new Coordinate();
                        _childCordinate.X = pCurrentNode.Coordinates.X + i;
                        _childCordinate.Y = pCurrentNode.Coordinates.Y + j;
                        _childsNodes.add(new Node(null, _childCordinate, pCurrentNode, _coordinateEnd));
                    }
                }
            }
        }
        //sort for best score
        Node temp_node;
        for (int i = 0; i < _childsNodes.size(); i++)
        {
            for (int j = i + 1; j < _childsNodes.size(); i++)
            {
                if (_childsNodes.get(i).Score < _childsNodes.get(j).Score)
                {
                    temp_node = _childsNodes.get(i);
                    _childsNodes.add(i, _childsNodes.get(j));
                    _childsNodes.add(j, temp_node);
                }
            }
        }
        return _childsNodes;
    }
    
    private ArrayList<Node> ClearChildsNodes (ArrayList<Node> pChildsNodes)
    {
        for (int i = 0; i < pChildsNodes.size(); i++)
        {
            int Index = OpenList.indexOf(pChildsNodes.get(i));
            if (Index != -1)
                pChildsNodes.remove(i);
        }
        for (int i = 0; i < pChildsNodes.size(); i++)
        {
            int Index = CloseList.indexOf(pChildsNodes.get(i));
            if (Index != -1)
                pChildsNodes.remove(i);
        }
        return pChildsNodes;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
}
