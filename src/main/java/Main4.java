import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author huoershuai
 * Created on 2020-07-30
 */
public class Main4 {
    private static final int MAX_TWO_USER_ID_TAIL = 100;

    public static void main(String[] args) throws ParseException {
        int twoUserIdTail = 88;
        List<String> a = getTableByUserIdTwoTail(twoUserIdTail);
        System.out.println(Arrays.toString(a.toArray()));
    }

    public static List<String> getTableByUserIdTwoTail(int twoUserIdTail) {
        List<String> result = new ArrayList<>();
        if (twoUserIdTail >= 0 && twoUserIdTail < MAX_TWO_USER_ID_TAIL) {
            for (int i = 0; i < 10; i++) {
                result.add(getTable(twoUserIdTail + MAX_TWO_USER_ID_TAIL * i));
            }
        }
        return result;
    }

    public static String getTable(long userId) {
        long mod = userId % 1000 + 1;
        return String.format("tt" + "_%d", mod);
    }

}
