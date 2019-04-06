package Hackathon;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;

//TWITTER KEYS
//oauth.consumerKey=TZ2ojnd1SUeBZoPGTB48FsR0s
//oauth.consumerSecret=lLONlfKbPKcgOwH856b2dMZVpfvjUnuDmaZNXREbygakCsjhS8
//oauth.accessToken=1114320820581675008-1EEqAIU9sFdAVljlWv7sRzyOWZFlfc
//oauth.accessTokenSecret=twSM2V7avNgGv5zER3e4qGm5G2BDHJxx9ljqVSwzFQOb0


public class NegativityIndex {
    public static void main(String[] args) {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        .setOAuthConsumerKey("TZ2ojnd1SUeBZoPGTB48FsR0s")
        .setOAuthConsumerSecret("lLONlfKbPKcgOwH856b2dMZVpfvjUnuDmaZNXREbygakCsjhS8")
        .setOAuthAccessToken("1114320820581675008-1EEqAIU9sFdAVljlWv7sRzyOWZFlfc")
        .setOAuthAccessTokenSecret("twSM2V7avNgGv5zER3e4qGm5G2BDHJxx9ljqVSwzFQOb0");
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
