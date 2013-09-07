package rikyu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import rikyu.model.Algorithm;
import rikyu.model.Sentence;
import rikyu.model.Word;
import rikyu.utils.AnalyzeUtils;
import rikyu.utils.CSVReaderUtils;
import rikyu.utils.WeightingUtils;

public class Rikyu {

	private static ArrayList<Algorithm> weightList;

	public static void init() {
		if (weightList == null) {
			try {
				weightList = new ArrayList<Algorithm>();
				HashMap<Integer, ArrayList<String>> listJp = CSVReaderUtils
						.csvToListJp();

				if (listJp != null && listJp.size() > 0) {
					for (Entry<Integer, ArrayList<String>> entry : listJp
							.entrySet()) {
						Algorithm algorithm = new Algorithm();
						algorithm.setAlgorithmId(weightList.size()
								+ entry.getKey());
						algorithm.setWord(entry.getValue().get(0));
						algorithm.setPoint(Double.parseDouble(entry.getValue()
								.get(1)));
						weightList.add(algorithm);
					}
				}

				HashMap<Integer, ArrayList<String>> listWago = CSVReaderUtils
						.csvToListWago();

				if (listWago != null && listWago.size() > 0) {
					for (Entry<Integer, ArrayList<String>> entry : listWago
							.entrySet()) {
						Algorithm algorithm = new Algorithm();
						algorithm.setAlgorithmId(weightList.size()
								+ entry.getKey());
						algorithm.setWord(entry.getValue().get(1));
						algorithm.setPoint(Double.parseDouble(entry.getValue()
								.get(0)));
						System.out.println(entry.getValue().get(1));
						weightList.add(algorithm);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析処理
	 *
	 * @param string
	 * @return
	 */
	public static Sentence analyze(String string) {
		Sentence bean = new Sentence();
		bean.setSentence(string);
		ArrayList<Word> arrayList = AnalyzeUtils.analyze(string);
		Double totalPoint = 0D;
		for (Word word : arrayList) {
			WeightingUtils.calcWeight(word);
			totalPoint = totalPoint + word.getPoint();
		}
		bean.setWordList(arrayList);
		bean.setPoint(totalPoint);
		return bean;
	}

	public static ArrayList<Algorithm> getWeightList() {
		return weightList;
	}
	 public static void main(String[] args) {
		 init();
		 Sentence analyze = analyze("これは素晴らしい出来ですね");
		 System.out.println(analyze.getPoint().toString());
	 }
}
