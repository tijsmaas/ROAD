package road.movementmapper.util;

import java.io.File;
import java.util.Calendar;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClient {

    private final String URL = "http://localhost:8080/MovementMapper";

    // HTTP POST request
    public void sendPost(final File file, final Calendar basedate) throws Exception {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    // prevent calling too early
                    Thread.sleep(1000);

                    DefaultHttpClient client = new DefaultHttpClient();
                    String urlAndParams = URL + "?inputfile=" + file.getAbsolutePath() + "&basedate=" +basedate.toString();
                    HttpPost post = new HttpPost(urlAndParams);
                    HttpResponse response = client.execute(post);
                    System.out.println("\nSending 'POST' request to URL : " + urlAndParams);
                    System.out.println("Post parameters : " + post.getEntity());
                    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

//                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

//                    StringBuffer result = new StringBuffer();
//                    String line = "";
//                    while ((line = rd.readLine()) != null) {
//                        result.append(line);
//                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
