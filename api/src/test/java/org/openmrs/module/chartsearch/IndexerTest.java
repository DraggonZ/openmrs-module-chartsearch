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
package org.openmrs.module.chartsearch;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.module.chartsearch.server.PatientInfo;
import org.openmrs.module.chartsearch.solr.ChartSearchIndexer;
import org.openmrs.module.chartsearch.solr.SolrManagement;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 *
 */
public class IndexerTest extends BaseModuleContextSensitiveTest{

	
	ChartSearchIndexer indexer;
	
	/**
	 * Test method for {@link org.openmrs.module.chartsearch.solr.ChartSearchIndexer#indexPatientData(java.lang.Integer)}.
	 */
	@Test
	public void testIndexPatiendData() {
		indexer.clearIndex("patientIds" , "28", 0, 0);
		indexer.indexPatientData(28);
		PatientInfo pi = indexer.getPatientInfo(28);
		assertNotNull(pi);
		assertEquals(pi.getPatientId().intValue(), patientID);
		try {
			List<ChartListItem> obs = solr.search(patientID, "blood", 0, 10);
			for (ChartListItem item : obs) {
				System.out.println(((ObsItem) item).getConceptName());
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	@Before
    public void setUp() {
		indexer = new ChartSearchIndexer();
    }
 
    @After
    public void tearDown() {
        SolrManagement  management = new SolrManagement();
       // management.shutdown();
    }
	


}
