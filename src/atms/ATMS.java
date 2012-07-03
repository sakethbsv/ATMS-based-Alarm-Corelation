package atms;

import java.util.*;

public class ATMS {
	
	public Set<Environment> _nogoods;
	public Set<Environment> _nogoodsTemp;
	private Environment _nodes;
	public Environment J1;
	public Environment J2;
	public ATMSNode _faultNode;
	public Set<Environment> EA, EA1, EA2;
	/**
	 * constructor
	 */
	public ATMS(ATMSNode A){

		_nogoods = new LinkedHashSet<Environment>();
	//	_nodes = nodes;
		 _faultNode = A;
		J1 = A.getJustifications1();
		J2 = A.getJustifications2();
		
	if(!J1._nodes.isEmpty()){
			
		Set<Environment> E = _faultNode.getLabel();
		EA = E;
			
		for (ATMSNode node : J1._nodes) {
		
			EA.removeAll(node.getLabel());
			EA2 = EA; 
		
			boolean status = Test(node);
				
			if(status == false){
					
				ATMS nextAtms = new ATMS(node);
				_nogoods.addAll(nextAtms._nogoods);
				
				}
			}
			
		_nogoods.addAll(EA2);
				
		}
	
		else if(!J2._nodes.isEmpty()){
			for (ATMSNode node : J2._nodes) {
				boolean status = Test(node);
				
				if(status == false){
					
						ATMS nextAtms = new ATMS(node);
						_nogoodsTemp.addAll(nextAtms._nogoods);
						
					}
				else{
					for (Environment env : EA) {
							
							Set<Environment> intersection = new HashSet<Environment>(EA);
							intersection.retainAll(node.getLabel());
							if(intersection!=null){
							EA2 = intersection;
							System.out.println(EA2);
							_nogoodsTemp.addAll(EA2);
							}
					}
						
				}
			}
			for(Environment env : _nogoodsTemp){
				Set<Environment> Temp;
				Temp = _nogoodsTemp;
				Temp.remove(env);
				for(Environment env1 : Temp){
					
					if(env.isSuperset(env1)){
						_nogoodsTemp.remove(env);
						
					}
					else{
						_nogoodsTemp.remove(env1);
					}
				}
				
			}
		}
	
	}
		
		
	public static boolean Test(ATMSNode node){
		
		return node.getStatus();
		
	}
	
public static void main(String[] args) {
		
	    Parser parser = new Parser("config.xml");
	    /*	
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
		 
		 */
		ATMSNode.getNode("4").setAlarm();
		//ATMSNode.getNode("3").setAlarm();
		
		ATMS atms = new ATMS(ATMSNode.getNode("5"));
		System.out.println("The Diagnosis yields :" + atms._nogoods);
		System.out.println("The AlternateDiagnosis yields :" + atms._nogoodsTemp);
		
			}
}
