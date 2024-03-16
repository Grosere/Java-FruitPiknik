import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class WordAnalyzer {

    private HashMap<String, String> fruitsAndVegetables = new HashMap<>();

    public WordAnalyzer() {
        fruitsAndVegetables.put("apple", "fruit");
        fruitsAndVegetables.put("banana", "fruit");
        fruitsAndVegetables.put("orange", "fruit");
        fruitsAndVegetables.put("carrot", "vegetable");
        fruitsAndVegetables.put("broccoli", "vegetable");
    }

    public void analyzeWords(File file) {
        HashMap<String, Integer> wordFrequency = new HashMap<>();
        int fruitCount = 0;
        int vegetableCount = 0;
        int otherCount = 0;
        String longestWord = "";
        String shortestWord = null;

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();

                if (fruitsAndVegetables.containsKey(word)) {
                    if (fruitsAndVegetables.get(word).equals("fruit")) {
                        fruitCount++;
                    } else {
                        vegetableCount++;
                    }
                } else {
                    otherCount++;
                }

                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }

                if (shortestWord == null || word.length() < shortestWord.length()) {
                    shortestWord = word;
                }

                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден");
            e.printStackTrace();
        }

        System.out.println("Количество слов фруктов: " + fruitCount);
        System.out.println("Количество слов овощей: " + vegetableCount);
        System.out.println("Количество слов, которые не являются овощами и фруктами: " + otherCount);
        System.out.println("Самое длинное слово: " + longestWord);
        System.out.println("Самое короткое слово: " + shortestWord);

        int maxFruitFrequency = 0;
        int maxVegetableFrequency = 0;
        String mostPopularFruit = "";
        String mostPopularVegetable = "";

        for (String word : wordFrequency.keySet()) {
            if (fruitsAndVegetables.containsKey(word)) {
                int frequency = wordFrequency.get(word);
                if (fruitsAndVegetables.get(word).equals("fruit")) {
                    if (frequency > maxFruitFrequency) {
                        maxFruitFrequency = frequency;
                        mostPopularFruit = word;
                    }
                } else {
                    if (frequency > maxVegetableFrequency) {
                        maxVegetableFrequency = frequency;
                        mostPopularVegetable = word;
                    }
                }
            }
        }

        System.out.println("Самый популярный фрукт: " + mostPopularFruit);
        System.out.println("Самый популярный овощ: " + mostPopularVegetable);
    }

    public static void main(String[] args) {
        WordAnalyzer wordAnalyzer = new WordAnalyzer();
        File file = new File("input.txt");
        wordAnalyzer.analyzeWords(file);
    }
}
