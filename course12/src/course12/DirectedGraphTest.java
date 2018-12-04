package course12;

import java.util.Scanner;

public class DirectedGraphTest {
	public static void main(String[] args) {
		System.out.println("course12 : 김윤서");

		// 정점 수 n 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("정점 수 입력: ");
		int n = scan.nextInt();

		// 정점 수가 n인 방향 그래프 graph1과 graph2를 생성
		DirectedMatrixGraph graph1 = new DirectedMatrixGraph(n);
		DirectedListGraph graph2 = new DirectedListGraph(n);

		// 간선 수 e 입력
		System.out.print("간선 수 입력: ");
		int e = scan.nextInt();

		// e개의 간선(정점 쌍)을 입력받아 graph1과 graph2에 삽입
		System.out.println(e + "개의 간선을 입력하세요(각 간선은 정점 번호 2개를 whitespace로 구분하여 입력):");
		for(int i = 0; i < e; i++) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			graph1.addEdge(v1, v2);
			graph2.addEdge(v1, v2);
		}

		// graph1 각 정점의 in-degree를 출력
		System.out.println("graph1");
		for(int i = 0; i < n; i++) {
			System.out.println("정점 " + i + "의 진입차수 = " + graph1.inDegree(i));  
		}

		// graph2 각 정점의 in-degree를 출력
		System.out.println("graph2");
		for(int i = 0; i < n; i++) {
			System.out.println("정점 " + i + "의 진입차수 = " + graph2.inDegree(i));  
		}
	}
}
class DirectedMatrixGraph {
	private int[][] matrix;  // 인접 행렬
	private int n;    // 정점 수

	// 정점 0, 1, 2, ..., n-1 인 그래프를 생성
	public DirectedMatrixGraph(int n) {
		matrix = new int[n][n];
		this.n = n;
	}

	// 정점 v의 진입차수를 구하여 리턴하는 메소드 ************* (1)
	public int inDegree(int v) {
		int r=0;
		for(int i=0;i<matrix.length;i++) if(matrix[i][v]==1) r++; 
		return r;    // 메소드 완성 후 삭제할 것
	}

	// 간선 (v1, v2) 존재 여부를 검사 - 정점 번호가 잘못된 경우는 예외 발생
	public boolean hasEdge(int v1, int v2) {
		return matrix[v1][v2] == 1;
	}

	// 간선 (v1, v2) 를 삽입
	public void addEdge(int v1, int v2) {
		if(!isValid(v1) || !isValid(v2)) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. <" + v1 + ", " + v2 + ">");
		}
		else if(hasEdge(v1, v2)) {
			System.out.println("간선 삽입 오류 - 이미 존재하는 간선입니다. <" + v1 + ", " + v2 + ">");
		}
		else {
			matrix[v1][v2] = 1;
		}
	}

	// 정점 번호의 유효성을 검사
	private boolean isValid(int v) {
		return v >= 0 && v < n;
	}
}


/*
 * 파일명: DirectedListGraph.java
 * 작성일: 2018.12.3
 * 작성자: 홍길동
 * 설명: 인접리스트를 이용하여 방향 그래프를 구현한 클래스
 */

class DirectedListGraph {
	private class Node {
		int vertex;
		Node link;
		Node(int vertex) {
			this.vertex = vertex;
		}
	}
	private Node[] list; // 인접 리스트
	private int n;   // 정점 수

	// 정점 0, 1, 2, ..., n-1 인 그래프를 생성
	public DirectedListGraph(int n) {
		list = new Node[n];
		this.n = n;
	}

	// 정점 v의 진입차수를 구하여 리턴하는 메소드 ************* (2)
	public int inDegree(int v) {
		int r=0;
		for(int i=0;i<list.length;i++) {
			Node n=list[i];
			while(n!=null) {
				if(n.vertex==v) r++;
				n=n.link;
			}
		}
		return r;    // 메소드 완성 후 삭제할 것
	}


	// 간선 (v1, v2) 존재 여부를 검사 - 정점 번호가 잘못된 경우는 예외 발생
	public boolean hasEdge(int v1, int v2) {
		Node p = list[v1];
		while(p != null) {
			if(p.vertex == v2)
				return true;
			p = p.link;
		}
		return false;
	}

	// 간선 (v1, v2)를 삽입
	public void addEdge(int v1, int v2) {
		if(!isValid(v1) || !isValid(v2)) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. (" + v1 + ", " + v2 + ")");
		}
		else if(hasEdge(v1, v2)) {
			System.out.println("간선 삽입 오류 - 이미 존재하는 간선입니다. (" + v1 + ", " + v2 + ")");
		}
		else {
			Node newNode = new Node(v2);
			newNode.link = list[v1];
			list[v1] = newNode;
		}
	}

	// 정점 번호의 유효성을 검사
	private boolean isValid(int v) {
		return v >= 0 && v < n;
	}
}