/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Q2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author Abdallah_Ahmed
 */
public class Main {

    public static void main(String[] args){

        Path file = Paths.get("./src/Q2/test.txt");
        try {
            Stream<String> lines = Files.lines(file);
            Map<Character, Integer> count =
                    lines
                    .flatMap(line -> IntStream.range(0, line.length()).mapToObj(line::charAt))
                    .filter(Character::isLetter)
                    .map(Character::toLowerCase)
                    .collect(TreeMap::new, (m, c) -> m.merge(c, 1, Integer::sum), Map::putAll);

            count.forEach((letter, num) -> System.out.println(letter + " : " + num));

        } catch (IOException e) {
            System.out.println("Failed to read file.");
            e.printStackTrace(System.out);
        }

    }
}
