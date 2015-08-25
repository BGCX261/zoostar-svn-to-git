package net.zoostar.roughcut.web.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import net.zoostar.roughcut.entity.model.AbstractAuditableEntity;
import net.zoostar.roughcut.module.service.CrudManager;

public abstract class AbstractCrudController<T extends AbstractAuditableEntity>
		extends MultiActionController {
	
	static final Logger log = LoggerFactory.getLogger(AbstractCrudController.class);
	
	@Autowired
	public void setCrudManager(CrudManager<T> crudManager) {
		if(crudManager == null)
			log.warn("crudManager is NULL!");
		else
			log.info("crudManager: [{}]", crudManager);

		this.crudManager = crudManager;
	}
	public CrudManager<T> getCrudManager() {
		return crudManager;
	}
	private CrudManager<T> crudManager;
	
	public void setNamedQueryForRetrieve(String namedQueryForRetrieve) {
		log.debug("Set namedQueryForRetrieve: [{}]", namedQueryForRetrieve);
		this.namedQueryForRetrieve = namedQueryForRetrieve;
	}
	protected String getNamedQueryForRetrieve() {
		return namedQueryForRetrieve;
	}
	private String namedQueryForRetrieve;
	

	protected String getRetrieveViewName() {
		return "retrieve";
	}
	
	public void setRetrieveParams(Map<String, Object> retrieveParams) {
		this.retrieveParams = retrieveParams;
	}
	protected Map<String, Object> getRetrieveParams() {
		return this.retrieveParams;
	}
	private Map<String, Object> retrieveParams = new HashMap<String, Object>(0);
	
	@RequestMapping(value="/retrieve")
	public String retrieve(Model model) {
		log.debug("Retrieving CRUD class [{}]...", getCrudClass());
		List<T> records = getCrudManager().findByNamedQueryForList(getCrudClass(), getNamedQueryForRetrieve(), getRetrieveParams());
		model.addAttribute("records", records);
		return getRetrieveViewName();
	}

	@RequestMapping(value="/create")
	public String create(Model model) {
		log.debug("Invoked [/create]");
		T t = getNewInstance();
		model.addAttribute("record", t);
		log.info("Returning view: [{}]", getCreateViewName());
		log.info("With record: [{}]", t);
		return getCreateViewName();
	}
	
	@RequestMapping(value="/created")
	public String created(@ModelAttribute T t,
			HttpServletRequest request, HttpServletResponse response) {
		log.debug("ModelAttribute: [{}]", t);
		log.debug("Invoked [/created]");
		String view = "redirect:retrieve";
		Principal user = request.getUserPrincipal();
		t.setCreatedBy(user.getName());
		t.setLastModifiedBy(user.getName());
		getCrudManager().create(t);
		log.debug("Returning view: [{}]", view);
		log.debug("With persisted object: [{}]", t);
		return view;
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public ModelAndView update(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView(getUpdateViewName());
		T t = getCrudManager().retrieve(getCrudClass(), id);
		modelAndView.addObject("record", t);
		return modelAndView;
	}
	
	@RequestMapping(value="/updated", method=RequestMethod.POST)
	public String updated(@ModelAttribute T record,
			HttpServletRequest request, HttpServletResponse response) {
		String view = "redirect:retrieve";
		log.debug("ModelAttribute: [{}]", record);
		
		if(record.getId() == null) {
			log.error("Existing Entity Id is NULL. Skipping Update.");
		} else {
			Principal user = request.getUserPrincipal();
			record.setLastModifiedBy(user.getName());
			record.setLastModifiedDate(new DateTime());
			getCrudManager().update(record);
		}
		return view;
	}

	protected String getCreateViewName() {
		return "create";
	}
	protected String getUpdateViewName() {
		return "update";
	}
	
	protected abstract Class<T> getCrudClass();
	protected abstract T getNewInstance();
}
