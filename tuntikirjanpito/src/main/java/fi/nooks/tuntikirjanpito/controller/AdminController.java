package fi.nooks.tuntikirjanpito.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.nooks.tuntikirjanpito.bean.WorkHours;
import fi.nooks.tuntikirjanpito.dao.WorkHoursDao;

@Controller
@RequestMapping(value="/secure/admin")
public class AdminController {

	@Inject
	private WorkHoursDao dao;
	
	public WorkHoursDao getDao() {
		return dao;
	}

	public void setDao(WorkHoursDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value = "/adminpage", method = RequestMethod.GET)
	public String show(Model model) {
		List<WorkHours> allWorkHours = dao.listAllWorkHours();
		List<WorkHours> peopleHours = dao.listAllPersonHours();
		model.addAttribute("allWorkHours", allWorkHours);
		model.addAttribute("peopleHours", peopleHours);
		return "secure/admin/adminpage";
	}
}
