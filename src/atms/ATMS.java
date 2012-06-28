package atms;

import java.util.*;

public class ATMS {
	
	public Set<Environment> _nogoods;
	private Environment _nodes;
	public Environment J1;
	public Environment J2;
	public ATMSNode _faultNode;

	/**
	 * constructor
	 */
	public ATMS(Environment nodes, ATMSNode A){
		//_prevJust = new LinkedHashSet<Justification>();
		_nogoods = new LinkedHashSet<Environment>();
		_nodes = nodes;
		 _faultNode = A;
		J1 = A.getJustifications1();
		J2 = A.getJustifications2();
	//	System.out.println(A);
	//	System.out.println(J1);
	//	System.out.println(J2);
		
	}
		
	private boolean Test(ATMSNode node){
		
		return node.getAlarm();
		
	}
	
public static void main(String[] args) {
		
		TreeSet<ATMSNode> envSet1 = new TreeSet<ATMSNode>();
		envSet1.add(ATMSNode.getNode("A"));
		envSet1.add(ATMSNode.getNode("A"));
		envSet1.add(ATMSNode.getNode("B"));
		envSet1.add(ATMSNode.getNode("C"));
		Environment e1 = Environment.getEnvironment(envSet1);
		
		
		ATMSNode.getNode("A").addEnvironment(e1);
		
		TreeSet<ATMSNode> envSet12 = new TreeSet<ATMSNode>();
		envSet12.add(ATMSNode.getNode("A"));
		envSet12.add(ATMSNode.getNode("A"));
		envSet12.add(ATMSNode.getNode("B"));
		envSet12.add(ATMSNode.getNode("C"));
		Environment e12 = Environment.getEnvironment(envSet12);
		
		//ATMSNode.getNode("A").addEnvironment(1, e12);
		
		Environment e2 = Environment.getEnvironment("C");
		
		TreeSet<ATMSNode> envSet3 = new TreeSet<ATMSNode>();
		envSet3.add(ATMSNode.getNode("F"));
		envSet3.add(ATMSNode.getNode("G"));
		envSet3.add(ATMSNode.getNode("B"));
		envSet3.add(ATMSNode.getNode("E"));
		Environment e3 = Environment.getEnvironment(envSet3);
		
		//ATMSNode.getNode("A").addEnvironment(1, e2);
		ATMSNode.getNode("A").addJustification(1, e2);
		ATMSNode.getNode("A").addEnvironment(e3);
		
	//	System.out.println(ATMSNode.getNode("A"));	
		
		ATMSNode.getNode("B").addJustification(1, e1);
		ATMSNode.getNode("B").addEnvironment(e2);
		
	//	System.out.println(ATMSNode.getNode("B"));
		
		TreeSet<ATMSNode> envSet4 = new TreeSet<ATMSNode>();

		envSet4.add(ATMSNode.getNode("A"));
		envSet4.add(ATMSNode.getNode("B"));
		Environment e4 = Environment.getEnvironment(envSet4);
		
		
		ATMS atms = new ATMS(e4, ATMSNode.getNode("A"));
		
		Environment EA = null, EA1, EA2;
		//System.out.println(atms.J2);
		if(!atms.J1._nodes.isEmpty()){
			//Goto Step3
			
			//Start Step3
			for (ATMSNode node : atms.J1._nodes) {
				
				Set<Environment> E = atms._faultNode.getLabel();
				for (Environment env : E) {
					if(env._nodes.contains(node)){
					EA = env;
					EA1 = EA;
					}
					break;					
				}
				for (Environment env : node.getLabel()) {
					EA._nodes.removeAll(env._nodes);					
				}
				
				EA2 = EA; 
				boolean status = atms.Test(node);
				if(status == false){
					ATMS nextAtms = new ATMS(e4, node);
					atms._nogoods.addAll(nextAtms._nogoods);
				}
				else{
					atms._nogoods.add(EA2);
				}
				//atms.J1._nodes.remove(node);
			}
			System.out.println("The Diagnosis yields :" + atms._nogoods);
			
		 
		}
		else if(!atms.J2._nodes.isEmpty()){
			//Goto Step6
		}
		Set<Environment> E = atms._nogoods = atms._faultNode.getLabel();
		
		//Goto Step9
		
		
		
	}
}
