package dictionary;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;

public class TrieNodeTest {

	@Test
	public void getElementTest() {
		TreeSearchable test = new TrieNode('a');
		assertTrue(test.getElement() == 'a');
	}
	
	@Test
	public void isWordTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		assertTrue(smallDictionary.isWord("a"));
		assertTrue(smallDictionary.isWord("bunny"));
		assertTrue(!smallDictionary.isWord("abunny"));
		assertTrue(!smallDictionary.isWord("bunnyabet"));
		
	}
	
	@Test
	public void isWordEndTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		TreeSearchable aNode = smallDictionary.getNode('a');
		assertTrue(aNode.getElement()=='a');
		assertTrue(aNode.isWordEnd());	
		TreeSearchable otherNode = smallDictionary.getNode('b');
		assertFalse(otherNode.isWordEnd());
	}
	
	@Test
	public void getNodeTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		TreeSearchable aNode = smallDictionary.getNode('a');
		assertTrue(aNode.getElement()=='a');
		TreeSearchable otherNode = smallDictionary.getNode('z');
		assertTrue(otherNode == null);
		
	}
	
	@Test
	public void getNodesTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		Collection<TreeSearchable> children = smallDictionary.getNodes();
		String childChars = "";
		for (TreeSearchable i :  children){
			childChars+= i.getElement();
		}
		assertTrue(childChars.equals("ab")||childChars.equals("ba"));
	}
	
	@Test
	public void addWordTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		assertFalse(smallDictionary.isWord("zoo"));
		smallDictionary.addWord("zoo");
		assertTrue(smallDictionary.isWord("zoo"));
	}
	
	@Test
	public void searchNodeTest(){
		TreeSearchable smallDictionary = new TrieNode('\0');
		smallDictionary.addWord("a");
		smallDictionary.addWord("bunny");
		smallDictionary.addWord("abet");
		smallDictionary.addWord("abide");
		smallDictionary.addWord("abode");
		TreeSearchable ab = smallDictionary.searchNode("ab");
		assertTrue(ab.getElement()=='b');
		assertFalse(ab.isWordEnd());
		String childnodes = "";
		Collection<TreeSearchable> nodes = ab.getNodes();
		for (TreeSearchable i : nodes){
			childnodes+=i.getElement();
		}
		char[] childalphabet = childnodes.toCharArray();
		Arrays.sort(childalphabet);
		String sorted = new String(childalphabet);
		assertTrue(sorted.equals("eio"));
		
		TreeSearchable otherpath = smallDictionary.searchNode("ca");
		assertTrue(otherpath == null);
		otherpath = smallDictionary.searchNode("boisterious");
		assertTrue(otherpath == null);
	}
	
	
	
	
	
	
	

}
