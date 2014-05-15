package road.movementmapper.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.http.HttpEntity;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * This sends a request to JobSubmitterServlet so that we can start Batch 
 */
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
                    System.out.println("Sending batch request [3]");

                    DefaultHttpClient client = new DefaultHttpClient();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy|HH:mm:ss");
                    String formattedDate = sdf.format(basedate);
                    String urlAndParams = URL + "?inputfile=" + file.getAbsolutePath() + "&basedate=" +formattedDate;
                    HttpPost post = new HttpPost(urlAndParams);

                    // Request parameters and other properties.
                    List<NameValuePair> params = new ArrayList<>(2);
                    params.add(new BasicNameValuePair("inputfile", file.getName()));
                    params.add(new BasicNameValuePair("basedate", formattedDate));
                    post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                    
                    HttpResponse response = client.execute(post);
                    
                    System.out.println("\nSending 'POST' request to URL : " + urlAndParams);
                    System.out.println("Post parameters : " + post.getEntity());
                    System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
