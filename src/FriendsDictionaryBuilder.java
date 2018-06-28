import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.User;


public class FriendsDictionaryBuilder 
{
	FriendsDictionaryBuilder(String AccessToken) throws IOException
	{
        FacebookClient facebookClient = null;
		
		// Get access token got from face book and past it here  
		facebookClient= new DefaultFacebookClient(AccessToken);
		
		//get friends list and sort them
		List<String> myFacebookFriendList= new ArrayList();
		
		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
		System.out.println("Count of my friends: " + myFriends.getData().size());
		for(User friend: myFriends.getData())
		  {
			if(!AlreadyExists(friend.getName()))
		    {
		      myFacebookFriendList.add(friend.getName() );
		    }
			else
			{
				// friend name already exists to dont do any thing
			}
		  }
		Collections.sort(myFacebookFriendList);
		  //  sorting the list
		for(String temp:myFacebookFriendList )
		  {
			   
				System.out.println(temp); 
				FileWriter fw = new FileWriter("FreindsNames.txt",true);
    			BufferedWriter bw = new BufferedWriter(fw);
			    bw.write(temp+"\n");
			    bw.close();
	      }
	
	}
	
	boolean AlreadyExists(String name) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("FreindsNames.txt"));
		String line;
		while ((line = br.readLine()) != null)
		{
		   if(line.equals(name))
		   { br.close();
			   return true;
		   }
		}
		br.close();
		return false;
	}
}
