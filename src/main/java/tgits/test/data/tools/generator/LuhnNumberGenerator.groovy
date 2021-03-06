package tgits.test.data.tools.generator

import tgits.test.data.tools.common.LuhnNumberAlgorithm
import tgits.test.data.tools.common.Randomizer

/**
 * Created by TGITS on 09/01/2016.
 */
@Singleton
class LuhnNumberGenerator {

    private Randomizer randomizer = Randomizer.instance
    private LuhnNumberAlgorithm algorithm = LuhnNumberAlgorithm.instance

    String create(int numberOfDigits) {

        if (numberOfDigits < 1) {
            throw new IllegalArgumentException("The given parameter representing the number of digits of the number must be greater or equal to 1");
        }

        StringBuilder sb = new StringBuilder();

        (1..<numberOfDigits).each { sb.append(randomizer.nextInt(10)); }
        sb.append(0)

        long sum = algorithm.sumDigits(sb)
        long remainder = sum % 10

        if (remainder != 0) {
            sb.replace(sb.length() - 1, sb.length(), (10 - remainder).toString())
        }

        return sb.toString()
    }

    List<String> createList(int numberOfDigits, int size, boolean randomSize) {

        if (size < 1) {
            throw new IllegalArgumentException("The given parameter representing the size must be greater or equal to 1");
        }

        List<String> list = []

        int bound = size
        if (randomSize) {
            bound = randomizer.nextInt(size+1)

            while (bound < 1) {
                bound = randomizer.nextInt(size+1)
            }
        }

        (1..bound).each {
            list << create(numberOfDigits)
        }
        return list
    }

    String[] createArray(int numberOfDigits, int size) {
        return createList(numberOfDigits,size,false) as String[]
    }
}
