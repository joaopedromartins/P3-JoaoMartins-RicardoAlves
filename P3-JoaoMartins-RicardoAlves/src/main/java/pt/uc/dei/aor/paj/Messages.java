package pt.uc.dei.aor.paj;

import java.io.Serializable;
import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Messages implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String>messages;
	
	public Messages() {
		messages = new ArrayList<>();
	}

	public ArrayList<String> getMessages() {
		synchronized(messages){
			return messages;
		}
	}

	public void addMessages(String m) {
		synchronized(messages){
			messages.add(m);
		}
	}
}
