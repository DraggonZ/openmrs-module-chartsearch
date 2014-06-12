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
package org.openmrs.module.chartsearch.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.chartsearch.categories.CategoryFilter;
import org.openmrs.module.chartsearch.categories.SubCategoryFilter;
import org.openmrs.module.chartsearch.synonyms.Synonym;
import org.openmrs.module.chartsearch.synonyms.SynonymGroup;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(ChartSearchService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface ChartSearchService extends OpenmrsService {
    /**
     * Retrieve synonym group by id
     *
     * @param id - The group id.
     * @return the synonym group that matches the given id.
     * @should not return voided group
     */
    SynonymGroup getSynonymGroupById(Integer id);

    /**
     * Retrieve List of all synonym groups.
     *
     * @return List of all synonym groups.
     * @should not return voided group
     */
	List<SynonymGroup> getAllSynonymGroups();

    /**
     * Delete a synonym group.
     *
     * @param synGroup - The group to delete.
     * @should not return voided group
     */
    void purgeSynonymGroup(SynonymGroup synGroup);

    /**
     * Update a synonym group or save a new one.
     *
     * @param synGroup - The group to save.
     * @return the synonym group that has been saved.
     * @should not return voided group
     */
    SynonymGroup saveSynonymGroup(SynonymGroup synGroup) throws APIException;

    /**
     * Retrieve synonym group by group name
     *
     * @param groupName - The group name.
     * @return the synonym group that matches the given groupName.
     * @should not return voided group
     */
    SynonymGroup getSynonymGroupByName(String groupName);

    /**
     * Retrieve a list of synonym groups by is category.
     *
     * @param isCategory - Determines if a group is a category.
     * @return if isCategory == true => return list of synonym groups that are categories, else, return list of groups that aren't categories.
     * @should not return voided groups
     */
    List<SynonymGroup> getSynonymGroupsIsCategory(boolean isCategory);

    /**
     * Retrieve a count of synonym groups.
     *
     * @param byIsCategory - Determine if a group is a category.
     * @return if byIsCategory == false => return count of all groups, else, returns count for categorized groups only.
     * @should not return voided groups
     */
    Integer getCountOfSynonymGroups(boolean byIsCategory);

    /**
     * Retrive a synonym by its id.
     *
     * @param id - id of the synonym.
     * @return the synonym that matches the id.
     * @should not return voided synonyms
     */
    Synonym getSynonymById(Integer id);

    /**
     * Retrieve all synonyms.
     *
     * @return List of all the synonyms.
     * @should not return voided synonyms
     */
    List<Synonym> getAllSynonyms();

    /**
     * Delete a synonym.
     *
     * @param synonym - the synonym to delete.
     * @should not return voided synonyms
     */
    void purgeSynonym(Synonym synonym);

    /**
     * Update a synonym or save a new one.
     *
     * @param synonym - the synonym to save.
     * @return the synonym that has been saved.
     * @should not return voided synonyms
     */
    Synonym saveSynonym(Synonym synonym) throws APIException;

    /**
     * Retrieve synonyms by synonym group
     *
     * @param synonymGroup - the synonym group.
     * @return list of synonyms for the given group.
     * @should not return voided synonyms
     */
    List<Synonym> getSynonymsByGroup(SynonymGroup synonymGroup);

    /**
     * Retrieve count of synonyms by synonym group
     *
     * @param synonymGroup - the synonym group.
     * @return count of synonyms for the given group.
     * @should not return voided synonyms
     */
    Integer getSynonymsCountByGroup(SynonymGroup synonymGroup);
    
    public CategoryFilter getCategoryFilterById(Integer categoryFilterId);
	
	public List<CategoryFilter> getCategoryAllFilters();
	
	public void createCategoryFilter(CategoryFilter categoryFilter);
	
	public void updateCategoryFilter(CategoryFilter categoryFilter);
	
	public void deleteCategoryFilter(CategoryFilter categoryFilter);
	
	public CategoryFilter getCategoryFilterByUuid(String uuid);
	
	public SubCategoryFilter getSubCategoryFilterById(Integer subCategoryFilterId);
	
	public List<SubCategoryFilter> getSubCategoryFiltersFor(CategoryFilter categoryFilter);
	
	public void createSubCategoryFilter(SubCategoryFilter subCategoryFilter);
	
	public void updateSubCategoryFilter(SubCategoryFilter subCategoryFilter);
	
	public void deleteSubCategoryFilter(SubCategoryFilter subCategoryFilter);
	
	public SubCategoryFilter getSubCategoryFilterByUuid(String uuid);
}