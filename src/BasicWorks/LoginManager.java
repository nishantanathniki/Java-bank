package BasicWorks;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import BasicWorks.models.model_user;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class LoginManager {
	public static model_user checklogin() throws FileNotFoundException,Exception,ClassNotFoundException{
		File exp = new File(System.getProperty("user.dir")+"/.bankCrediential/");
		if (!exp.exists()){
			exp.mkdir();
		}
		File path = new File(System.getProperty("user.dir")+"/.bankCrediential/c_user.ser");
		if (!path.exists()){
			path.createNewFile();
		}
		return read(path);
	}
	private static model_user read(File path)throws FileNotFoundException,IOException,ClassNotFoundException{
		FileInputStream stream = new FileInputStream(path);
		ObjectInputStream output = new ObjectInputStream(stream);
		model_user user = (model_user) output.readObject();
		return user;
	}
}
