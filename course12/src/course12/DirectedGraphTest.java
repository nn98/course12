package course12;

import java.util.Scanner;

public class DirectedGraphTest {
	public static void main(String[] args) {
		System.out.println("course12 : ������");

		// ���� �� n �Է�
		Scanner scan = new Scanner(System.in);
		System.out.print("���� �� �Է�: ");
		int n = scan.nextInt();

		// ���� ���� n�� ���� �׷��� graph1�� graph2�� ����
		DirectedMatrixGraph graph1 = new DirectedMatrixGraph(n);
		DirectedListGraph graph2 = new DirectedListGraph(n);

		// ���� �� e �Է�
		System.out.print("���� �� �Է�: ");
		int e = scan.nextInt();

		// e���� ����(���� ��)�� �Է¹޾� graph1�� graph2�� ����
		System.out.println(e + "���� ������ �Է��ϼ���(�� ������ ���� ��ȣ 2���� whitespace�� �����Ͽ� �Է�):");
		for(int i = 0; i < e; i++) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			graph1.addEdge(v1, v2);
			graph2.addEdge(v1, v2);
		}

		// graph1 �� ������ in-degree�� ���
		System.out.println("graph1");
		for(int i = 0; i < n; i++) {
			System.out.println("���� " + i + "�� �������� = " + graph1.inDegree(i));  
		}

		// graph2 �� ������ in-degree�� ���
		System.out.println("graph2");
		for(int i = 0; i < n; i++) {
			System.out.println("���� " + i + "�� �������� = " + graph2.inDegree(i));  
		}
	}
}
class DirectedMatrixGraph {
	private int[][] matrix;  // ���� ���
	private int n;    // ���� ��

	// ���� 0, 1, 2, ..., n-1 �� �׷����� ����
	public DirectedMatrixGraph(int n) {
		matrix = new int[n][n];
		this.n = n;
	}

	// ���� v�� ���������� ���Ͽ� �����ϴ� �޼ҵ� ************* (1)
	public int inDegree(int v) {
		int r=0;
		for(int i=0;i<matrix.length;i++) if(matrix[i][v]==1) r++; 
		return r;    // �޼ҵ� �ϼ� �� ������ ��
	}

	// ���� (v1, v2) ���� ���θ� �˻� - ���� ��ȣ�� �߸��� ���� ���� �߻�
	public boolean hasEdge(int v1, int v2) {
		return matrix[v1][v2] == 1;
	}

	// ���� (v1, v2) �� ����
	public void addEdge(int v1, int v2) {
		if(!isValid(v1) || !isValid(v2)) {
			System.out.println("���� ���� ���� - �߸��� ���� ��ȣ�Դϴ�. <" + v1 + ", " + v2 + ">");
		}
		else if(hasEdge(v1, v2)) {
			System.out.println("���� ���� ���� - �̹� �����ϴ� �����Դϴ�. <" + v1 + ", " + v2 + ">");
		}
		else {
			matrix[v1][v2] = 1;
		}
	}

	// ���� ��ȣ�� ��ȿ���� �˻�
	private boolean isValid(int v) {
		return v >= 0 && v < n;
	}
}


/*
 * ���ϸ�: DirectedListGraph.java
 * �ۼ���: 2018.12.3
 * �ۼ���: ȫ�浿
 * ����: ��������Ʈ�� �̿��Ͽ� ���� �׷����� ������ Ŭ����
 */

class DirectedListGraph {
	private class Node {
		int vertex;
		Node link;
		Node(int vertex) {
			this.vertex = vertex;
		}
	}
	private Node[] list; // ���� ����Ʈ
	private int n;   // ���� ��

	// ���� 0, 1, 2, ..., n-1 �� �׷����� ����
	public DirectedListGraph(int n) {
		list = new Node[n];
		this.n = n;
	}

	// ���� v�� ���������� ���Ͽ� �����ϴ� �޼ҵ� ************* (2)
	public int inDegree(int v) {
		int r=0;
		for(int i=0;i<list.length;i++) {
			Node n=list[i];
			while(n!=null) {
				if(n.vertex==v) r++;
				n=n.link;
			}
		}
		return r;    // �޼ҵ� �ϼ� �� ������ ��
	}


	// ���� (v1, v2) ���� ���θ� �˻� - ���� ��ȣ�� �߸��� ���� ���� �߻�
	public boolean hasEdge(int v1, int v2) {
		Node p = list[v1];
		while(p != null) {
			if(p.vertex == v2)
				return true;
			p = p.link;
		}
		return false;
	}

	// ���� (v1, v2)�� ����
	public void addEdge(int v1, int v2) {
		if(!isValid(v1) || !isValid(v2)) {
			System.out.println("���� ���� ���� - �߸��� ���� ��ȣ�Դϴ�. (" + v1 + ", " + v2 + ")");
		}
		else if(hasEdge(v1, v2)) {
			System.out.println("���� ���� ���� - �̹� �����ϴ� �����Դϴ�. (" + v1 + ", " + v2 + ")");
		}
		else {
			Node newNode = new Node(v2);
			newNode.link = list[v1];
			list[v1] = newNode;
		}
	}

	// ���� ��ȣ�� ��ȿ���� �˻�
	private boolean isValid(int v) {
		return v >= 0 && v < n;
	}
}