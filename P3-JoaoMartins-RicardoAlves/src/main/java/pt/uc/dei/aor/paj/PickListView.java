package pt.uc.dei.aor.paj;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

@Named
@SessionScoped
public class PickListView implements Serializable {
	private static final long serialVersionUID = -1183677189929476404L;
	
	//private DualListModel<String> historico;
	private List<String> hist;
	//private List<String> selcecao;
	

	
	@PostConstruct
	public void init() {
		hist = new ArrayList<String>();
	//	selcecao = new ArrayList<String>();
		hist.add(" ");   
	//	historico = new DualListModel<String>(hist, selcecao);
	
	}

	public void init(String exp){
		hist.add(exp);
		//historico.setSource(hist);
	}
/*
	public DualListModel<String> getHistorico() {
		return historico;
	}

	public void setHistorico(DualListModel<String> historico) {
		this.historico=historico;
	}
*/
//	public void onSelect(SelectEvent event) {
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				"Item Selected", event.getObject().toString()));
//		
//	}
//
//	public void onUnselect(UnselectEvent event) {
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				"Item Unselected", event.getObject().toString()));
//
//	}
//
//	public void onReorder() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
//				"List Reordered", null));
//	}

	public List<String> getHist() {
		return hist;
	}

	public void setHist(List<String> hist) {
		this.hist = hist;
	} 
}