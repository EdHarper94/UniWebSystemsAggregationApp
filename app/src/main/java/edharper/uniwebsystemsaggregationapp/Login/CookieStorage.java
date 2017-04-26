package edharper.uniwebsystemsaggregationapp.Login;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @file CookieStorage.java
 * @author Ed Harper
 * @date 22/03/2017
 *
 * A lightweight cookie store for JSoup requests
 */

public class CookieStorage {

    // Map <Host, < Key, Value> >
    private static Map<URI, Map<String, String>> hostCookieMap = new HashMap<>();
    private Map<String, String> cookies;

    public CookieStorage(){
    }

    /**
     * Stores Hashmaps for URI and cookie maps (Uri, (key, value))
     * @param cookies Map of cookies (key, value)
     * @param stringUrl url of cookie
     */
    public void storeCookies(Map<String,String> cookies, String stringUrl){
        try {
            URI uri = getUri(stringUrl);
            // If host is not there
            if(hostCookieMap.get(uri)== null) {
                for(Map.Entry e : cookies.entrySet()){
                    hostCookieMap.put(uri, cookies);
                }
            }else{
            }
        }catch (Exception e){

        }
    }

    /**
     * Gets cookies for provided host
     * @param stringUrl string host
     * @return
     */
    public Map<String, String> getCookiesMap(String stringUrl){
        try{
            URI uri = getUri(stringUrl);
            if (hostCookieMap.containsKey(uri)) {
                cookies = hostCookieMap.get(uri);
            }
        }catch(Exception e){

        }
        return cookies;
    }

    /**
     * Removes cookies from storage
     */
    public void removeCookies(){
        hostCookieMap.clear();
    }

    /**
     * Converts String url to URI for cookie storage
     * @param stringUrl the url in string format
     * @return URI host
     * @throws URISyntaxException
     * @throws MalformedURLException
     */
    public URI getUri(String stringUrl) throws URISyntaxException, MalformedURLException {

        URL url = new URL(stringUrl);
        URI uri = new URI(url.getProtocol(), url.getHost(), null);

        return uri;
    }
}
