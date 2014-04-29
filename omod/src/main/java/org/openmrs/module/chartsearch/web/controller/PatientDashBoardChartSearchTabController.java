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
package org.openmrs.module.chartsearch.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.solr.handler.dataimport.custom.IndexClearStrategies;
import org.openmrs.module.chartsearch.solr.ChartSearchIndexer;
import org.openmrs.web.controller.PortletController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */

public class PatientDashBoardChartSearchTabController extends PortletController {

	@Autowired
	private ChartSearchIndexer chartSearchIndexer;	

	@Override
	protected void populateModel(HttpServletRequest request,
			Map<String, Object> model) {
		
		  Integer personId = (Integer) model.get("personId");
		  chartSearchIndexer.clearIndex(IndexClearStrategies.IDS.toString(), personId+"", 0, 0);
		  chartSearchIndexer.indexPatientData(personId);	 
	}
}
