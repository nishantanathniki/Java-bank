package BasicWorks;
import BasicWorks.models.model_user;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class ManageBalace
{
	public static void addMoney(model_user c_user,int amount,int cvv) throws Exception{
		if (c_user.getCvv() == cvv){
			c_user.setBal(c_user.getBal()+amount);
			dump(c_user);
			dump(c_user.getUid(),c_user);
			System.out.println("Suceess");
		}else{
			System.out.println("Cvv Not Matched");
			System.out.println("Sorrry We Can Proceed");
		}
	}
	private static void dump(int uid,model_user user) throws FileNotFoundException,IOException{
		File d = new File(System.getProperty("user.dir")+"/.bankCrediential/user/"+uid+".ser");
		if (d.exists()){
			FileOutputStream a = new FileOutputStream(d);
			ObjectOutputStream aa = new ObjectOutputStream(a);
			aa.writeObject(user);
			a.close();
			aa.close();
			
		}
	}
	//Function Overloading
	private static void dump(model_user user) throws FileNotFoundException,IOException{
		File d = new File(System.getProperty("user.dir")+"/.bankCrediential/c_user.ser");
		if (d.exists()){
			FileOutputStream a = new FileOutputStream(d);
			ObjectOutputStream aa = new ObjectOutputStream(a);
			aa.writeObject(user);
			a.close();
			aa.close();

		}
	}
	public static void depositeMoney(model_user c_user,int amount,int cvv) throws Exception{
		if (c_user.getCvv() == cvv){
			if (c_user.getBal() >= amount){
				c_user.setBal(c_user.getBal()-amount);
			}else{
				System.out.println("\u001B[32m");
				System.out.println("Not enough money to deposite");
				System.out.println("\u001B[39m");
				return;
			}
			dump(c_user);
			dump(c_user.getUid(),c_user);
			System.out.println("Suceess");
		}else{
			System.out.println("Cvv Not Matched");
			System.out.println("Sorrry We Can't Proceed");
		}
	}
	public static void sendByUid(int uid,int amount,int cvv,model_user user){
		if (uid == user.getUid()){
			System.out.println("\u001B[32m");
			System.out.println("Cannot Send Money To Your Self By This Method");
			System.out.println("\u001B[39m");
			return;
		}
		if (user.getCvv() == cvv){
			if (user.getBal() >= amount){
				try {
					model_user s = loadByUid(uid);
					if (s != null){
						user.setBal(user.getBal() - amount);
						s.setBal(s.getBal()+amount);
						dump(user);
						dump(user.getUid(),user);
						dump(s.getUid(),s);
						System.out.println("\u001B[32m");
						System.out.println("Sucessfull");
						System.out.println("\u001B[39m");
						return;
					}
					throw new Exception();
				} catch (Exception e) {
					System.out.println("\u001B[32m");
					//ystem.out.println(e);
					System.out.println("Cannot Transfer Please Try Agagin");
					System.out.println("\u001B[39m");
				} 
				
			}else{
				System.out.println("\u001B[32m");
				System.out.println("Not enough money to deposite");
				System.out.println("\u001B[39m");
			}
		}else{
			System.out.println("Cvv Not Matched");
			System.out.println("Sorrry We Can Proceed");
		}
	}
	public static model_user loadByUid(int uid) throws FileNotFoundException,IOException,ClassNotFoundException{
		File f = new File(System.getProperty("user.dir")+"/.bankCrediential/user/"+uid+".ser");
		if (f.exists()){
			FileInputStream s = new FileInputStream(f);
			ObjectInputStream ss = new ObjectInputStream(s);
			model_user u =  (model_user)ss.readObject();
			s.close();
			ss.close();
			return u;
		}
		return null;
	}
}
