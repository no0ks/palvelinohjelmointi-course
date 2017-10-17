package fi.nooks.tuntikirjanpito.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import fi.nooks.tuntikirjanpito.bean.WorkHours;
import fi.nooks.tuntikirjanpito.dao.WorkHoursDao;

@Controller
@RequestMapping(value = "/secure")
public class SecureController {

	@Inject
	private WorkHoursDao dao;
	
	public WorkHoursDao getDao() {
		return dao;
	}

	public void setDao(WorkHoursDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String listAllHours(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
		List<WorkHours> allWorkHours = dao.findWorkHours(username);
		WorkHours personHours = dao.listPersonHours(username);
		model.addAttribute("allWorkHours", allWorkHours);
		model.addAttribute("workHours", new WorkHours());
		model.addAttribute("personHours", personHours);
		return "secure/main";
	}
	
	@RequestMapping(value = "/main", method=RequestMethod.POST)
	public String newHours(@ModelAttribute(value="workHours") @Valid WorkHours workHours, BindingResult bindingResult, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String username = auth.getName();
	    workHours.setUsername(username);
		if(bindingResult.hasErrors()) {
			List<WorkHours> allWorkHours = dao.findWorkHours(username);
			WorkHours personHours = dao.listPersonHours(username);
			model.addAttribute("allWorkHours", allWorkHours);
			model.addAttribute("workHours", workHours);
			model.addAttribute("personHours", personHours);
		} else {
		    try {
		    	dao.findWorkHours(workHours);
		    	bindingResult.rejectValue("workDate", "existing.workDate");
		    	List<WorkHours> allWorkHours = dao.findWorkHours(username);
		    	WorkHours personHours = dao.listPersonHours(username);
				model.addAttribute("allWorkHours", allWorkHours);
				model.addAttribute("workHours", workHours);
				model.addAttribute("personHours", personHours);
			} catch (Exception e) {
		        dao.addWorkHours(workHours);
				List<WorkHours> allWorkHours = dao.findWorkHours(username);
				WorkHours personHours = dao.listPersonHours(username);
				model.addAttribute("allWorkHours", allWorkHours);
				model.addAttribute("workHours", new WorkHours());
				model.addAttribute("personHours", personHours);
			}
		}
		return "secure/main";
	}

}