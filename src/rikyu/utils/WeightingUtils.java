package rikyu.utils;

import rikyu.Rikyu;
import rikyu.model.Algorithm;
import rikyu.model.Word;

public class WeightingUtils {

	/**
	 * 重み付け処理
	 *
	 * @param Word
	 */
	public static void calcWeight(Word word) {
		Algorithm algorithm = getAlgorithmBySentence(word);
		word.setPoint(algorithm != null ? algorithm.getPoint() : 0D);
		word.setAlgorithmId(algorithm != null ? algorithm.getAlgorithmId()
				: null);
	}

	/**
	 * 重み付け検索
	 *
	 * @param word
	 * @return
	 */
	private static Algorithm getAlgorithmBySentence(Word word) {
		for (Algorithm algorithm : Rikyu.getWeightList()) {
			if ((algorithm.getWord().equals(word.getSurface()))) {
				return algorithm;
			}

		}
		return null;
	}

}
