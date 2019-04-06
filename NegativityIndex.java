package Hackathon;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;


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
        ArrayList<String> str = new ArrayList<String>();
		try {
			 ResponseList<Status> statuses;
			 Scanner input = new Scanner(System.in);
			 System.out.println("Enter Twitter Screen Name: ");
			 String user = input.nextLine();
			 
			 twitter.createFriendship(user);
			 
			 if (args.length == 1)
			 {
				user = args[0];
					statuses = twitter.getHomeTimeline();
			 } 
			 else 
			 {
				 user = twitter.verifyCredentials().getScreenName();
				 statuses = twitter.getHomeTimeline();
			 }
			
			 String error = twitter.destroyFriendship(user);
			 
			 System.out.println("Showing @" + user + "'s user timeline.");
			 for (Status status : statuses) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
				str.add(status.getText());
				 //System.out.println(status.getStatus() + "*");
			 }
		} catch (TwitterException te) 
		{
				 

			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
		for(int i = 0; i < str.size(); i++)
		{
			System.out.println(str.get(i));
		}
    }
}
