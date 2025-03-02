package ranker;

import java.util.Comparator;

import dictionary.Entry;
/**BasicRanker allows for the comparing of two entries and determining which should get precedence in being output.
 * The ranking is in ascending order, so to place the more important words first, call Collections.reverseorder on this Ranker
 * @author ja11
 *
 */
public class BasicRanker implements Comparator<Entry>{
	
	/** prevWord: the word prior to the word being searched**/
	private String prevWord;
	
	/** search: the word being searched**/
	private String search;

	
	/** Bigram ranker constructor
	 * @oaram prevWord : the previous word in a search term
	 * @param search: the word to be searched
	 */
	public BasicRanker(String prevWord, String search){
		this.prevWord = prevWord;
		this.search = search;
	}
	
	/** Unigram rankerconstructor
	 * 
	 * @param search: the word to be searched
	 */
	public BasicRanker(String search){
		this.search = search;
	}
	/**
	 * @param o1: the first Entry 
	 * @param o2: the second Entry
	 * @return an int whose sign signifies how o1 compares to o2.
	 * If either o1 or o2's String is equal to the word to be searched, that is always the "highest" value.
	 * If applicable, bigram probabilites will be compared. 
	 * If these are not available or are equal, the unigram probabilities are used.
	 * If these are also inconclusive, the words are sorted reverse-alphabetically.
	 */
	@Override
	public int compare(Entry o1, Entry o2) {
		if (o1.getString().equals(search)){
			if (o2.getString().equals(search)) return 0;
			else return 1;
		}
		else if (o2.getString().equals(search)) return -1;
		else if ((prevWord != null) && (o1.getBigramProb(prevWord) != o2.getBigramProb(prevWord)))
			return Double.compare(o1.getBigramProb(prevWord), o2.getBigramProb(prevWord));
		else if(o1.getUnigramProb()!= o2.getUnigramProb())
			return Double.compare(o1.getUnigramProb(),o2.getUnigramProb());
		else return -(o1.getString().compareTo(o2.getString()));
	}

}
