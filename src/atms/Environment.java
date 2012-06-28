package atms;

import java.util.*;

public class Environment {

	
	private static Hashtable<Set<ATMSNode>,Environment> _environments = new Hashtable<Set<ATMSNode>, Environment>();

	public static final Environment PREMIS = initPremis();
	
	private static Environment initPremis(){
		Environment premis = new Environment(new TreeSet<ATMSNode>());
		_environments.put(premis.getNodes(), premis);
		return premis;
	}
	
	public Set<ATMSNode> _nodes;
	private boolean _nogood;
	
	
	public static Environment getEnvironment(TreeSet<ATMSNode> nodes) {
		Environment targetEnv = new Environment(nodes);
		if(_environments.containsKey(nodes)) {
			targetEnv = _environments.get(nodes);
		}
		else {
			_environments.put(targetEnv.getNodes(), targetEnv);
		}
		return targetEnv;
	}
	
	public static Environment getEnvironment(String nodeName) {
		TreeSet<ATMSNode> nodes = new TreeSet<ATMSNode>();
		nodes.add(ATMSNode.getNode(nodeName));
		Environment targetEnv = new Environment(nodes);
		if(_environments.containsKey(nodes)) {
			targetEnv = _environments.get(nodes);
		}
		else {
			_environments.put(nodes, targetEnv);
		}
		return targetEnv;
	}

	public static void resatEnvironments() {
		for (Environment env : _environments.values()) {
			env.markGood();
		}
	}
	
//Contructor
	
	private Environment(TreeSet<ATMSNode> nodes) {
		_nodes = nodes;
	}
	
	public Set<ATMSNode> getNodes() {
		return this._nodes;
	}
	
	public boolean isSuperset(Environment env) {
		return getNodes().containsAll(env.getNodes());
	}
	
	public String toString() {
		String ans = " { ";
		for (ATMSNode n : this._nodes) {
			ans += n.getNodeName()+" ";
		}
		ans += "} ";
		return ans;
	}
	
	@Override
	public boolean equals(Object env) {
		return env instanceof Environment && (this == env || (this.getNodes().size() == ((Environment) env).getNodes().size() &&
				this.getNodes().containsAll(((Environment) env).getNodes())));
	}

	public boolean isNogood() {
		return _nogood;
	}
	
	public void markNogood() {
		_nogood = true;
	}
	
	private void markGood() {
		_nogood = false;	
	}

	/**
	 * unit testing
	 * @param args
	 */
	public static void main(String[] args) {
		TreeSet<ATMSNode> sysComponents = new TreeSet<ATMSNode>();
		sysComponents.add(ATMSNode.getNode("1"));
		sysComponents.add(ATMSNode.getNode("2"));
		sysComponents.add(ATMSNode.getNode("3"));
		sysComponents.add(ATMSNode.getNode("4"));
		sysComponents.add(ATMSNode.getNode("5"));
		sysComponents.add(ATMSNode.getNode("6"));
		sysComponents.add(ATMSNode.getNode("7"));
		
		Environment a = Environment.getEnvironment(sysComponents);
		System.out.println(a);

		
		TreeSet<ATMSNode> sysComponents2 = new TreeSet<ATMSNode>();
		sysComponents2.add(ATMSNode.getNode("1"));
		sysComponents2.add(ATMSNode.getNode("2"));
		sysComponents2.add(ATMSNode.getNode("4"));
		
		Environment b = Environment.getEnvironment(sysComponents2);
		System.out.println(b);
		
		
		TreeSet<ATMSNode> sysComponents3 = new TreeSet<ATMSNode>();
		sysComponents3.add(ATMSNode.getNode("a"));
		
		Environment c = Environment.getEnvironment(sysComponents3);
		System.out.println(c);
		
		TreeSet<ATMSNode> sysComponents4 = new TreeSet<ATMSNode>(a.getNodes());
		sysComponents4.add(ATMSNode.getNode("a"));
		
		Environment d = Environment.getEnvironment(sysComponents4);
		System.out.println(d);
		
		System.out.println(b);

		System.out.println(b.equals(b));
		System.out.println(b.equals(c));
		System.out.println(b.equals("sdfv"));
	}
}