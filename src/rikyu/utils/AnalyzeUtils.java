package rikyu.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.java.sen.SenFactory;
import net.java.sen.StringTagger;
import net.java.sen.dictionary.Token;
import rikyu.model.Word;

public class AnalyzeUtils {

	/**
	 * 形態素解析
	 *
	 * @param string
	 * @return
	 */
	public static ArrayList<Word> analyze(String string) {
		StringTagger tagger = SenFactory.getStringTagger(null);
		List<Token> tokens = new ArrayList<Token>();
		ArrayList<Word> arrayList = new ArrayList<Word>();
		try {
			tagger.analyze(string, tokens);
			for (Token token : tokens) {
				Word word = new Word();
				word.setSurface(token.getSurface());
				List<String> readings = token.getMorpheme().getReadings();
				if (readings != null && readings.size() > 0) {
					word.setReading(readings.get(0));
				}
				String partOfSpeech = token.getMorpheme().getPartOfSpeech();
				if (partOfSpeech != null) {
					String[] speechs = partOfSpeech.split("-", 0);
					if (speechs.length > 0) {
						word.setPos(speechs[0]);
					}
				}
				arrayList.add(word);
			}
		} catch (IOException e) {
		}
		return arrayList;

	}


}
