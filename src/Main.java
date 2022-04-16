import java.util.*;
import BasicWorks.LoginManager;
import BasicWorks.models.model_user;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import javax.crypto.KeyAgreement;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import BasicWorks.UserManager;
import java.io.File;
import BasicWorks.ManageBalace;
import java.io.FileInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class Main {
	public static void login() throws Exception,FileNotFoundException {
		System.out.println("\u001B[32m*******Your Not Logedin*********");
		System.out.print("\u001B[39m");
		Scanner scan = new Scanner(System.in);
		System.out.print("Your Bank Uid : ");
		int uid = scan.nextInt();
		System.out.print("Your Bank Cvv : ");
		int cvv = scan.nextInt();
		if (Integer.toString(cvv).length() == 3) {
			File f =new File(System.getProperty("user.dir") + "/.bankCrediential/user/" + uid + ".ser");
			if (f.exists()) {
				FileInputStream stream = new FileInputStream(f);
				ObjectInputStream s = new ObjectInputStream(stream);
				model_user u = (model_user)s.readObject();
				if (cvv == u.getCvv()) {
					FileOutputStream d = new FileOutputStream(System.getProperty("user.dir") + "/.bankCrediential/c_user.ser");
					ObjectOutputStream ss = new ObjectOutputStream(d);
					ss.writeObject(u);
					ss.close();
					d.close();
				}else{
					lineBreak();
					System.out.println("\u001B[32mLogin Failed cvv not matched");
					System.out.print("\u001B[39m");
					lineBreak();
				}
			} else {
				FileOutputStream d = new FileOutputStream(System.getProperty("user.dir") + "/.bankCrediential/c_user.ser");
				model_user j = new model_user();
				j.setBal(0);
				j.setCvv(cvv);
				j.setUid(uid);
				ObjectOutputStream ss = new ObjectOutputStream(d);
				ss.writeObject(j);
				ss.close();
				d.close();
				boolean bk = UserManager.manageUser(j);
				if (!bk) {
					lineBreak();
					lineBreak();
					login();
					lineBreak();
				}
			}
			
			main(new String[]{});
		} else {
			lineBreak();
			System.out.println("Cvv must be 3 number");
			lineBreak();
			login();
		}

	}
	public static void main(String[] args) {
		model_user u = null;
		try {
			u = LoginManager.checklogin();
			if (u != null) {
				System.out.println("\u001B[32m********Login Sucessfull**********");
				System.out.print("\u001B[39m");
				Scanner scan = new Scanner(System.in);
				while (true) {
					System.out.println("1 for check your balance\n2 for deposit\n3 for add money\n4 for transfer to someoneelse\n5 for cureent account details\n6 for sighout\n7 for all user's uid list\n8 for exit");
					int  st = scan.nextInt();
					if (st == 1) {
						lineBreak();
						System.out.println("Your Balance :" + u.getBal());
						lineBreak();
					} else if (st == 2) {
						lineBreak();
						System.out.print("Enter Your Amount : ");
						int am = scan.nextInt();
						System.out.print("Enter Your Cvv : ");
						int cv = scan.nextInt();
						if (am <= 0) {
							main(new String[] {});
						}
						System.out.println("\u001B[32m");

						ManageBalace.depositeMoney(u, am, cv);
						lineBreak();
						System.out.print("\u001B[39m");

					} else if (st == 3) {
						lineBreak();
						System.out.print("Enter Your Amount : ");
						int am = scan.nextInt();
						System.out.print("Enter Your Cvv : ");
						int cv = scan.nextInt();
						if (am <= 0) {
							main(new String[] {});
						}
						System.out.println("\u001B[32m");

						ManageBalace.addMoney(u, am, cv);
						lineBreak();
						System.out.print("\u001B[39m");
					} else if (st == 4) {
						lineBreak();
						System.out.println("Enter Uid Where Your Want to send money : ");
						int useruid = scan.nextInt();
						System.out.print("Enter Your Amount : ");
						int am = scan.nextInt();
						System.out.print("Enter Your Cvv : ");
						int cv = scan.nextInt();
						if (am <= 0) {
							main(new String[] {});
						}
						System.out.println("\u001B[32m");

						ManageBalace.sendByUid(useruid,am,cv,u);
						lineBreak();
						System.out.print("\u001B[39m");
					} else if (st == 5) {

						lineBreak();
						System.out.println("Uid : " + u.getUid());
						System.out.println("Cvv : " + u.getCvv());
						lineBreak();
					} else if (st == 6) {
						sighout();
						u = null;
					}else if(st == 7){
						lineBreak();
						UserManager.listUser();
						lineBreak();
					}else if (st == 8) {
						lineBreak();
						System.out.println("\u001B[32mProgram Exited");
						System.out.print("\u001B[39m");
						lineBreak();
						break;
					} else {
						lineBreak();
						System.out.println("\u001B[32mwtf not define");
						System.out.print("\u001B[39m");
						lineBreak();
					}
				}
				System.exit(1);
			}
		} catch (Exception e) {
			if (u == null) {
				try {
					login();
				} catch (Exception ee) {
					System.out.println(ee);
				}
			} else {
				main(new String[]{});
			}
		}
    }
	public static void lineBreak() {
		System.out.println();
	}
	public static void sighout() {
		File o = new File(System.getProperty("user.dir") + "/.bankCrediential/c_user.ser");
		if (o.exists()) {
			o.delete();
		}
		lineBreak();
		System.out.println("\u001B[32mSighout Sucessfull");
		System.out.print("\u001B[39m");
		lineBreak();
		try {
			login();
		} catch (Exception e) {}
	}

}
