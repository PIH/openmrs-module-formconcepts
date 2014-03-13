package org.opernmrs.module.formconcepts.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.OpenmrsObject;
import org.openmrs.api.context.Context;
import org.openmrs.module.formconcepts.api.FormconceptsService;
import org.openmrs.module.htmlformentry.HtmlForm;
import org.openmrs.module.htmlformentry.HtmlFormEntryService;
import org.openmrs.module.htmlformentry.HtmlFormExporter;


/** 
 * Form concepts utility methods
 */
public class FormConceptsUtil {
	/** Logger for this class and subclasses */
	protected final static Log log = LogFactory.getLog(FormConceptsUtil.class);
	
	/**
	 * Returns the Form concepts service from the Context
	 * 
	 * @return Concept Cleanup service
	 */
	public static FormconceptsService getService() {
		return Context.getService(FormconceptsService.class);
	}

	
	/**
	 * Return a list of all the concepts found in an html form
	 * 
	 * @return a List<Concept> object containing concepts
	 */
	public static List<Concept> getConceptsInForms(int formId) {
		List<Concept> formConcepts = new ArrayList<Concept>();
		List<HtmlForm> forms = new ArrayList<HtmlForm>();
		
		forms.add(Context.getService(HtmlFormEntryService.class).getHtmlForm(formId));
		
		Set<Integer> conceptIds= new TreeSet<Integer>();
		if (forms.size() > 0) {
			for (HtmlForm htmlForm : forms) {
				if (htmlForm != null) {
					HtmlForm form = new HtmlForm();
					form.setXmlData(htmlForm.getXmlData());
					HtmlFormExporter exporter = new HtmlFormExporter(form);
					HtmlForm formClone = exporter
							.export(true, true, true, true);
					Collection<OpenmrsObject> obj = formClone.getDependencies();

					if (obj != null && obj.size() > 0) {
						for (OpenmrsObject openmrsObject : obj) {
							if (openmrsObject != null
									&& openmrsObject instanceof Concept) {
								Concept c = (Concept) openmrsObject;
								if(c.isRetired() == false)
								conceptIds.add(c.getId());
							}

						}
					}
				}

			}
		}
		for (Integer integer : conceptIds) {
			formConcepts.add(Context.getConceptService().getConcept(integer));
		}
		return formConcepts;
	}
}
