def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    import util
    queue = util.Queue()
    start_state = problem.getStartState()
    if (problem.isGoalState(start_state)):
        return None
    flg = 0
    res = []
    visited = set()
    visited.add(start_state)
    many_paths = set()
    #many_paths.add(start_state)
    for i in problem.getSuccessors(start_state):
        tem = [i[0], i[1]]
        queue.push(tem)
        #many_paths.add(i[0])

    while not queue.isEmpty():
        t = queue.pop()
        visited.add(t[0])

        if (problem.isGoalState(t[0])):
            res = t[1]
            flg = 1
            break
        else:

            for j in problem.getSuccessors(t[0]):
                if j[0] not in visited and j[0] not in many_paths:
                    ltem = t[1] + "," + j[1]
                    queue.push([j[0], ltem])
                    #many_paths.add(j[0])

    final = []
    if flg == 1:
        data = res.split(",")
        for temp in data:
            final.append(temp)
        return final
    else:
        return None
