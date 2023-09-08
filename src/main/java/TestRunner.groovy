import net.grinder.script.GTest
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import org.apache.hc.client5.http.cookie.Cookie
import org.junit.Test
import org.junit.runner.RunWith
import org.ngrinder.http.HTTPRequest
import org.ngrinder.http.HTTPRequestControl
import util.JsonUtil

import static net.grinder.script.Grinder.grinder

/**
 * A simple example using the HTTP plugin that shows the retrieval of a single page via HTTP.
 *
 * This script is automatically generated by ngrinder.
 *
 * @author admin
 */
@RunWith(GrinderRunner)
class TestRunner {

    static GTest test
    static HTTPRequest request
    static Map<String, String> headers = [:]
    static Map<String, String> params = [:]
    static List<Cookie> cookies = []

    @BeforeProcess
    static void beforeProcess() {
        HTTPRequestControl.setConnectionTimeout(300000)
        test = new GTest(1, "18.138.2.73")
        request = new HTTPRequest()
        // Set header data --unnecessary
        // Set request body
        String data = """
            {
                "ExchangeID": "DeepCoin",
                "MemberID": "9115761",
                "InstrumentID": "BTCUSDT",
                "OrderPriceType": "0",
                "Direction": "0",
                "OffsetFlag": "0",
                "UserID": "9115761",
                "Price": "0",
                "AccountID": "9115761",
                "Volume": 1,
                "IsCrossMargin": 1,
                "TradeUnitID": "9115761",
                "BusinessType": "V",
                "randomstr": "NJKXQX",
                "timestamp": 1680588489182,
                "convertPOST": 1,
                "sign": "78a754ad14fe9429f35996247ecc4a7a"
            }
            """
        params = JsonUtil.parseMap(data, String.class, String.class)
        params['Price'] = "34000"
        params['Direction'] = "1"
        params['Volume'] = "20"
        grinder.logger.info("before process.")
    }

    @BeforeThread
    void beforeThread() {
        test.record(this, "test")
        grinder.statistics.delayReports = true
        grinder.logger.info("before thread.")
    }

    @Test
    void test() {
        grinder.logger.info(params)
    }
}