'''
The University of Melbourne
COMP90054 AI Planning for Autonomy
Student: Wendong Chen
Student ID: 931018      Username: wendongc1
Date: Aug 30th, 2018
'''
# search.py
# ---------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
# 
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util
from util import *
from game import Directions

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]





def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    initialstate = problem.getStartState() #return a state
    stack = Stack()
    traversallist = list()
    actions = {initialstate : list()}
    stack.push(initialstate)

    while not stack.isEmpty():
        state = stack.pop()
        if problem.isGoalState(state):
            return actions[state]
        else:
            if not state in traversallist:
                nextnodes = problem.getSuccessors(state)
                for successor in nextnodes:
                    if not successor[0] in traversallist:
                        actions[successor[0]] = actions[state] + [successor[1]]
                        stack.push(successor[0])
            traversallist.append(state)

    util.raiseNotDefined()
    

def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    
    initialstate = problem.getStartState()
    queue = Queue()
    traversallist = list()
    actions = {initialstate : list()}
    queue.push(initialstate)

    while not queue.isEmpty():
        state = queue.pop()
        if problem.isGoalState(state):
            return actions[state]
        else:
            if not state in traversallist:
                nextnodes = problem.getSuccessors(state)
                for successor in nextnodes:
                    if not successor[0] in actions:
                        actions[successor[0]] = actions[state] + [successor[1]]
                        queue.push(successor[0])
            traversallist.append(state)

    util.raiseNotDefined()

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    "*** YOUR CODE HERE ***"
    
    initialstate = problem.getStartState()
    priorityqueue = PriorityQueue()
    traversallist = list()
    attributes = {initialstate : [[], 0]}
    priorityqueue.push(initialstate, attributes[initialstate][1])

    while not priorityqueue.isEmpty():
        state = priorityqueue.pop()
        if problem.isGoalState(state):
            return attributes[state][0]
        else:
            if not state in traversallist:
                nextnodes = problem.getSuccessors(state)
                for successor in nextnodes:
                    if not successor[0] in traversallist:
                        if not successor[0] in attributes:
                            attributes[successor[0]] = [attributes[state][0] + [successor[1]], attributes[state][1] + successor[2]]
                            priorityqueue.update(successor[0], attributes[successor[0]][1])
                        elif attributes[state][1] + successor[2] < attributes[successor[0]][1]:
                            attributes[successor[0]] = [attributes[state][0] + [successor[1]], attributes[state][1] + successor[2]]
                            priorityqueue.update(successor[0], attributes[successor[0]][1])
            traversallist.append(state)

    util.raiseNotDefined()


def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    initialstate = problem.getStartState()
    priorityqueue = PriorityQueue()
    traversallist = list()
    attributes = {initialstate : [[], 0]}
    priorityqueue.push(initialstate, attributes[initialstate][1])

    while not priorityqueue.isEmpty():
        state = priorityqueue.pop()
        if problem.isGoalState(state):
            return attributes[state][0]
        else:
            if not state in traversallist:
                nextnodes = problem.getSuccessors(state)
                for successor in nextnodes:
                    if not successor[0] in traversallist:
                        if not successor[0] in attributes:
                            attributes[successor[0]] = [attributes[state][0] + [successor[1]], attributes[state][1] + successor[2]]
                            priorityqueue.update(successor[0], attributes[successor[0]][1] + heuristic(successor[0], problem))
                        elif attributes[state][1] + successor[2] + heuristic(successor[0], problem) < attributes[successor[0]][1]:
                            attributes[successor[0]] = [attributes[state][0] + [successor[1]], attributes[state][1] + successor[2]]
                            priorityqueue.update(successor[0], attributes[successor[0]][1] + heuristic(successor[0], problem))
            traversallist.append(state)
    
    util.raiseNotDefined()


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
