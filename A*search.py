   """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    import util
    priority_queue = util.PriorityQueue()
    start_state = problem.getStartState()
    if (problem.isGoalState(start_state)):
        return None
    flg = 0
    res = []
    visited = set()
    visited.add(start_state)
    many_paths = set()
    many_paths.add(start_state)
    for i in problem.getSuccessors(start_state):
        tem = [i[0],i[1],i[2]]
        initial_cost = i[2]+heuristic(i[0],problem)
        priority_queue.push(tem,initial_cost)
        many_paths.add(i[0])


    while not priority_queue.isEmpty():
        if flg==1:
            break
        t = priority_queue.pop()
        if t[0] in visited:
            # print "DId I come in atleat once !!!!!!!!!!!!!!!!!!!!!!!!!"
            # print t[0]
            continue
        visited.add(t[0])

        if (problem.isGoalState(t[0])):
            res = t[1]
            flg = 1
            break
        else:
            #print "inside succ"
            for j in problem.getSuccessors(t[0]):
                if j[0] not in visited:
                    ltem = t[1] + "," + j[1]
                    cost = heuristic(j[0],problem)+j[2]+t[2]
                    #print j
                    priority_queue.push([j[0], ltem,cost],cost)
                    many_paths.add(j[0])

    final = []
    if flg == 1:
        data = res.split(",")
        for temp in data:
            final.append(temp)
        return final
    else:
        return None
