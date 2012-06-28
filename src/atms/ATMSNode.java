package atms;
import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;

public class ATMSNode implements Comparable<ATMSNode>{
	
	
	private Set<Environment> _Label;
	private String _nodeName;
	private Environment _Justifications1;
	private Environment _Justifications2;
	private Boolean Alarm = false;
	
	private ATMSNode(String nodeName) {
	
		this._Label = new LinkedHashSet<Environment>();
		this._nodeName = nodeName;
	}
	public void addEnvironment(Environment environment) {
		
		_Label.add(environment);
	}
	
	public void setAlarm() {
		Alarm = true;
	}
	

	public boolean getAlarm() {
		return Alarm;
	}
	
	public void addJustification(int justType, Environment environ) {
		if(justType == 1) {
			_Justifications1 = environ;
		}
		else {
			_Justifications2 = environ;
		}
	}
	
	@Override
	public String toString() {
		String nodeString = "\n\nPrinting Node " + _nodeName + " Label:\nlabel of the node is: { " ;
		
		for (Environment env : _Label) {
			nodeString += env + " ";
		}
		nodeString += "}";
		 nodeString += "\n Justifications1:\n" ;
		
			nodeString += _Justifications1 + "\n";
		
		 nodeString += "\n Justifications2:\n";
		
			nodeString += _Justifications2 + "\n";
		
		
		return nodeString;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this == obj;		
	}
	
	@Override
	public int hashCode() {
		return this._nodeName.hashCode();
	}

	/**
	 * getters
	 */
	public Set<Environment> getLabel() {
		return _Label;
		
	}
	
	public String getNodeName() {
		return _nodeName;
	}
	
	public Environment getJustifications1() {
		return _Justifications1;
	}
	
	public Environment getJustifications2() {
		return _Justifications2;
	}
	
	public int compareTo(ATMSNode other) {
		return _nodeName.compareTo(other._nodeName);
	}

	public static Hashtable<String, ATMSNode> nodes = new Hashtable<String, ATMSNode>();
	//TODO: private
	
	public static ATMSNode getNode(String nodeName) {
		ATMSNode node;
		if(nodes.containsKey(nodeName)) {
			node = nodes.get(nodeName);
		}
		else {
			node = new ATMSNode(nodeName);
			nodes.put(nodeName, node);
		}
		
		return node;
	}
	
	public static void putNode(ATMSNode node) {
		nodes.put(node.getNodeName(), node);
	}
	
	/*
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
		
		System.out.println(ATMSNode.getNode("A"));		
		
	}*/

}
