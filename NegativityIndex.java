package Hackathon;
import java.util.*;
import twitter4j.*;
import twitter4j.conf.*;

public class NegativityIndex {
    public static void main(String[] args) {

    	//Constructs twitter4j configuration with desired settings 
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
        
        //User authentication keys and tokens
        .setOAuthConsumerKey("TZ2ojnd1SUeBZoPGTB48FsR0s")
        .setOAuthConsumerSecret("lLONlfKbPKcgOwH856b2dMZVpfvjUnuDmaZNXREbygakCsjhS8")
        .setOAuthAccessToken("1114320820581675008-1EEqAIU9sFdAVljlWv7sRzyOWZFlfc")
        .setOAuthAccessTokenSecret("twSM2V7avNgGv5zER3e4qGm5G2BDHJxx9ljqVSwzFQOb0");
        
        //Creates twitter4j objects
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        
        //Normal variables
        ArrayList<String> str = new ArrayList<String>();
        int numTweets = 100;

		try {
			//List of TwitterResponse
			 ResponseList<Status> statuses;
			 
			 //Scan system input into 'user' string
			 Scanner input = new Scanner(System.in);
			 System.out.println("Enter Twitter Screen Name: ");
			 String user = input.nextLine();
			 String tempUser = user;
			 input.close();
			 
			 //Adds 'tempUser' as a friend to scrape tweets and retweets from the authentication user's timeline
			 twitter.createFriendship(tempUser);
			 
			 //Number of tweets to scrape from authentication user's timeline
			 Paging page = new Paging(1, numTweets);
			 
			 if (args.length == 1)
			 {
				user = args[0];
				
				//Passes tweets and retweets from authentication user's timeline to ResponseList
				statuses = twitter.getUserTimeline(tempUser, page);
			 } 
			 else 
			 {
				 user = twitter.verifyCredentials().getScreenName();
				 //Passes tweets and retweets from authentication user's timeline to ResponseList
				 statuses = twitter.getUserTimeline(tempUser, page);
			 }
			
			 //Removes 'tempUser' as a friend to reset timeline for next search
			 twitter.destroyFriendship(tempUser);
			 
			 System.out.println("Showing @" + user + "'s user timeline.");
			 
			 //Adds 'tempUser's tweets and retweets to ArrayList 'str'
			 for (Status status : statuses) {
				str.add(status.getText());
			 }
		} catch (TwitterException te)			//ERROR: Could not get timeline of 'tempUser'
		{
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
			
		//Cleans ArrayList 'str'
		for(int i = 0; i < str.size(); i++)
		{
			String line = str.get(i);
			Scanner scan = new Scanner(line);
			if(scan.next().equals("RT"))
			{
				line = line.substring(line.indexOf(":"));
				line = line.substring(2);
			}
			scan.close();
			
			//Removes links from ArrayList 'str'
			if(line.contains("https://"))
			{
				int index = line.indexOf("https://");
				line = line.substring(0, index);
				//FIX ME: Remove links that are in the middle of a line
				//newStr = line.replaceAll("https://\\p{Space}+", "");
			}
			if(line.contains("//"))			//Assumes "//" is involved in a link
			{
				int index = line.indexOf("//");
				line = line.substring(0, index);
			}
			
			//Gets rid of unnecessary characters ("  ", "_", "|", "\", "\n")
			if(line.contains("  "))
			{
				int index = line.indexOf("  ");
				line = line.substring(0, index);
			}
			
			line = line.replaceAll("_", "");
			line = line.replaceAll("|", "");
			line = line.replaceAll("\\\\", "");
			line = line.replaceAll("\n", "");
			
			//Removes blank lines and prints non-blank lines
			if(line.equals(" ") || line.equals(""))
			{
				str.remove(i);
			}
			else {
			str.set(i, line);
			System.out.println(str.get(i));
			}
		}
    }
}
