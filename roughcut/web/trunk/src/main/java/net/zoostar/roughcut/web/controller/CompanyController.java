package net.zoostar.roughcut.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.zoostar.roughcut.entity.model.Company;

@Controller
@RequestMapping(value="/company")
public class CompanyController extends AbstractCrudController<Company> {

	static final Logger log = LoggerFactory.getLogger(CompanyController.class);
	
	static final String VIEW_PREPEND = "company.";
	
	@Override
	protected Class<Company> getCrudClass() {
		return Company.class;
	}

	@Override
	public String getNamedQueryForRetrieve() {
		return "company.retrieveAllByName";
	}
//	
//	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {
//		ModelAndView modelAndView = new ModelAndView("company.create");
//		modelAndView.addObject("company", new Company());
//		log.info("Returning View: [{}]", modelAndView.getViewName());
//		return modelAndView;
//	}
	
//	@RequestMapping(value="/company/added", method=RequestMethod.POST)
//	public ModelAndView companyCreated(@ModelAttribute Company company) {
//		return null;
//	}

//	@Override
//	public String retrieve(Model model) {
//		log.debug("Retrieving CRUD class [{}]...", getCrudClass());
//		//ModelAndView modelAndView = new ModelAndView(getRetrieveViewName());
//		List<Company> records = new ArrayList<Company>(); //getCrudManager().findByNamedQueryForList(getCrudClass(), getNamedQueryForRetrieve(), getRetrieveParams());
//		Company company = new Company();
//		company.setName("Test company");
//		records.add(company);
//		model.addAttribute("records", records);
//		return getRetrieveViewName();
//	}
	
	@Override
	protected String getRetrieveViewName() {
		String view = VIEW_PREPEND + super.getRetrieveViewName();
		log.debug("Returning view: [{}]", view);
		return view;
	}

	@Override
	protected String getCreateViewName() {
		return VIEW_PREPEND + super.getCreateViewName();
	}

	@Override
	protected String getUpdateViewName() {
		return VIEW_PREPEND + super.getUpdateViewName();
	}

	@Override
	protected Company getNewInstance() {
		return new Company();
	}
}
