package ru.stga.pft.soap;

/**
 * Created by admin on 03.08.2017.
 */
import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("90.143.17.96"); //myip.ru (внешний ip адрес компьютера)
        assertEquals(geoIP.getCountryCode(), "SWE");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("90.143.17.xx");
        assertEquals(geoIP.getCountryCode(), "SWE");
    }
}