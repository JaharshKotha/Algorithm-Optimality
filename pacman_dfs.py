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
    import util
    stack=util.Stack()
    start_state = problem.getStartState()
    if(problem.isGoalState(start_state)):
        return None
    flg =0
    res = []
    visited = set()


    for i in problem.getSuccessors(start_state):
        tem = [i[0],i[1]]
        stack.push(tem)


    while not stack.isEmpty():
        t=stack.pop()
        visited.add(t[0])

        if (problem.isGoalState(t[0])):
            res = t[1]
            flg=1
            break
        else:

            for j in problem.getSuccessors(t[0]):
                if j[0] not in visited:
                    ltem = t[1]+","+j[1]
                    stack.push([j[0],ltem])

    final =[]
    if flg==1:
        data = res.split(",")
        for temp in data:
             final.append(temp)
        return final
    else:
        return None
