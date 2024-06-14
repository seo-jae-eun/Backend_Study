package astar;

import java.util.*;

public class Astar {
    final Integer DEFAULT_COST = 10;
    final Integer DEFAULT_DIAGONAL_COST = 14;

    Integer rowSize;
    Integer colSize;

    // 맵의 크기 저장 변수
    Integer[][] map;

    Node start;
    Node goal;

    List<Node> openList;
    List<Node> closeList;

    // 생성자
    //  맵을 전달받아서
    //  맵 변수에 저장
    //  맵의 가로 길이
    //  맵의 세로 길이
    //  출발지 노드
    //  목적지 노드
    //  열린 목록 생성
    //  닫힌 목록 생성
    public Astar(Integer[][] map) {
        this.map = map;
        this.rowSize = map[0].length;
        this.colSize = map.length;
        // 초기화할 때 맵 정보를 전달 받아서 출발지와 목적지의 좌표를 변수에 저장
        this.start = findStart(map);
        this.goal = findGoal(map);
        this.openList = new ArrayList<>();
        this.closeList = new ArrayList<>();
    }

    // 출발지 찾는 메소드
    public Node findStart(Integer[][] map) {
        Node node = null;
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 1) {
                    node = new Node(i, j);
                    return node;
                }
            }
        }
        return node;
    }

    // 목적지 찾는 메소드
    public Node findGoal(Integer[][] map) {
        Node node = null;
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 2) {
                    node = new Node(i, j);
                    return node;
                }
            }
        }
        return node;
    }

    // 최단 경로 찾는 메소드
    public void findPath() {
        // 시작 위치를 닫힌 목록에 넣고 시작
        closeList.add(start);
        Node currentNode = start;

        Node[] friends = new Node[8];
        while (true) {
            // 오른쪽으로 y+1
            // 왼쪽으로 y-1
            // 위로 x-1
            // 아래로 x+1

            // 현재 위치 주변 8방향 확인
            if(currentNode.getY() != rowSize - 1) {
                // 오
                Node right = new Node(currentNode.getX(), currentNode.getY() + 1, currentNode);
                friends[0] = right;
                // 오위
                if(currentNode.getX() != 0) {
                    Node topRight = new Node(currentNode.getX() - 1,currentNode.getY() + 1, currentNode);
                    friends[1] = topRight;
                }
            }
            // 왼
            if(currentNode.getY() != 0) {
                Node left = new Node(currentNode.getX(),currentNode.getY() - 1, currentNode);
                friends[2] = left;
                // 왼아래
                if(currentNode.getX() != colSize - 1) {
                    Node leftBottom = new Node(currentNode.getX() + 1,currentNode.getY() - 1, currentNode);
                    friends[3] = leftBottom;
                }
            }
            // 아래
            if(currentNode.getX() != colSize - 1) {
                Node bottom = new Node(currentNode.getX() + 1, currentNode.getY(), currentNode);
                friends[4] = bottom;
                // 아래오
                if(currentNode.getY() != rowSize - 1) {
                    Node bottomRight = new Node(currentNode.getX() + 1, currentNode.getY() + 1, currentNode);
                    friends[5] = bottomRight;
                }
            }
            // 위
            if(currentNode.getX() != 0) {
                Node top = new Node(currentNode.getX() - 1, currentNode.getY(), currentNode);
                friends[6] = top;
                // 위왼
                if(currentNode.getY() != 0) {
                    Node topLeft = new Node(currentNode.getX() - 1, currentNode.getY() - 1, currentNode);
                    friends[7] = topLeft;
                }
            }
            for(int i = 0; i < friends.length; i++) {
                if(friends[i] != null) {
                    // 장애물 제외, 닫힌 목록에 있으면 제외
                    if(map[friends[i].getX()][friends[i].getY()] != 3 && !isCloseList(friends[i])) {
                        friends[i].setF(fCalc(friends[i]));
                        friends[i].setG(gCalc(friends[i]));
                        friends[i].setH(friends[i].getF() + friends[i].getG());

                        // 이미 열린 목록에 있으면
                        if(findOpenList(friends[i]) != null) {
                            Node originNode = findOpenList(friends[i]);
                            // FGH 및 부모가 더 적은 값이라면 변경
                            if(originNode.getF() > friends[i].getF() && originNode.getG() > friends[i].getG() && originNode.getH() > friends[i].getH()) {
                                openList.remove(originNode);
                                friends[i].setParents(currentNode);
                                friends[i].setF(friends[i].getF());
                                friends[i].setG(friends[i].getG());
                                friends[i].setH(friends[i].getH());
                                openList.add(friends[i]);
                            }
                        }
                        else {
                            friends[i].setParents(currentNode);
                            openList.add(friends[i]);
                        }
                    }
                }
            }

            // 열린 목록에서 H가 제일 작은 노드를 가져와서 현재 위치를 가져온 노드로 변경
            currentNode = findLowH();
            openList.remove(currentNode);
            closeList.add(currentNode);

            // 목적지에 도착하면 멈춤
            if(currentNode.getX().equals(goal.getX()) && currentNode.getY().equals(goal.getY())) {
                break;
            }
        }
        // 목적지에 도착하면 마지막 현재 위치에서부터 출발지까지 부모 노드를 가져와서 스택에 넣고 pop() 하면서 그리기
        drawPath(map, getPath(currentNode));
    }

    // 맵에 최단경로를 8로 보여주는 메소드
    public void drawPath(Integer[][] map, Stack stack) {
        while(!stack.isEmpty()) {
            Node path = (Node) stack.pop();
            for (int i = 0; i < map[0].length; i++) {
                for (int j = 0; j < map.length; j++) {
                    if(start.getX() == path.getX() && start.getY() == path.getY()) {
                        continue;
                    }
                    if(goal.getX() == path.getX() && goal.getY() == path.getY()) {
                        continue;
                    }
                    map[path.getX()][path.getY()] = 8;

                }
            }
        }
        for (int i = 0; i < map[0].length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    // 목적지부터 도착지까지 경로를 스택에 넣는 메소드
    public Stack getPath(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        while(true) {
            node = node.getParents();
            stack.push(node);

            if(node.getX().equals(start.getX()) && node.getY().equals(start.getY())) {
                break;
            }
        }
        return stack;
    }

    // 열린 목록에서 H가 제일 작은 노드 찾는 메소드
    public Node findLowH() {
        int lowH = Integer.MAX_VALUE;
        for(Node n : openList) {
            if(n.getH() < lowH) {
                lowH = n.getH();
            }
        }
        for(Node n : openList) {
            if(n.getH() == lowH) {
                return n;
            }
        }
        return null;
    }
    
    // 닫힌 목록에 있는지 확인하는 메소드
    public boolean isCloseList(Node node) {
        for(Node n : closeList) {
            if(n.getX().equals(node.getX()) && n.getY().equals(node.getY())) {
                return true;
            }
        }
        return false;
    }

    // 열린 목록에 있는지 찾아서 Node 반환하는 메소드
    public Node findOpenList(Node node) {
        for(Node n : openList) {
            if(n.getX().equals(node.getX()) && n.getY().equals(node.getY())) {
                return n;
            }
        }
        return null;
    }

    // F값(출발지에서 얼마나 떨어져있나 (한칸은 10, 대각선은 14))을 계산하는 메소드
    public Integer fCalc(Node node) {
        int diffX = Math.abs(node.getParents().getX() - node.getX());
        int diffY = Math.abs(node.getParents().getY() - node.getY());

        if(diffX == 0 || diffY == 0) {
            return DEFAULT_COST;
        } else {
            return DEFAULT_DIAGONAL_COST;
        }
    }

    // G값(목적지까지 얼마나 이동해야하나)을 계산하는 메소드
    public Integer gCalc(Node node) {
        int diffX = Math.abs(start.getX() - node.getX());
        int diffY = Math.abs(start.getY() - node.getY());

        return (diffX + diffY) * DEFAULT_COST;
    }
}
