package cn.edu.hncj.gcl.eno4j_demo.service;

public interface Neo4jService {
	void add();
	void delete();
	void change();
	void query(String name);
}
