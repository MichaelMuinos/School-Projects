package edu.cpp.cs.cs241.prog_assgmnt_4;
/**
 * Interface in which acts as a template for the node class. It keeps track of a nodes
 * id, name, and appropriate data.
 * @author Luke
 *
 * @param <I>
 * @param <N>
 * @param <D>
 */
public interface NodeInterface<I,N,D> {
/**
 * Pre: Id is not null.
 * Post: True
 * @return
 */
	I getID();
/**
 * Name is not null.
 * Post: True
 * @return
 */
	N getName();
/**
 * Pre: Data is not null.
 * Post: True
 * @return
 */
	D getData();
	
}
