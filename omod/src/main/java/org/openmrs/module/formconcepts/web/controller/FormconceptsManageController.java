/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.formconcepts.web.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.opernmrs.module.formconcepts.util.FormConceptsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The main controller.
 */
@Controller
public class FormconceptsManageController {

	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping(value = "/module/formconcepts/manage", method = RequestMethod.GET)
	public void manage(ModelMap model) {
		model.addAttribute("user", Context.getAuthenticatedUser());
	}

	@RequestMapping(value = "/module/formconcepts/conceptsList", method = RequestMethod.POST)
	public void getForm(
			@RequestParam(required = true, value = "formId") Integer formId,
			ModelMap model) {
		int id = Integer.valueOf(formId);
		List<Concept> formConcepts = FormConceptsUtil.getConceptsInForms(id);
		model.addAttribute("formConcepts", formConcepts);
	}
}
