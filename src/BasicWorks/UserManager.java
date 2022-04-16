package BasicWorks;
import BasicWorks.models.model_user;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class UserManager {
	public static boolean manageUser(model_user user) throws IOException {
		File f = new File(System.getProperty("user.dir") + "/.bankCrediential/user/");
		if (!f.exists()) {
			f.mkdir();
		}
		File f2 = new File(System.getProperty("user.dir") + "/.bankCrediential/user/" + user.getUid() + ".ser");
		if (!f2.exists()) {
			f2.createNewFile();
		} else {
			System.out.println();
			System.out.println("\u001B[32maccount already exits");
			System.out.print("\u001B[39m");
			return false;
		}
		FileOutputStream st = new FileOutputStream(f2, true);
		ObjectOutputStream stream = new ObjectOutputStream(st);
		stream.writeObject(user);
		stream.close();
		st.close();
		return true;
	}
	public static void  listUser() {
		
		File ob = new File(System.getProperty("user.dir") + "/.bankCrediential/user/");
		if (ob.exists()) {
			File[] o= ob.listFiles();
			int index = 0;
			if (ob.exists()) {
				for (File f:o) {
					System.out.println(index +"->" + f.getName().substring(0,f.getName().lastIndexOf(".")));
					index++;
				}
			}
			index=0;
		}
		
	}
}
