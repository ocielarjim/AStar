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
    //Coordenadas para Nodo Final y Nodo Inicial;
    public Coordinate _coordinateEnd;
    public Coordinate _coordinateInit;
    
    
    public AStar (char[][] pMap)
    {
        Map = pMap;
        _coordinateInit = this.FindInitPosition();
        _coordinateEnd = this.FindGoalPosition();
    }
    
    public AStar (Node pRootTree)
    {
        
    }
    
    public Node InitProcess ()
    {
        Node CurrentNode;
        Node NodeInit = new Node("Init", FindInitPosition(), null, _coordinateEnd, 0);
        OpenList.add(NodeInit);
        CurrentNode = OpenList.get(0);
        while (!isEndNode(CurrentNode) && !OpenList.isEmpty())
        {
            OpenList.remove(0);
            CloseList.add(CurrentNode);
            CurrentNode.ChildsNodes = this.GenerateChildsNodes(CurrentNode);
            CurrentNode.ChildsNodes = this.ClearChildsNodes(CurrentNode.ChildsNodes);
            CurrentNode.ChildsNodes = this.SortBestScoreChildsNodes(CurrentNode);
            this.AddOpenList(CurrentNode.ChildsNodes);
            CurrentNode = OpenList.get(0);
        }
        return CurrentNode;
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
                    _coordinateInit = _coordinate;
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
    
    private void AddOpenList (ArrayList<Node> pChildNodes)
    {
        for (int i = 0; i < pChildNodes.size(); i++)
        {
            OpenList.add(0, pChildNodes.get(i));
        }
    }
    
    private boolean isEndNode (Node pCurrentNode)
    {
        if (pCurrentNode.Coordinates.CompareTo(_coordinateEnd))
            return true;
        return false;
    }
    
    private ArrayList<Node> GenerateChildsNodes (Node pCurrentNode)
    {
        ArrayList<Node> _childsNodes = new ArrayList<>();
        Coordinate _eval_coordinates;// = new Coordinate();
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <=1; j++)
            {
                _eval_coordinates = new Coordinate();
                _eval_coordinates.X = pCurrentNode.Coordinates.X + i;
                _eval_coordinates.Y = pCurrentNode.Coordinates.Y + j;
                
                if (!_eval_coordinates.CompareTo(pCurrentNode.Coordinates))//verifica que no sea el nodo actual
                {
                    if (_eval_coordinates.X >= 0 && _eval_coordinates.Y >= 0 &&
                        _eval_coordinates.X < Map.length && _eval_coordinates.Y < Map[0].length)//evalua que se encuentra dentro del limite del arreglo
                    {
                        if ((_eval_coordinates.X == pCurrentNode.Coordinates.X ||
                            _eval_coordinates.Y == pCurrentNode.Coordinates.Y))//evalua horizontales y verticales
                        {
                            if (Map[_eval_coordinates.X][_eval_coordinates.Y] == 'c' ||
                                Map[_eval_coordinates.X][_eval_coordinates.Y] == 'f')//evalua que sea un camino
                                _childsNodes.add(new Node(null, _eval_coordinates, pCurrentNode, _coordinateEnd, 10));
                        }
                    }
                }
            }
        }
        return _childsNodes;
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
        for (int i = 0; i < pChildsNodes.size(); i++)
        {
            for (int j = 0; j < OpenList.size(); j++)
            {
                if (pChildsNodes.get(i).Coordinates.CompareTo(OpenList.get(j).Coordinates))
                {
                    pChildsNodes.remove(i);
                    break;
                }
            }
            for (int j = 0; j < CloseList.size(); j++)
            {
                if (pChildsNodes.get(i).Coordinates.CompareTo(CloseList.get(j).Coordinates))
                {
                    pChildsNodes.remove(i);
                    break;
                }
                    
            }
        }
        return pChildsNodes;
    }
}
