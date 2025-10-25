package Shared;

import java.io.Serializable;


public class Operation implements Serializable{
	private static final long serialVersionUID = 1L;
	private int operande1;
    private int operande2;
    private char operateur;
    
    public Operation(int operande1, int operande2 ,char operateur) {
    	this.operande1=operande1;
    	this.operande2=operande2;
    	this.operateur=operateur;

    }
    public int getOperande1() { return operande1; }
    public int getOperande2() { return operande2; }
    public char getOperateur() { return operateur; }
}
