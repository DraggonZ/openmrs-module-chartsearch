/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.chartsearch.fragment.controller;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.openmrs.module.chartsearch.SearchProjectAccess;
import org.openmrs.ui.framework.fragment.FragmentModel;

public class CustomIndexingFragmentController {
	
	public void controller(FragmentModel model) {
	}
	
	@SuppressWarnings("rawtypes")
	public JSONObject indexDataForANewProject(HttpServletRequest request) {
		JSONObject json = new JSONObject();
		long savingTime;
		long indexingTime;
		SearchProjectAccess accessSearchProject = new SearchProjectAccess();
		long startSavingTime = new Date().getTime();// start search project saving time
		String projectUuid = accessSearchProject.createAndSaveANewSearchProject(request);
		long endSavingTime = new Date().getTime();// end saving time
		savingTime = endSavingTime - startSavingTime;
		boolean isNonOpenMRSDB = false;
		if (!StringUtils.isBlank(projectUuid)) {
			if (!StringUtils.isBlank(request.getParameter("databaseName"))) {
				isNonOpenMRSDB = true;
			}
			long startIndexingTime = new Date().getTime();// start search project indexing time
			Collection docs = accessSearchProject.indexSearchProjectData(projectUuid, isNonOpenMRSDB);
			long endIndexingTime = new Date().getTime();// end indexing time
			indexingTime = endIndexingTime - startIndexingTime;
			json.put("numberOfIndexedDocs", docs.size());
			json.put("savingTime", TimeUnit.MILLISECONDS.toSeconds(savingTime));
			json.put("indexingTime", TimeUnit.MILLISECONDS.toSeconds(indexingTime));
			json.put("projectUuid", projectUuid);//returned from the server
		}
		
		return json;
	}
	
}
