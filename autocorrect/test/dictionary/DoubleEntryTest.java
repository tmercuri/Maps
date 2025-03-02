package dictionary;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleEntryTest {

	@Test
	public void getStringTest() {
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		assertTrue(testdouble.getString().equals("test ing"));
	}
	
	@Test
	//This test has to be circumstantial as we can't access total publically, but we can infer it changes
	//by the changing probabilities of unigramProbabilities.
	public void setTotalTest(){
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		testdouble.setTotal(5);
		assertTrue(test.getUnigramProb() == 0.2);
		testdouble.setTotal(4);
		assertTrue(test.getUnigramProb() == 0.25);	
	}
	
	//This is identical as above; I include it to have one test per method but getUnigramProb has been
	//implicitly tested already
	public void getUnigramProbTest(){
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		testdouble.setTotal(5);
		assertTrue(test.getUnigramProb() == 0.2);
		testdouble.setTotal(4);
		assertTrue(test.getUnigramProb() == 0.25);	
	}
	
	@Test
	public void addUnigramCountTest(){
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		testdouble.setTotal(5);
		assertTrue(test.getUnigramProb() == 0.2);
		testdouble.addUnigramCount();
		assertTrue(test.getUnigramProb() == 0.4);	
	}
	
	@Test
	public void addBigramCountTest(){
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		testdouble.setTotal(10);
		testdouble.addBigramCount("a");
		assertTrue(testdouble.getBigramProb("a")==0.1);
		testdouble.addBigramCount("a");
		assertTrue(testdouble.getBigramProb("a")==0.2);
	}
	
	@Test
	public void getBigramProbTest(){
		WordEntry test = new WordEntry("test");
		WordEntry test2 = new WordEntry("ing");
		DoubleEntry testdouble = new DoubleEntry(test, test2);
		testdouble.setTotal(10);
		testdouble.addBigramCount("a");
		assertTrue(testdouble.getBigramProb("a")==0.1);
		testdouble.addBigramCount("a");
		assertTrue(testdouble.getBigramProb("a")==0.2);
		assertTrue(testdouble.getBigramProb("notaword") == 0);
	}
	
	

}
