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
        tem = {i[0],i[1]}
        stack.push(i)
    

    while not stack.isEmpty():
        t=stack.pop()
        visited.add(t[0])
        res.append(t[1])
        if (problem.isGoalState(t[0])):
            print "GOAl"
            print t[0]
            flg=1
            break
        else:

            for j in problem.getSuccessors(t[0]):
                if j[0] not in visited:
                    stack.push(j)


    if flg==1:
        print "this is res"
        print res
        return res
    else:
        return None

