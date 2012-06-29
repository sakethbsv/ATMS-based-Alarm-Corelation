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
		
		Set<Environment> EA, EA1, EA2 = null;
		if(!J1._nodes.isEmpty()){
			Set<Environment> E = _faultNode.getLabel();
			//System.out.println(E);
					
	EA = E;
//	System.out.println(EA);
			
			
			for (ATMSNode node : J1._nodes) {
			/*	
				System.out.println(E);
				System.out.println(node);
				for (Environment env : E) {
					if(env._nodes.contains(node)){
					EA = env;
					EA1 = EA;
					break; 	
					}
				}		
				//if(!env._nodes.isEmpty())
				//System.out.println(EA);
				//System.out.println(node.getLabel());
				 */
				//EA1 = EA;
				EA.removeAll(node.getLabel());
				EA2 = EA; 
				
				
				boolean status = Test(node);
				//System.out.println(status);
				
				if(status == false){
				//	System.out.println(node);
					ATMS nextAtms = new ATMS(nodes, node);
				//	System.out.println(nextAtms._nogoods);
					_nogoods.addAll(nextAtms._nogoods);
					
				}
				
				
				//atms.J1._nodes.remove(node);
			}
			_nogoods.addAll(EA2);
			
			
			
		}
		else if(!J2._nodes.isEmpty()){
			//Goto Step6
		}
		//Set<Environment> E = _nogoods = _faultNode.getLabel();
		
		//Goto Step9
		
		
		

		
	}
		
	public static boolean Test(ATMSNode node){
		
		return node.getAlarm();
		
	}
	
public static void main(String[] args) {
		
		TreeSet<ATMSNode> en = new TreeSet<ATMSNode>();
		en.add(ATMSNode.getNode("1"));
		en.add(ATMSNode.getNode("2"));
		en.add(ATMSNode.getNode("3"));
		en.add(ATMSNode.getNode("4"));
		en.add(ATMSNode.getNode("5"));
		Environment e = Environment.getEnvironment(en);
		
		
		ATMSNode.getNode("1").addEnvironment(Environment.getEnvironment("1"));
		

		TreeSet<ATMSNode> env1 = new TreeSet<ATMSNode>();
		env1.add(ATMSNode.getNode("A"));
		env1.add(ATMSNode.getNode("2"));
		Environment e1 = Environment.getEnvironment(env1);
		
		ATMSNode.getNode("2").addEnvironment(e1);
		
		TreeSet<ATMSNode> env2 = new TreeSet<ATMSNode>();
		env2.add(ATMSNode.getNode("B"));
		env2.add(ATMSNode.getNode("3"));
		//env1.add(ATMSNode.getNode("2").getLabel());
		Environment e2 = Environment.getEnvironment(env2);
		
		ATMSNode.getNode("3").addEnvironment(e2);
		ATMSNode.getNode("3").addEnvironment(ATMSNode.getNode("2").getLabel());
		ATMSNode.getNode("3").addJustification(1, Environment.getEnvironment("2"));

		
		TreeSet<ATMSNode> env3 = new TreeSet<ATMSNode>();
		env3.add(ATMSNode.getNode("C"));
		env3.add(ATMSNode.getNode("4"));
		//env1.add(ATMSNode.getNode("2").getLabel());
		Environment e3 = Environment.getEnvironment(env3);
		
		ATMSNode.getNode("4").addEnvironment(e3);
		ATMSNode.getNode("4").addEnvironment(ATMSNode.getNode("2").getLabel());
		ATMSNode.getNode("4").addJustification(1, Environment.getEnvironment("2"));
		
		TreeSet<ATMSNode> env4 = new TreeSet<ATMSNode>();
		env4.add(ATMSNode.getNode("5"));
		env4.add(ATMSNode.getNode("F"));
		
		//env1.add(ATMSNode.getNode("2").getLabel());
		Environment e4 = Environment.getEnvironment(env4);
		
		TreeSet<ATMSNode> env5 = new TreeSet<ATMSNode>();
		env5.add(ATMSNode.getNode("5"));
		env5.add(ATMSNode.getNode("E"));
		
		//env1.add(ATMSNode.getNode("2").getLabel());
		Environment e5 = Environment.getEnvironment(env5);

		TreeSet<ATMSNode> env6 = new TreeSet<ATMSNode>();
		env6.add(ATMSNode.getNode("4"));
		env6.add(ATMSNode.getNode("3"));
		
		Environment e6 = Environment.getEnvironment(env6);

		
		ATMSNode.getNode("5").addEnvironment(e4);
		ATMSNode.getNode("5").addEnvironment(e5);
		ATMSNode.getNode("5").addEnvironment(ATMSNode.getNode("3").getLabel());
		ATMSNode.getNode("5").addEnvironment(ATMSNode.getNode("4").getLabel());
		ATMSNode.getNode("5").addJustification(1, e6);
		//ATMSNode.getNode("5").addJustification(1, Environment.getEnvironment("4"));
		
		//System.out.println(ATMSNode.getNode("1"));
		//System.out.println(ATMSNode.getNode("2"));
		//System.out.println(ATMSNode.getNode("3"));
		//System.out.println(ATMSNode.getNode("4"));
		//System.out.println(ATMSNode.getNode("5"));		
		//System.out.println(ATMSNode.getNode("6"));
		//ATMSNode.getNode("4").setAlarm();
		//ATMSNode.getNode("3").setAlarm();
		
		ATMS atms = new ATMS(e, ATMSNode.getNode("5"));
		System.out.println("The Diagnosis yields :" + atms._nogoods);
		
			}
}
