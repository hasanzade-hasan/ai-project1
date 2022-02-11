import java.util.Comparator;
import java.util.PriorityQueue;

public class SearchProblem {

    int[] pitchers;
    int target;

    int[] cost;
    int[] oldF;
    int maxPitcherCapacity;
    boolean[] visited;
    PriorityQueue<Integer> queue;

    public SearchProblem(int[] pitchers, int target) {
        this.pitchers = pitchers;
        this.target = target;
    }

    // to calculate the GCD of two numbers
    private int gcd(int a, int b) {
        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        if (b % a == 0)
            return a;
        else
            return gcd(b % a, a);
    }

    // to determine whether a problem with given pitchers capacity and target capacity is solvable
    public boolean isSolvable() {
        int gcdOfPitchers = pitchers[0];
        for (int i = 1; i < pitchers.length; i++) {
            gcdOfPitchers = gcd(gcdOfPitchers, pitchers[i]);

            // if gcd is already 1 no need to consider the rest of capacities
            if(gcdOfPitchers == 1)
                return true;
        }

        // if gcd of all capacities is divisor of target capacity then problem is solvable
        return target % gcdOfPitchers == 0;
    }

    public int solve() {
        // answer is -1 if problem is not solvable
        if (!this.isSolvable()){
            return -1;
        }
        // otherwise, find the optimal number of steps
        else {
            // find max pitcher capacity
            maxPitcherCapacity = pitchers[0];
            for (int pitcher : pitchers) {
                if (maxPitcherCapacity < pitcher)
                    maxPitcherCapacity = pitcher;
            }

            // cost to get to nodes, initially all 0
            cost = new int[target+maxPitcherCapacity+1];
            // f values for all nodes, initially MAX value
            oldF = new int[target+maxPitcherCapacity+1];
            for (int i = 0; i <= target + maxPitcherCapacity; i++) {
                oldF[i] = Integer.MAX_VALUE;
            }

            // define comparator and priority queue for open list - nodes to be processed
            Comparator<Integer> comparator = Comparator.comparingInt(this::f);
            queue = new PriorityQueue<>(comparator);
            // define visited set for closed list, initially all false - not visited
            visited = new boolean[target+maxPitcherCapacity+1];

            int source = 0; // initial capacity of infinite pitcher

            // start by adding source node to queue
            queue.add(source);

            return this.aStar();
        }
    }

    public int f(int node) {
        // f = cost + h
        // h = heuristic function
        double h = ((double) 2 * Math.abs(target - node)) / maxPitcherCapacity;
        return cost[node] + (int) Math.ceil(h);
    }

    public int aStar() {
        while (!queue.isEmpty()) {
            // get and remove the top element from queue
            int currentNode = queue.poll();

            // for each successor of current node do following for successors
            int successor;
            String[] operations = {"add", "subtract"};
            for (int pitcher : pitchers) {
                for (String operation : operations) {
                    // pouring water or adding water depending on operation
                    successor = operation.equals("add") ? (currentNode + pitcher) : (currentNode - pitcher);

                    if (successor > 0 && successor <= target + maxPitcherCapacity) {
                        // cost for pouring water is 1 and adding water is 2
                        cost[successor] = operation.equals("add") ? (cost[currentNode] + 2) : (cost[currentNode] + 1);

                        // if the target is reached, stop the search
                        if (successor == target) {
                            return cost[successor];
                        }
                        else {
                            // if a node with the same position as
                            // successor is in the OPEN list which has a
                            // lower f than successor, skip this successor
                            if (!(queue.contains(successor) && oldF[successor] < f(successor))) {
                                // if a node with the same position as
                                // successor is in the CLOSED list which has
                                // a lower f than successor, skip this successor
                                // otherwise, add  the node to the open list
                                if (!(visited[successor] && oldF[successor] < f(successor))) {
                                    queue.add(successor);
                                    oldF[successor] = f(successor);
                                }
                            }
                        }
                    } // end
                }
            } // end successor for loop
            visited[currentNode] = true;
        }

        // never comes to this, as it breaks and exit loop eventually
        return 0;
    }
}
