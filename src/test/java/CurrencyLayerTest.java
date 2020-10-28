import api.CurrencyLayer;
import enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


/**
 * CurrencyLayer Tester.
 *
 * @author Tim
 * @since Oct 28, 2020
 * @version 1.1
 */

public class CurrencyLayerTest {

    private static final String apiKey
            = "API_KEY_GOES_HERE";

    private static final Currency USD
            = Currency.USD;

    private static final Currency HKD
            = Currency.HKD;

    private static final Currency JPY
            = Currency.JPY;

    private static final double TEST_DELTA = 0.001;

    CurrencyLayer currencyLayer = new CurrencyLayer();

    @Test
    void shouldReturnRateEqualToExpected() throws IOException {
        double expected = 7.75;
        double actual = currencyLayer.rate(apiKey, USD, HKD);
        Assertions.assertEquals(expected, actual, TEST_DELTA);
    }

    @Test
    void shouldReturnRateNotEqualToExpected() throws IOException {
        double expected = 7.77;
        double actual = currencyLayer.rate(apiKey, USD, HKD);
        Assertions.assertNotEquals(expected, actual, TEST_DELTA);
    }


    /**
     *
     * Method: rate(String apiKey, Currency from, Currency to)
     *
     */
    @Test
    public void testRate() throws Exception {
//TODO: Test goes here...
    }


    /**
     *
     * Method: getQueryString(String apiKey, Currency from, Currency to)
     *
     */
    @Test
    public void testGetQueryString() throws Exception {
//TODO: Test goes here...
/*
try {
   Method method = CurrencyLayer.getClass().getMethod("getQueryString", String.class, Currency.class, Currency.class);
   method.setAccessible(true);
   method.invoke(<Object>, <Parameters>);
} catch(NoSuchMethodException e) {
} catch(IllegalAccessException e) {
} catch(InvocationTargetException e) {
}
*/
    }

}
