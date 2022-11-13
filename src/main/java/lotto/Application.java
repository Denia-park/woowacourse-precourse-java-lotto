package lotto;

import lotto.domain.Lotto;

import java.util.List;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        final int PURCHASE_MONEY = askPurchaseMoney();

        if (PURCHASE_MONEY == -1) {
            return;
        }

        List<Lotto> lottos = Lotto.buyLottosByPurchaseMoney( PURCHASE_MONEY );
    }




    public static int askPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String userInput = readLine();

        try {
            validateUserInput(userInput);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

        return Integer.parseInt(userInput);
    }

    private static void validateUserInput(String userInput) {
        String verifiedUserInput = verifyUserInputIsDigit(userInput);
        verifyUserInputIsMultipleOf1000(verifiedUserInput);
    }

    private static void verifyUserInputIsMultipleOf1000(String verifiedUserInput) {
        try {
            if (Integer.parseInt(verifiedUserInput) % 1000 != 0) {
                throw new IllegalArgumentException("[ERROR] 1,000원 단위로 입력을 해주세요.");
            }
        }catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 2^31-1 까지의 범위만 입력이 가능합니다.");
        }
    }

    private static String verifyUserInputIsDigit(String userInput) {
        for (int idx = 0; idx < userInput.length(); idx++) {
            int eachNum = userInput.charAt(idx);

            if (!Character.isDigit(eachNum)) {
                throw new IllegalArgumentException("[ERROR] \"숫자\" 만 입력해주세요 (쉼표도 제외입니다.)");
            }
        }

        return userInput;
    }
}
