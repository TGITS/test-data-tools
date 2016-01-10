package tgits.test.data.tools.generator

import tgits.test.data.tools.common.Randomizer

/**
 * Created by TGITS on 08/01/2016.
 */
@Singleton
class PhoneNumberGenerator {
    private Randomizer randomizer = Randomizer.instance

    String getFrenchPhoneNumber() {
        //Generate a phone number following this pattern "\\+\\d{11}|0\\d{9}"
        StringBuilder number = new StringBuilder()

        if (randomizer.nextBoolean()) {
            number << "+"
            number << randomizer.nextInt(10)
            number << randomizer.nextInt(10)
        } else {
            number << "0"
        }

        for (int i = 0; i < 9; i++) {
            number << randomizer.nextInt(10)
        }

        return number.toString()
    }

    List<String> getListOfFrenchPhoneNumbers(int maxSizeList) throws IllegalArgumentException {
        if (maxSizeList <= 0) {
            throw new IllegalArgumentException("you must provide a value greater than 0")
        }
        List<String> list = []

        int bound = randomizer.nextInt(maxSizeList)

        while (bound <= 1) {
            bound = randomizer.nextInt(maxSizeList)
        }

        (1..bound).each {
            list << getFrenchPhoneNumber()
        }
        return list;
    }

    List<String> getFixedSizeListOfFrenchPhoneNumbers(int sizeList) throws IllegalArgumentException {
        if (sizeList <= 0) {
            throw new IllegalArgumentException("you must provide a value greater than 0")
        }
        List<String> list = []

        (1..sizeList).each {
            list << getFrenchPhoneNumber()
        }
        return list;
    }
}
