package fi.nooks.painonhallinta.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.nooks.painonhallinta.bean.Weight;
import fi.nooks.painonhallinta.dao.WeightDao;

@Controller
@RequestMapping(value="/")

public class WeightController {

	@Inject
	private WeightDao dao;
	
	public WeightDao getDao() {
		return dao;
	}

	public void setDao(WeightDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getList(Model model) {
		List<Weight> entries = dao.getAllEntries();
		model.addAttribute("entries", entries);
		model.addAttribute("weight", new Weight());
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String create(@ModelAttribute(value="weight") @Valid Weight weight, BindingResult bindingResult, Model model) {
		String message = "";
		if (bindingResult.hasErrors()) {
			message = "Syötä paino muodossa 0.0 (max 999.9)";
        } else {
        	dao.addEntry(weight);
        }
		List<Weight> entries = dao.getAllEntries();
		model.addAttribute("entries", entries);
		model.addAttribute("message", message);
		model.addAttribute("weight", new Weight());
		return "index";
	}
}
