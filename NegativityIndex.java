package Hackathon;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;


public class NegativityIndex {
    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("")
        .setOAuthConsumerSecret("")
        .setOAuthAccessToken("")
        .setOAuthAccessTokenSecret("");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
		try {
			 ResponseList<User> statuses;
			 Scanner input = new Scanner(System.in);
			 System.out.println("Enter Twitter Screen Name: ");
			 String user = input.nextLine();
			  
			 if (args.length == 1)
			 {
				user = args[0];
				statuses = twitter.searchUsers(user, 1);
			 } 
			 else 
			 {
				 user = twitter.verifyCredentials().getScreenName();
				 statuses = twitter.searchUsers(user, 1);
			 }
			
			 System.out.println("Showing @" + user + "'s user timeline.");
			 for (User status : statuses) {
				// System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				 System.out.println(status.getStatus() + "*");
			 }
		} catch (TwitterException te) 
		{
				 
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
    }
}
