package BasicWorks.models;
import java.io.Serializable;

public class model_user implements Serializable{
	private int uid;
	private int cvv;
	private int bal;

	public void setBal(int bal) {
		this.bal = bal;
	}

	public int getBal() {
		return bal;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public int getCvv() {
		return cvv;
	}




	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}}
