package pt.uc.dei.aor.paj;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named
@SessionScoped
public class PickListView implements Serializable {
	private static final long serialVersionUID = -1183677189929476404L;
	
	private List<String> hist;
	private List<String> temp;

	
	@PostConstruct
	public void init() {
		hist = new ArrayList<String>();
		temp = new ArrayList<String>();
		hist.add(" "); 
		temp.add(" "); 
		
	}

	public void init(String exp){
		hist.add(exp);
		//historico.setSource(hist);
	}
	
	public void addTime(String time){
		temp.add(time);
	}
	
	public List<String> getHist() {
		return hist;
	}
	
	public List<String> getTime() {
		return temp;
	}

	public void setHist(List<String> hist) {
		this.hist = hist;
	} 
}